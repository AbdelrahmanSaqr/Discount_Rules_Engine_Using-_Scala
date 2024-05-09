import scala.io.Source

// take & takeWhile
val newList: List[Int] = List(1, 2, 3, 8, 9, 4)
newList.take(1)
newList.take(2)
newList.takeRight(2)
newList.takeWhile(_<5)
newList.takeWhile(_>5)

// drop & dropWhile
newList.drop(1)
newList.dropRight(2)
newList.dropWhile(_<5)
newList.dropWhile(_>5)

// head, tail & last
newList.head
newList.tail
newList.last
newList.slice(2,5)

// map
val list = List.range(0, 10)
def multiplyBy2(x: Int): Int = x * 2
def multiply(x: Int, y: Int) = x * y
list.map(_ * 2)
list.map(number => number *2)
list.map(multiplyBy2)
list.map(x => multiply(x, 3))

// flatten
val book = Source.fromFile("C:\\Users\\youss\\work\\ITI 44\\BigData Course\\ScalaCode\\src\\main\\resources\\book.txt").getLines().toList
val words = book.map(_.toLowerCase.split("\\W+"))
val wordsFlatten = words.flatten

// flatMap = map + flatten
val wordsFlatten = book.flatMap(_.toLowerCase.split("\\W+"))

// groupBy
val wordsGrouped = wordsFlatten.groupBy(identity)

// mapValues
val wordsCount = wordsGrouped.mapValues(_.size).toMap

val wordsOnes = wordsGrouped.mapValues(_.map(_ => 1)).toMap
val wordsCount = wordsOnes.mapValues(_.reduce(_+_)).toMap

// groupMapReduce
val wordsCount = wordsFlatten.groupMapReduce(identity)(_ => 1)((x, y) => x + y)

// filter
val sWords = wordsCount.filter(kv => kv._1.startsWith("s"))
val frequentWords = wordsCount.filter(kv => kv._2 > 5)

def isPalindrome(s: String): Boolean = {
  if (s.length <= 1) true
  else if (s.head != s.last) false
  else isPalindrome(s.slice(1, s.length - 1))
}
val palindromes = wordsCount.filter(kv => isPalindrome(kv._1))

// reduce
val newList = Range.inclusive(1, 10).toList // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
newList.reduce((n1, n2)=>n1+n2)
newList.reduce(_+_)

def add(x: Int, y: Int): Int = {
  val theSum = x + y
  println(s"received $x and $y, their sum is $theSum")
  theSum
}
newList.reduce(add)

// another reduce example
val listOfStrings = List("thIs", "iS", "a", "lIst", "of", "sTringS")
val concatenatedStrings = listOfStrings.reduce((s1, s2) => s1.toLowerCase + " " + s2.toLowerCase)

// fold
newList.fold(0)(_+_)
newList.fold(1)(_+_)
newList.fold(10)(_+_)

listOfStrings.foldLeft("START:")((s1, s2) => s1.toLowerCase + " " + s2.toLowerCase)
listOfStrings.foldRight(":END")((s1, s2) => s1.toLowerCase + " " + s2.toLowerCase)

// foreach (mostly used for side effects as it returns a Unit)
listOfStrings.foreach(println)
