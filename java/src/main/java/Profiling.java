import sort.*;
import java.util.*;
import sort.Options.ORDER;


public class Profiling {
  public static void main(String[] args) {
    System.out.println("Profiling");

    List<Integer> array = Arrays.asList(3, 1, 4, 2, -1, 0);
    BubbleSort.sort(array, ORDER.ASCENDING);
    System.out.println(array);
  }
}