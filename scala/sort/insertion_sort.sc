object InsertionSort {
    private val inOrder = (x1: Int, x2: Int, desc: Boolean) => {
        if (desc) x1 > x2 else x1 < x2
    } 
    /**
     * Sorts the given list using the iterative approach to insertion sort
     * 
     * @param toSort the list of integers that needs to be sorted
     * @param sortDesc sorts in descending order
     */
    def insertionSortImperative(toSort: List[Int], sortDesc: Boolean = false): List[Int] = {
        // This logic requires a mutable alternative to the list
        var arr = toSort.toArray
        for (i <- 1 until arr.length) {
            var j = i - 1
            var item = arr(i)
            while (j >= 0 && !inOrder(arr(j), item, sortDesc)) { 
                arr(j + 1) = arr(j)
                j -= 1
            }
            arr(j + 1) = item
        }
        arr.toList
    }

    /**
     * Sorts the given list using the recursive approach to insertion sort
     * along with pattern matching
     * 
     * @param toSort the list of integers that needs to be sorted
     * @param sortDesc sorts in descending order
     */
    def insertionSortFunctional(toSort: List[Int], sortDesc: Boolean = false): List[Int] = {
        def insert(head: Int, tail: List[Int]): List[Int] = tail match {
            case List() => List(head)
            case head2 :: tail2 => if (inOrder(head, head2, sortDesc)) head :: tail
                                   else head2 :: insert(head, tail2)
        }
        toSort match {
            case List() => List()
            case head :: tail => insert(head, insertionSortFunctional(tail, sortDesc))
        }
    }
}

