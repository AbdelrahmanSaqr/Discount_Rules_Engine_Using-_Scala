val a = 5
val b = 7
var max = 0
// If Statement
if (a>b) {
    max = a
} else {
    max = b
}

// If Expression
val larger =
    if (a>b) a
    else b

/////////////////////////////////////////////////////
val lst = List(1, 2, 3, 4, 5)
var new_lst: List[Int] = List()

// for statement
for (i <- lst) {
    new_lst = new_lst :+ (i * 2)
}
println(new_lst)

// for expression
val doubles = for (i <- lst) yield i*2

/////////////////////////////////////////////////////
val dayNum = 4
var dayName = ""

// match statement
dayNum match {
    case 1 => dayName = "Sunday"
    case 2 => dayName = "Monday"
    case 3 => dayName = "Tuesday"
    case 4 => dayName = "Wednesday"
    case 5 => dayName = "Thursday"
    case 6 => dayName = "Friday"
    case 7 => dayName = "Saturday"
    case _ => dayName = "Invalid"
}
dayName

// match expression
val dayName = dayNum match {
    case 1 => "Sunday"
    case 2 => "Monday"
    case 3 => "Tuesday"
    case 4 => "Wednesday"
    case 5 => "Thursday"
    case 6 => "Friday"
    case 7 => "Saturday"
    case _ => "Invalid"
}
