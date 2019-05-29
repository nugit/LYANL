object MergeSort {

  private def merge[A](predicate: (A, A) => Boolean, left: Seq[A], right: Seq[A]): Seq[A] =
    if (left.isEmpty) right
    else if (right.isEmpty) left
    else {
      val headLeft +: tailLeft = left;
      val headRight +: tailRight = right;
      if (predicate(headLeft, headRight)) headLeft +: merge(predicate, tailLeft, right)
      else headRight +: merge(predicate, left, tailRight)
    }

  def sort[A](seq: Seq[A])(implicit comparisonPredicate: (A, A) => Boolean): Seq[A] =
    if (seq.length < 2) seq
    else {
      val pivot = seq.length / 2
      merge(
        comparisonPredicate,
        sort(seq take pivot),
        sort(seq drop pivot),
      )
    }
}
