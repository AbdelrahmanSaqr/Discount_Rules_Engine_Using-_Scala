// Function that takes function as argument
def transformX(x: Int, f: Function1[Int, Int]): Int = {
    x match {
        case x if x < 10 => x + 1
        case x if x >= 10 => f(x)
    }
}
def cube(x: Int): Int = x * x * x
def subtractThree(x: Int): Int = x - 3
transformX(5, cube)
transformX(15, subtractThree)
transformX(12, x => x+7)

// Function that returns function as value
def addX(x: Int): Int => Int = {
    def add(num: Int): Int = num + x
    add
}
def addX_2(x: Int): Function1[Int, Int] = num => num + x
def addX_3(x: Int): Int => Int = _ + x

val add12 = addX(12)
val add5 = addX(5)
add12(3)

// Function that takes function as argument
// And return function as value
def compose(f1: Int => Boolean,
            f2: Function1[Boolean, Double]):
Int => Double = {
    x => f2(f1(x))
}


def isEven(x: Int): Boolean = x % 2 == 0
def BoolToDbl(x: Boolean): Double = if (x) 1.0 else 0.0
val newFunction = compose(isEven, BoolToDbl)

newFunction(20)




def composeAny[A, B, C](f1: A => B, f2: B => C): A => C = {
    x: A => f2(f1(x))
}
composeAny(isEven, BoolToDbl)(22)