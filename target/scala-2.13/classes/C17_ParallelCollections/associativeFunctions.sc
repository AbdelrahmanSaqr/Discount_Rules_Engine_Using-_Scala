import scala.collection.parallel.CollectionConverters.ImmutableIterableIsParallelizable

val list = (1 to 100).toList
val parallelList = list.par

list.reduce(_ + _)
list.reduceRight(_ + _)
parallelList.reduce(_ + _)
parallelList.reduceLeft(_ + _)
parallelList.reduceRight(_ + _)

list.reduce(_ - _)
list.reduceRight(_ - _)

parallelList.reduce(_ - _)

parallelList.reduceLeft(_ - _)
parallelList.reduceRight(_ - _)