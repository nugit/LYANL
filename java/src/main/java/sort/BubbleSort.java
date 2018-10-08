package sort;

import java.util.List;
import javafx.util.Pair;
import sort.Options.ORDER;

public class BubbleSort {
  static <T> Pair<T, T> getSwapped(Pair<T, T> pair) {
    return new Pair<T, T>(pair.getValue(), pair.getKey());
  }

  /*
   3  1  4  2 -1  0
   4  1  4  2 -1  0
   1  4  3  2 -1  0
   1  3  4  2 -1  0
   1  2  4  3 -1  0
   1  2  3  4 -1  0
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
      for (int j = 0; j < N; ++j) {
        if (ORDER.ASCENDING == sortType[0] ? array.get(j).compareTo(array.get(i)) > 0: array.get(j).compareTo(array.get(i)) < 0) {
          Pair<T, T> swapped = getSwapped(new Pair<T, T>(array.get(j), array.get(i)));
          array.set(j, swapped.getKey());
          array.set(i, swapped.getValue());
        }
      }
    }



  }
}
