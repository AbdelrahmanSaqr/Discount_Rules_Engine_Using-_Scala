package Project

import java.sql.{Connection, Date, DriverManager, PreparedStatement}
import java.io.{File, FileOutputStream, PrintWriter}
import scala.io.{BufferedSource, Source}
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.Instant

object orders_project extends App {

  val source: BufferedSource = Source.fromFile("src/main/resources/TRX1000.csv")
  val lines: List[String] = source.getLines().drop(1).toList // drop header


  val f: File = new File("src/main/resources/logs.txt")
  val writer = new PrintWriter(new FileOutputStream(f, true))

  logEvents("INFO/Debug", "Connect to Database")
  val url = "jdbc:oracle:thin:@//localhost:1521/XE"
  val username = "SCALA_PROJECT"
  val password = "123"
  val connection: Connection = DriverManager.getConnection(url, username, password)
  logEvents("INFO/Debug", "Successful Connect to Database")



  case class Order(timestamp: LocalDate, product_name: String, expiry_date: LocalDate, quantity: Int, unit_price: Double, channel: String, payment_method: String)

    def toOrder(line: String): Order = {
    val fields = line.split(",").map(_.trim)

    val timestamp = LocalDate.parse(fields(0).split("T")(0), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val product_name = fields(1)
    val expiry_date = LocalDate.parse(fields(2), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val quantity = fields(3).toInt
    val unit_price = fields(4).toDouble
    val channel = fields(5)
    val payment_method = fields(6)


    Order(timestamp, product_name, expiry_date, quantity, unit_price, channel, payment_method)
  }

 // Function to calculate the discount based on remaining days until expiry
    def remainingDays(orderDate: LocalDate, expiryDate: LocalDate): Long = {
      ChronoUnit.DAYS.between(orderDate, expiryDate).toInt
    }
    def rule1ExpiryDiscount(timestamp: LocalDate, expiryDate: LocalDate): Double = {

      logEvents("INFO", "Expairy Date Discount Rule Start")

      val daysRemaining = remainingDays(timestamp, expiryDate)

      if (daysRemaining >= 30) {
        logEvents("INFO", "Expairy Date Discount Rule End")
        0 // No discount if the product has expired
      } else {
        logEvents("INFO", "Expairy Date Discount Rule End")
        // Linearly decreasing discount for remaining days 29 to 1
        val discountPercentage = 30 - daysRemaining
        discountPercentage.toDouble / 100 // Convert to percentage
      }
  }

 // Function to calculate the discount based on product type
    def rule2ProductTypeDiscount(productName: String): Double = {

      logEvents("INFO", "Product Type Discount Rule Start")

      val ProductName = productName.toLowerCase
      if (ProductName.startsWith("cheese")) {
        logEvents("INFO", "Product Type Discount Rule End")
        0.1
      } // 10% discount for cheese
      else if (ProductName.startsWith("wine")) {
        logEvents("INFO", "Product Type Discount Rule End")
        0.05
      } // 5% discount for wine
      else 0 // No discount for other products
  }

 // Function to calculate the discount based on special days
    def rule3SpecialDayDiscount(transactionDate: LocalDate): Double = {

      logEvents("INFO", "Special Days Discount Rule Start")

      if (transactionDate.getMonthValue == 3 && transactionDate.getDayOfMonth == 23)
        {
          logEvents("INFO", "Special Days Discount Rule End")
          0.5
        } // 50% discount on March 23rd
      else {
        logEvents("INFO", "Special Days Discount Rule End")
        0
      } // No special discount
  }

 // Function to calculate the discount based on quantity sold
    def rule4QuantityDiscount(quantity: Int): Double = {

      logEvents("INFO", "Quantity Sold Discount Rule Start")
      logEvents("INFO", "Quantity Sold Discount Rule End")
      quantity match {
        case q if q >= 6 && q <= 9 => 0.05 // 5% discount for 6 to 9 units
        case q if q >= 10 && q <= 14 => 0.07 // 7% discount for 10 to 14 units
        case q if q >= 15 => 0.1 // 10% discount for more than 15 units
        case _ => 0 // No discount for quantities less than 6
      }

  }

 // Function to calculate the discount based on payment method
    def rule5PaymentMethodDiscount(paymentMethod: String): Double = {

      logEvents("INFO", "Payment Type Discount Rule Start")

      if (paymentMethod.equalsIgnoreCase("Visa")) {
        logEvents("INFO", "Payment Type Discount Rule End")
        0.05
      } // 5% discount for sales made using Visa cards
      else {
        logEvents("INFO", "Payment Type Discount Rule End")
        0
      } // No discount for other payment methods
  }

 // Function to calculate the discount based on channel
    def rule6ChannelDiscount(channel: String, qunatity: Int): Double = {

      logEvents("INFO", "Channel Discount Rule Start")

      if(channel == "App"){
          val remainder = qunatity % 5
          logEvents("INFO", "Channel Discount Rule End")
          (qunatity + (5 - remainder)) / 100.0 // Round up to the next multiple of 5
        }
        else {
          logEvents("INFO", "Channel Discount Rule End")
          0
        }
    }

 // Function to Round
    def round(value: Double, places: Int): Double = {
    val bigDecimalValue = BigDecimal(value)
    bigDecimalValue.setScale(places, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

 // log Function
    def logEvents(log_level: String, message: String): Unit = {
    writer.write(s"Timestamp: ${Instant.now()}\tLogLevel: ${log_level}\tMessage: ${message}\n")
    writer.flush()
  }

    def processOrder(o: Order): Unit = {

    logEvents("INFO", "Rules Engin Start")

    val discountRules = List(
      rule1ExpiryDiscount(o.timestamp, o.expiry_date),
      rule2ProductTypeDiscount(o.product_name),
      rule3SpecialDayDiscount(o.timestamp),
      rule4QuantityDiscount(o.quantity),
      rule5PaymentMethodDiscount(o.payment_method),
      rule6ChannelDiscount(o.channel, o.quantity)
    )

    val highestTwoDiscounts = discountRules.sorted.reverse.take(2)

    val removeZeroes = highestTwoDiscounts.filter(_ != 0)
    val discount = if (removeZeroes.nonEmpty) round(removeZeroes.sum / removeZeroes.size.toDouble, 3) else 0

    val priceAfterDiscount = round((o.unit_price * o.quantity) * (1 - discount), 2)

    logEvents("INFO", "Rules Engin End")
    logEvents("INFO", "Write to Database Start")

    writeToDatabase(o, discount, priceAfterDiscount)

    logEvents("INFO", "Write to Database Finished")
  }

    def writeToDatabase(o: Order, discount: Double, priceAfterDiscount: Double): Unit = {
    val preparedStatement: PreparedStatement = connection.prepareStatement(
      "INSERT INTO SCALA_PROJECT.ORDERS_SCALA (ORDER_DATE, Product_Name, Expiry_Date, Quantity, Unit_Price, Channel, Payment_Method, Discount, Price_After_Discount) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")

    preparedStatement.setDate(1, java.sql.Date.valueOf(o.timestamp))
    preparedStatement.setString(2, o.product_name)
    preparedStatement.setDate(3, java.sql.Date.valueOf(o.expiry_date))
    preparedStatement.setDouble(4, o.quantity.toDouble) // Assuming quantity is a Double in the database
    preparedStatement.setDouble(5, o.unit_price) // Assuming unit_price is a Double in the database
    preparedStatement.setString(6, o.channel)
    preparedStatement.setString(7, o.payment_method)
    preparedStatement.setDouble(8, discount)
    preparedStatement.setDouble(9, priceAfterDiscount)

    preparedStatement.executeUpdate()
    preparedStatement.close()
  }


  logEvents("INFO/Debug", "Orders Processing Start")
  lines.map(toOrder).foreach(processOrder)
  logEvents("INFO/Debug", "Orders Processing End")
  logEvents("INFO/Debug", "Close Connection with Database")
  writer.close()
  connection.close()
}
