import java.lang.Thread.sleep
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
/// Containers Option

//def convertToInt(intVal: String): Int = {
//  intVal.toInt
//}

    //convertToInt(":)")
    // 1
//    def convertToInt(intVal: String): Int = {
//      try{
//        intVal.toInt
//      }
//      catch {
//        case _: Exception => 0
//      }
//    }

//    // 2
//    def convertToInt(intVal: String): Int = {
//      try{
//        intVal.toInt
//      }
//      catch {
//        case e: Exception => 0 // what if we passed 0 ?
//      }
//    }
//
//    // 3
    def convertToInt(intVal: String): Option[Int] = {
      try{
        Some(intVal.toInt)
      }
      catch {
        case e: Exception => {
          println("Error")
          None
        }
      }
    }
convertToInt("5")
//
//    var x = "5"
//    val res = convertToInt(x)
//
//    if(res != None){ // !res.isEmpty // res.isDefined
//      println(res.get)
//    }else{
//      println("Couldn't parse")
//    }
//
//    val str = ":)"
//
    convertToInt("q") match {
      case Some(h) => println(h)
      case None => println("That didn't work.")
    }
//
//    /// Some
//
//
//    def convertToInt(intVal: String): Either[String, Int] = {
//      try{
//        Right(intVal.toInt)
//      }
//      catch {
//        case e: Exception => Left(intVal)
//      }
//    }
//
//    val x = "5"
//    val result = convertToInt(x)
//
//    result match {
//      case Right(x) => s"You passed me the Int: $x, which I will increment. $x + 1 = ${x+1}"
//      case Left(x)  => s"You passed me the String: $x"
//    }
//
//
//    /// Future
//
//    val f = Future {
//      sleep(5000)
//      1 + 1
//    }
//
//    //this is blocking (blocking is bad)
//    println("Started processing")
//    val result = Await.result(f, 6.second)
//    println(result)
//    println("Hello world")
//
//
//    //
//
//    println("starting calculation ...")
//    val f = Future {
//      sleep(150)
//      42
//    }
//
//    println("before onComplete")
//
//    f.onComplete {
//      case Success(value) => println(s"Got the callback, meaning = $value")
//      case Failure(e) => e.printStackTrace
//    }
//}
// do the rest of your work
//sleep(100)
//println("A ...");
//sleep(100)
//println("B ...")
//sleep(100)
//println("C ...")
//sleep(100)
//println("D ...")
//sleep(100)
//println("E ...")
//sleep(100)
//println("F ...")
//sleep(100)


