package sort;

import java.util.List;
import javafx.util.Pair;
import sort.Options.ORDER;

public class BubbleSort {
  static <T> Pair<T, T> getSwapped(Pair<T, T> pair) {
    return new Pair<T, T>(pair.getValue(), pair.getKey());
  }

  /*
    https://en.wikipedia.org/wiki/Bubble_sort
    - Compare elements next to each other and swap if out of order
      (this creates the "bubbling" effect of the largest number swapping its way to the top)
    - Continue until no swaps are needed
    For example, sorting in ascending order:
     3  1  4  2 -1  0
     1  3  4  2 -1  0 (3 > 1, swapped)
     1  3  2  4 -1  0 (4 > 2, swapped)
     1  3  2 -1  4  0 (4 > -1, swapped)
     1  3  2 -1  0  4 (4 > 0, swapped)
     1  2  3 -1  0  4 (3 > 2, swapped)
     1  2 -1  3  0  4 (3 > -1, swapped)
     1  2 -1  0  3  4 ...
     1 -1  2  0  3  4
     1 -1  0  2  3  4
    -1  1  0  2  3  4
    -1  0  1  2  3  4
   */
  public static <T extends Comparable<T>> void sortClassic(List<T> array, ORDER... sortType) {
    if (array.isEmpty()) return;
    int N = array.size();
    Boolean isOrdered = false;
    while (!isOrdered) {
      isOrdered = true;
      for (int j = 1; j < N; ++j) {
        if (sortType.length == 0 || ORDER.ASCENDING == sortType[0] ? array.get(j-1).compareTo(array.get(j)) > 0: array.get(j-1).compareTo(array.get(j)) < 0) {
          Pair<T, T> swapped = getSwapped(new Pair<T, T>(array.get(j-1), array.get(j)));
          array.set(j-1, swapped.getKey());
          array.set(j, swapped.getValue());
          isOrdered = false;
        }
      }
      N -= 1; // Since each inner loop bubbles the Nth largest/smallest element to the top
    }
  }

  /*
  - Iterating over each element
  - For each other element, swap if out of order
  - Optimization: if no swaps are needed, we consider the list to be ordered and thus stop iterating
    For example, sorting in ascending order:
     3  1  4  2 -1  0  (index [0] is the pivot)
     4  1  3  2 -1  0  (4 > 3, swapped values)
     1  4  3  2 -1  0  (4 > 1, swapped values)
     1  3  4  2 -1  0  (index [1] is the pivot. 4 > 3, swapped values)
     1  2  4  3 -1  0  (3 > 2, swapped values)
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

    This variant of bubble sort is generally faster because it requires
    less swaps (at the expense of more iterations)
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
