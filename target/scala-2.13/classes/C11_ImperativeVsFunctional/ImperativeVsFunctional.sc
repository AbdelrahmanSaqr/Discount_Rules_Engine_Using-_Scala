import scala.collection.mutable

def addOne(x:Int) = {x+1}
def multiplyTen(x:Int) = {x*10}
def subtractThree(x:Int) = {x-3}

val data = List(3, 2, 1, 5, 4, 6)

// Requirement 1: Add one then multiply ten then subtract three
// Imperative
for (d <- data) {
  println(subtractThree(multiplyTen(addOne(d))))
}

// declarative
data.
  map(addOne).
  map(multiplyTen).
  map(subtractThree).
  foreach(println)




// Requirement 2: Only subtract three If the result of
// first 2 transformations is bigger than 30
// Imperative
for (d <- data) {
  val temp = multiplyTen(addOne(d))
    if (temp > 30) {
      println(subtractThree(temp))
    }
}

// declarative
data.
  map(addOne).
  map(multiplyTen).
  filter(_ > 30).
  map(subtractThree).
  foreach(println)




// Requirement 3: We need ascendingly sorted output
// Imperative
var tempSet: mutable.SortedSet[Int] = mutable.SortedSet()
for (d <- data) {
  val temp = multiplyTen(addOne(d))
  if (temp > 30) {
    tempSet += subtractThree(temp)
  }
}
for (e <- tempSet) {
  println(e)
}

// declarative
data.
  map(addOne).
  map(multiplyTen).
  filter(_ > 30).
  map(subtractThree).
  sortBy(+_).
  foreach(println)




// Requirement 4: total sum of outputs
// Imperative
var tempSet: mutable.SortedSet[Int] = mutable.SortedSet()
for (d <- data) {
  val temp = multiplyTen(addOne(d))
  if (temp > 30) {
    tempSet += subtractThree(temp)
  }
}
var total = 0
for (e <- tempSet) {
  total += e
}
println(total)

// declarative
data.
  map(addOne).
  map(multiplyTen).
  filter(_ > 30).
  map(subtractThree).
  sortBy(+_).
  sum