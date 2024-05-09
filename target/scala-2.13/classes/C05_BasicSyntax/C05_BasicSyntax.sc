///////////////// Methods ////////////////
// Method with single Input
def square(x: Int) = x * x

def multiply(x: Int, y: Int): Int = x * y






// Method with multiple inputs
def multiply(x: Int, y: Int): Int = {
    return x * y
}

// You can omit the return keyword
def greet(name: String): String = {
    "Hello" + name
}

// You can omit the return type
// (only if there is no return keyword)
def half(x: Int) = {
    x / 2.0
}

///////////////// if statements /////////////////
var grade = 70
if (grade < 60)
    print("C")
else if ((grade >= 60) && (grade < 80))
    print("B")
else
    print("A")

//////////////// for loop //////////////////
var counter = 0
for(i <- 1 until 20){
    if (i % 2 == 0) counter += 1
}
print(s"Found $counter even numbers")

Range(1, 20) == (1 until 20)
Range.inclusive(1, 20) == (1 to 20)
Range(1,20,2) == (1 until 20 by 2)
Range.inclusive(20, 1, -2) == (20 to 1 by -2)

////////////// match case ////////////////
def getMonthName(monthNumber: Int): String = {
    var monthName = ""
    monthNumber match {
        case 1 => monthName = "January"
        case 2 => monthName = "February"
        case 3 => monthName = "March"
        case 4 => monthName = "April"
        case 5 => monthName = "May"
        case 6 => monthName = "June"
        case 7 => monthName = "July"
        case 8 => monthName = "August"
        case 9 => monthName = "September"
        case 10 => monthName = "October"
        case 11 => monthName = "November"
        case 12 => monthName = "December"
        case _ => monthName = "Invalid month number"
    }
    monthName
}
getMonthName(7)
getMonthName(20)

// handle alternate cases
def evenOrOdd(i: Int) = i match {
    case 1 | 3 | 5 | 7 | 9 => println("odd")
    case 2 | 4 | 6 | 8 | 10 => println("even")
    // catch the default with a variable so you can print it
    case x => println(s"$x is not in range")
}
evenOrOdd(1)

// Add if expressions to your cases
var count = 4
count match {
    case 1 => println("one, a lonely number")
    case x if (x == 2 || x == 3) => println("two's company, three's a crowd")
    case x if (x > 3) => println("4+, that's a party")
    case _ => println("i'm guessing your number is zero or less")
}

