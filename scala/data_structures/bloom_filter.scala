package data_structures

import scala.util.hashing.MurmurHash3.stringHash
import Math.{abs, ceil, log, pow, round}

case class BloomFilter[A](
  nbOfItems: Int,
  falsePositiveProbability: Float,
  private val array: Array[Boolean],
  private val numberOfHashFunctions: Int,
  private val hashSeed: Int
) {

  private val hashFunctions: Stream[String => Int] =
    (1 to numberOfHashFunctions)
      .toStream
      .map(i => (str: String) => abs(stringHash(str, hashSeed + i)) % array.length)

  def +=(item: A): BloomFilter[A] = {
    val itemString = item.toString
    val hashes = hashFunctions.map(_(itemString))
    val updatedArray = hashes.foldLeft(array)((acc, hash) => acc.updated(hash, true))
    this.copy(array=updatedArray)
  }

  def ++=(items: Seq[A]): BloomFilter[A] = items.foldLeft(this)(_ += _)

  def mayContain(item: A): Boolean = {
    val itemString = item.toString
    hashFunctions.forall(fn => array(fn(itemString)))
  }

  def approxNumberOfItems(): Int = {
    val setBits = array.filter(identity).length.toDouble
    val totalBits = array.length.toDouble
    round(
      (-totalBits / numberOfHashFunctions.toDouble) * log((1D - (setBits / totalBits)))
    ).toInt
  }
}

object BloomFilter {

  private def getArraySize(nbOfItems: Int, falsePositiveProbability: Float): Int =
    ceil(
      abs(nbOfItems * log(falsePositiveProbability)).toDouble / log(1D / pow(log(2), 2).toDouble)
    ).toInt

  private def getNumberOfHashFunctions(nbOfItems: Int, arraySize: Int): Int =
    round(
      (arraySize.toDouble / nbOfItems.toDouble) * log(2)
    ).toInt

  def apply[A](nbOfItems: Int, falsePositiveProbability: Float): BloomFilter[A] = {
    val arraySize = getArraySize(nbOfItems, falsePositiveProbability)
    BloomFilter(
      nbOfItems=nbOfItems,
      falsePositiveProbability=falsePositiveProbability,
      array=Array.ofDim[Boolean](arraySize),
      numberOfHashFunctions=getNumberOfHashFunctions(nbOfItems, arraySize),
      hashSeed=scala.util.Random.nextInt
    )
  }
}

object BloomFilterTest {

  def main(args: Array[String]): Unit = {
    val empty = BloomFilter[Int](4, 0.1f)
    val withOne = empty += 4
    val withThree = withOne ++= Vector(5, 2910)
    println(withThree mayContain 4)
    println(withThree mayContain 5)
    println(withThree mayContain 2910)
    println(withThree mayContain 211)
    println(withThree.approxNumberOfItems)
  }
}
