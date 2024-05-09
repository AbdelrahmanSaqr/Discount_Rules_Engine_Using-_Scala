def productTail(f: Int => Int)(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
        if(a > b) acc
        else loop(a+1, acc*f(a))
    }
    loop(a, 1)
}

def identityProduct: (Int, Int) => Int = productTail(x => x)
identityProduct(4,9)

productTail(x => x*x)(3, 4)

def factorial(n: Int): Int = {
    identityProduct(1, n)
}

factorial(5)