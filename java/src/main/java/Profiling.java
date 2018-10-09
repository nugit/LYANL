import sort.*;
import java.util.*;
import sort.Options.ORDER;
import java.util.Random;

public class Profiling {
  /*
    Use this as the program's entry point, to experiment with Java, or
    Run profiling: mvn compile exec:java -Dexec.mainClass=Profiling -Dexec.args=""
   */
  public static void main(String[] args) {
    long start, elapsedTimeMillis;
    int sz = 10000;
    Random rand = new Random();
    List<Integer> array = new ArrayList<Integer>();
    for (int i = 0; i < sz; ++i) {
      Integer x = rand.nextInt(sz);
      array.add(x);
    }

    start = System.currentTimeMillis();
    BubbleSort.sortClassic(array, ORDER.DESCENDING);
    elapsedTimeMillis = System.currentTimeMillis() - start;
    System.out.println("BubbleSort for " + String.valueOf(array.size()) + " elements: " + elapsedTimeMillis);

    start = System.currentTimeMillis();
    BubbleSort.sort(array, ORDER.DESCENDING);
    elapsedTimeMillis = System.currentTimeMillis() - start;
    System.out.println("BubblySort for " + String.valueOf(array.size()) + " elements: " + elapsedTimeMillis);
  }
}