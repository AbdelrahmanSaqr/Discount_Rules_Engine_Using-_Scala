import scala.collection.mutable
//////// Mutable Sequence ////////
    //// Mutable reference ////
var x = mutable.Seq(1, 2, 3)

// Change Reference
x = mutable.Seq(1, 5, 3)

// Change value
x.update(2, 7)  // Notice no return value
x

    //// Immutable reference ////
val x = mutable.Seq(1, 2, 3)

// Can't change Reference
//x = mutable.Seq(1, 5, 3)

// But you can change value in-place
x.update(2, 7)  // Notice no return value
x

//////// Immutable Sequence ////////
    //// Immutable reference ////
val x = Seq(1, 2, 3)

// Can't change reference
//x = Seq(1, 5, 3)

// can't change value in-place
//x.update(1, 5)

    //// Mutable reference ////
var x = Seq(1, 2, 3)

// You can change reference
x = Seq(1, 5, 3)

// But you cant change the value in-place
// x.update(2, 7)