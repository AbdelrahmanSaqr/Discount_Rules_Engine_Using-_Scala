case class Person(name: String, relation: String)

// apply
val naguib = Person("Naguib", "cousin")

// unapply
Person.unapply(naguib)

// parameters are public val fields
// accessor methods
s"${naguib.name} is my ${naguib.relation}"
// immutable
// naguib.name = "Nageeb"

// copy
val nageeb = naguib.copy(name="Nageeb")

// equal
naguib.equals(nageeb)
naguib == nageeb

// hashCode
naguib.hashCode()

// toString
print(naguib)

trait animal
case class Dog(name: String, breed: String) extends animal
case class Bear(name: String, color: String) extends animal
def getPrintableString(a: animal): String = a match {
  case Dog(name, breed) =>
    s"$name is a $breed dog."
  case Bear(n, c) =>
    s"$n's color is $c."
}
val d = Dog("Rocky", "German Shepherd")
val b = Bear("grizzly", "brown")
getPrintableString(d) // Rocky is a German Shepherd dog.
getPrintableString(b) // grizzly's color is brown.

