var y = 7
def x = {
    val z = 5
    2 + y + z
}

y = 10
x

y=12
x




val llist = LazyList(1, 2, 3, 4, 5, 6, 7, 8)
llist.head
llist.tail

llist.take(3)
llist.map(x => x +2)
val x = llist.map(x => x +2).foreach(println)


def and(b1: Boolean, b2: => Boolean): Boolean = {
    if (!b1) false else {
        if (!b2) false
        else true
    }
}
val x = 5
val y = 10
and(false, (x + 2 * y > 10))