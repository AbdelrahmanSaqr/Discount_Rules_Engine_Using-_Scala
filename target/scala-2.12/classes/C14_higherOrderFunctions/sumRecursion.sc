import scala.annotation.tailrec

// Sum numbers
def sumIntsTail(a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, acc: Int): Int = {
        if (a > b) acc
        else loop(a+1, acc+a)
    }
    loop(a, 0)
}
sumIntsTail(2, 5)

// Sum square numbers
def sumSquaresTail(a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, acc: Int): Int = {
        if (a > b) acc
        else loop(a+1, acc+a*a)
    }
    loop(a, 0)
}
sumSquaresTail(2, 5)

// Sum double numbers
def sumDoublesTail(a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, acc: Int): Int = {
        if (a > b) acc
        else loop(a+1, acc+a*2)
    }
    loop(a, 0)
}
sumDoublesTail(2, 5)

// Generic reusable function
def sumTail(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
        if(a > b) acc
        else loop(a+1, acc+f(a))
    }
    loop(a, 0)
}
def square(x: Int) = x * x
def double(x: Int) = x * 2

sumTail(square, 2, 5)
sumTail(double, 2, 5)
sumTail(x => x-1, 4, 9)

// Reuse
def sumDoubles(x: Int, y: Int): Int = sumTail(double,x, y)
def sumSquares(x: Int, y: Int): Int = sumTail(square, x, y)
sumDoubles(2, 5)
sumSquares(2, 5)