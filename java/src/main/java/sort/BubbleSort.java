package sort;

import java.util.List;
import javafx.util.Pair;
import sort.Options.ORDER;

public class BubbleSort {
  static <T> Pair<T, T> getSwapped(Pair<T, T> pair) {
    return new Pair<T, T>(pair.getValue(), pair.getKey());
  }

  /*
  Classic bubble sort works by
  - Iterating over each element
  - For each other element, swap if out of order
  - Optimization: if each other element is already ordered, stop iterating

  For example, sorting in ascending order:
   3  1  4  2 -1  0  (index [0] is the pivot)
   4  1  4  2 -1  0  (4 > 3, swap values)
   1  4  3  2 -1  0  (4 > 1, swap values)
   1  3  4  2 -1  0  (index [1] is the pivot. 4 > 3, swap values)
   1  2  4  3 -1  0  (3 > 2, swap values)
   1  2  3  4 -1  0  ...
  -1  2  3  4  1  0
  -1  1  3  4  1  0
  -1  1  3  4  2  0
  -1  1  2  4  3  0
  -1  1  2  3  4  0
  -1  0  2  3  4  1
  -1  0  1  3  4  2
  -1  0  1  2  4  3
  -1  0  1  2  3  4
   */
  public static <T extends Comparable<T>> void sort(List<T> array, ORDER... sortType) {
    if (array.isEmpty()) return;
    int N = array.size();
    for (int i = 0; i < N; ++i) {
      Boolean isOrdered = true;
      for (int j = 0; j < N; ++j) {
        if (sortType.length == 0 || ORDER.ASCENDING == sortType[0] ? array.get(j).compareTo(array.get(i)) > 0: array.get(j).compareTo(array.get(i)) < 0) {
          Pair<T, T> swapped = getSwapped(new Pair<T, T>(array.get(j), array.get(i)));
          array.set(j, swapped.getKey());
          array.set(i, swapped.getValue());
          isOrdered = false;
        }
      }
      if (isOrdered)
        break;
    }
  }
}
