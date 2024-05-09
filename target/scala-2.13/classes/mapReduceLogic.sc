def sumRange(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
        if (a > b) acc
        else loop(a+1, acc+a)
    }
    loop(a, 0)
}
def productRange(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
        if(a > b) acc
        else loop(a+1, acc*a)
    }
    loop(a, 1)
}
def mapReduce(map: Int => Int, reduce: (Int, Int) => Int, identity: Int)(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
        if(a > b) acc
        else loop(a+1, reduce(acc, map(a)))
    }
    loop(a, identity)
}

def sum: (Int, Int) => Int = mapReduce(x => x, (x, y) => x + y, 0)
def product: (Int, Int) => Int = mapReduce(x => x, (x, y) => x * y, 1)
def factorial: Int => Int = product(1, _)
factorial(5)
