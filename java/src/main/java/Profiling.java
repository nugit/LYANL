import sort.*;
import java.util.*;
import sort.Options.ORDER;


public class Profiling {
  /*
    Use this as the program's entry point, to experiment with Java, or
    Run profiling: mvn compile exec:java -Dexec.mainClass=Profiling -Dexec.args=""
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    List<Integer> array = new ArrayList<Integer>();
    for (int i = 0; i < 1000000; ++i) {
      array.add(1000000-i);
    }
    BubbleSort.sort(array);

    long elapsedTimeMillis = System.currentTimeMillis()-start;
    System.out.println("Bubble sort for " + String.valueOf(array.size()) + " elements: " + elapsedTimeMillis);
  }
}