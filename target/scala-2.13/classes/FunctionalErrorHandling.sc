import scala.util.{Try,Success,Failure}

def toInt(s: String): Try[Int] = Try(Integer.parseInt(s.trim))
val toInt2: String => Try[Int] = s => {
  Try(Integer.parseInt(s.trim))
}

toInt("1")
toInt("one")

val x = "5"
toInt(x) match {
  case Success(i) => println(i)
  case Failure(s) => println(s"Failed. Reason: $s")
}