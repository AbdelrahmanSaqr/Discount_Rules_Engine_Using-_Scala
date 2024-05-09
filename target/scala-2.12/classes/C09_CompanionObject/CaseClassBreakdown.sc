// Class with companion object
class Person2(val name: String, val age: Int) {
    override def equals(obj: Any): Boolean = obj match {
        case person: Person2 => this.name == person.name && this.age == person.age
        case _ => false
    }
    //override def hashCode(): Int = name.hashCode() + age.hashCode()
    def copy(name: String = this.name, age: Int = this.age): Person2 = new Person2(name, age)
    override def toString: String = s"Person(name = $name, age = $age)"
}
object Person2 {
    def apply(name: String, age: Int): Person2 = new Person2(name, age)
    def unapply(person: Person2): Option[(String, Int)] = Some((person.name, person.age))
}

val ahmed = Person2("ahmed", 26)
val samy = ahmed.copy(name = "samy")
samy == ahmed
samy match {
    case Person2(name, age) => s"$name's age is $age"
}
//val personMap: HashMap[Person2, String] = HashMap(ahmed -> "hamada", samy -> "semsem")









// Equivalent case class
case class Person3(var name: String, var age: Int)

val ahmed = Person3("ahmed", 26)
val samy = ahmed.copy(name = "samy")
samy == ahmed
samy match {
    case Person3(name, age) => s"$name's age is $age"
}







// pattern matching example
trait Animal
case class Dog(name: String) extends Animal
case class Cat(breed: String) extends Animal

def animalType(a: Animal): String = a match {
    case Cat(breed) => breed
    case Dog(name) => name
}

var c = new Cat("sphinx")
animalType(c)