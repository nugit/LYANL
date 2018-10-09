package sort;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import sort.BubbleSort;

/*
  Run tests using Maven: mvn test -Dtest="sort.BubbleSortTest"
 */
public class BubbleSortTest {
  @Test
  public void testDefaultSortIsAscending() {
    List<Integer> array = Arrays.asList(3, 1, 4, 2, -1, 0);
    BubbleSort.sort(array);
    Assert.assertTrue(Arrays.asList(-1, 0, 1, 2, 3, 4).equals(array));

    array = Arrays.asList(3, 1, 4, 2, -1, 0);
    BubbleSort.sortClassic(array);
    Assert.assertTrue(Arrays.asList(-1, 0, 1, 2, 3, 4).equals(array));
  }

  @Test
  public void testSortAscending() {
    List<Integer> array = Arrays.asList(3, 1, 4, 2, -1, 0);
    BubbleSort.sort(array, Options.ORDER.ASCENDING);
    Assert.assertTrue(Arrays.asList(-1, 0, 1, 2, 3, 4).equals(array));

    array = Arrays.asList(3, 1, 4, 2, -1, 0);
    BubbleSort.sortClassic(array, Options.ORDER.ASCENDING);
    Assert.assertTrue(Arrays.asList(-1, 0, 1, 2, 3, 4).equals(array));
  }

  @Test
  public void testSortDescending() {
    List<Integer> array = Arrays.asList(3, 1, 4, 2, -1, 0);
    BubbleSort.sort(array, Options.ORDER.DESCENDING);
    Assert.assertTrue(Arrays.asList(4, 3, 2, 1, 0, -1).equals(array));

    array = Arrays.asList(3, 1, 4, 2, -1, 0);
    BubbleSort.sortClassic(array, Options.ORDER.DESCENDING);
    Assert.assertTrue(Arrays.asList(4, 3, 2, 1, 0, -1).equals(array));
  }
}