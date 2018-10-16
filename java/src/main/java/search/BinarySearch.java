package search;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BinarySearch {
    public int recursiveSearch(int[] intArray, int searchInt) throws IOException {
        boolean present = IntStream.of(intArray).anyMatch(x -> x == searchInt);
        if (!present) {
            throw new IOException("Integer not present in array");
        } else {
            int low = 0;
            int high = intArray.length - 1;
            Arrays.sort(intArray);
            return recursiveSearch(low, high, intArray, searchInt);
        }
    }
    public int recursiveSearch(int low, int high, int[] intArray, int searchInt) {
        int mid = (high + low) / 2;
        if (intArray[mid] != searchInt) {
            if (searchInt < intArray[mid]) {
                return recursiveSearch(low, mid - 1, intArray, searchInt);
            } else {
                return recursiveSearch(mid + 1, high, intArray, searchInt);
            }
        } else {
            return mid;
        }
    }
}