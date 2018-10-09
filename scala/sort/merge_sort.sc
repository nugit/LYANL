object MergeSort {
  def apply(comparisonPredicate: (Int, Int) => Boolean)(seq: Seq[Int]): Seq[Int] = {
    seq match {
      case first +: second +: tail =>
        val pivot = Math.round((seq.length - 1).toFloat / 2)
        merge(
          comparisonPredicate,
          apply(comparisonPredicate)(seq.take(pivot)),
          apply(comparisonPredicate)(seq.drop(pivot))
        )
      case _ => seq
    }
  }

  def merge(predicate: (Int, Int) => Boolean, left: Seq[Int], right: Seq[Int]): Seq[Int] = {
    left.length match {
      case 0 => right
      case _ => right.length match {
        case 0 => left
        case _ =>
          val headLeft +: tailLeft = left;
          val headRight +: tailRight = right;
          if (predicate(headLeft, headRight)) headLeft +: merge(predicate, tailLeft, right)
          else headRight +: merge(predicate, left, tailRight)
      }
    }
  }
}

val predicate = (a: Int, b: Int) => a < b
println(MergeSort(predicate)(Seq(2,4,9,1,3,2,7,0)))
