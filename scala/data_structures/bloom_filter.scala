package data_structures

import scala.collection.BitSet
import scala.util.hashing.MurmurHash3.stringHash
import Math.{abs, ceil, log, pow, round}

case class BloomFilter[A](
  nbOfItems: Int,
  falsePositiveProbability: Float,
  private val bitset: BitSet,
  private val maxSize: Int,
  private val numberOfHashFunctions: Int,
  private val hashSeed: Int,
) {

  private lazy val hashFunctions: Stream[String => Int] =
    (1 to numberOfHashFunctions)
      .toStream
      .map(i => (str: String) => abs(stringHash(str, hashSeed + i)) % maxSize)

  def :+(item: A): BloomFilter[A] = {
    val itemString = item.toString
    copy(bitset=(bitset ++ hashFunctions.map(_(itemString))))
  }

  def ++(items: IterableOnce[A]): BloomFilter[A] = items.iterator.foldLeft(this)(_ :+ _)

  def mayContain(item: A): Boolean = {
    val itemString = item.toString
    hashFunctions.forall(bitset contains _(itemString))
  }

  def approxNumberOfItems: Int = {
    val totalBits = maxSize.toDouble
    round(
      (-totalBits / numberOfHashFunctions.toDouble) * log((1D - (bitset.size.toDouble / totalBits)))
    ).toInt
  }

  def isEmpty: Boolean = bitset.isEmpty
}

object BloomFilter {

  private def getMaxSize(nbOfItems: Int, falsePositiveProbability: Float): Int =
    ceil(
      abs(nbOfItems * log(falsePositiveProbability)).toDouble / log(1D / pow(log(2), 2).toDouble)
    ).toInt

  private def getNumberOfHashFunctions(nbOfItems: Int, maxSize: Int): Int =
    round(
      (maxSize.toDouble / nbOfItems.toDouble) * log(2)
    ).toInt

  def apply[A](nbOfItems: Int, falsePositiveProbability: Float): BloomFilter[A] = {
    val maxSize = getMaxSize(nbOfItems, falsePositiveProbability)
    BloomFilter(
      nbOfItems=nbOfItems,
      falsePositiveProbability=falsePositiveProbability,
      bitset=BitSet(),
      maxSize=maxSize,
      numberOfHashFunctions=getNumberOfHashFunctions(nbOfItems, maxSize),
      hashSeed=scala.util.Random.nextInt,
    )
  }
}
