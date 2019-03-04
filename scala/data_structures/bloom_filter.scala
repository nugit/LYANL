package data_structures

import scala.util.hashing.MurmurHash3
import scala.util.Random

case class BloomFilter[A](
  nbOfItems: Int,
  falsePositiveProbability: Double,
  private val array: Array[Boolean],
  private val hashFunctions: List[String => Int]
) {

  def +=(elem: A): BloomFilter[A] = {
    val str = elem.toString
    val hashes = this.hashFunctions.map(_(str))
    val newArr = hashes.foldLeft(this.array)((acc, hash) => acc.updated(hash, true))
    BloomFilter(nbOfItems, falsePositiveProbability, newArr, hashFunctions)
  }

  def ++=(elems: Seq[A]): BloomFilter[A] = elems.foldLeft(this)(_ += _)

  def contains(elem: A): Boolean = {
    val str = elem.toString
    this.hashFunctions.forall(fn => this.array(fn(str)))
  }
}

object BloomFilter {

  def apply[A](nbOfItems: Int, falsePositiveProbability: Double): BloomFilter[A] = {
    val arraySize = Math.ceil(Math.abs(nbOfItems * Math.log(falsePositiveProbability)) / Math.log(1 / Math.pow(Math.log(2), 2))).toInt
    val array = Array.ofDim[Boolean](arraySize)

    val nbOfHashFunctions = Math.round((arraySize / nbOfItems) * Math.log(2)).toInt
    val hashFunctions: List[String => Int] =
      (1 to nbOfHashFunctions).toList.map(_ => {
        val seed = Random.nextInt
        (str: String) => Math.abs(MurmurHash3.stringHash(str, seed)) % arraySize
      })
    BloomFilter(nbOfItems, falsePositiveProbability, array, hashFunctions)
  }
}

object BloomFilterTest {

  def main(args: Array[String]): Unit = {
    val empty = BloomFilter[Int](4000, 0.0000001d)
    val withOne = empty += 4
    val withThree = withOne ++= Vector(5, 2910)
    println(withThree contains 4)
    println(withThree contains 5)
    println(withThree contains 2910)
    println(withThree contains 211)
  }
}
