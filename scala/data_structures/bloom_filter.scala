package data_structures

case class BloomFilter[A](
  nbOfItems: Int,
  falsePositiveProbability: Float,
  private val array: Array[Boolean],
  private val hashFunctions: Array[String => Int]
) {

  def +=(item: A): BloomFilter[A] = {
    val itemString = item.toString
    val hashes = this.hashFunctions.map(_(itemString))
    val updatedArray = hashes.foldLeft(this.array)((acc, hash) => acc.updated(hash, true))
    BloomFilter(nbOfItems, falsePositiveProbability, updatedArray, hashFunctions)
  }

  def ++=(items: Seq[A]): BloomFilter[A] = items.foldLeft(this)(_ += _)

  def contains(item: A): Boolean = {
    val itemString = item.toString
    this.hashFunctions.forall(fn => this.array(fn(itemString)))
  }
}

object BloomFilter {

  import Math.{abs, ceil, log, pow, round}

  private def getArraySize(nbOfItems: Int, falsePositiveProbability: Float): Int =
    ceil(
      abs(nbOfItems * log(falsePositiveProbability)) / log(1 / pow(log(2), 2))
    ).toInt

  private def getNumberOfHashFunctions(nbOfItems: Int, arraySize: Int): Int =
    round(
      (arraySize / nbOfItems) * log(2)
    ).toInt

  def apply[A](nbOfItems: Int, falsePositiveProbability: Float): BloomFilter[A] = {
    import scala.util.Random
    import scala.util.hashing.MurmurHash3.stringHash

    val arraySize = getArraySize(nbOfItems, falsePositiveProbability)
    val array = Array.ofDim[Boolean](arraySize)

    val hashFunctions: Array[String => Int] =
      Array.ofDim[String => Int](getNumberOfHashFunctions(nbOfItems, arraySize)).map(_ => {
        val seed = Random.nextInt
        (string: String) => abs(stringHash(string, seed)) % arraySize
      })
    BloomFilter(nbOfItems, falsePositiveProbability, array, hashFunctions)
  }
}

object BloomFilterTest {

  def main(args: Array[String]): Unit = {
    val empty = BloomFilter[Int](4000, 0.0000001f)
    val withOne = empty += 4
    val withThree = withOne ++= Vector(5, 2910)
    println(withThree contains 4)
    println(withThree contains 5)
    println(withThree contains 2910)
    println(withThree contains 211)
  }
}
