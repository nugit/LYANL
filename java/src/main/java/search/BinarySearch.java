package search;

import java.io.IOException;
import java.util.Arrays;

public class BinarySearch {
    public <T extends Comparable> int recursiveSearch(T[] typeArray, T searchItem) throws IOException {
        boolean present = Arrays.stream(typeArray).anyMatch(searchItem::equals);
        if (!present) {
            throw new IOException("Item not present in array");
        } else {
            int low = 0;
            int high = typeArray.length - 1;
            Arrays.sort(typeArray);
            return recursiveSearch(low, high, typeArray, searchItem);
        }
    }
    private <T extends Comparable> int recursiveSearch(int low, int high, T[] typeArray, T searchItem) {
        int mid = (high + low) / 2;
        if (typeArray[mid].compareTo(searchItem) != 0) {
            if (typeArray[mid].compareTo(searchItem) > 0) {
                return recursiveSearch(low, mid - 1, typeArray, searchItem);
            } else {
                return recursiveSearch(mid + 1, high, typeArray, searchItem);
            }
        } else {
            return mid;
        }
    }
}