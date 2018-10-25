package search;

import java.io.IOException;

public class BinarySearch {
    // Array is assumed to be sorted for the purposes of binary search. Return -1 if search item is not present
    public <T extends Comparable> int recursiveSearch(T[] typeArray, T searchItem) throws IOException {
        int low = 0;
        int high = typeArray.length - 1;
        return recursiveSearch(low, high, typeArray, searchItem);
    }
    private <T extends Comparable> int recursiveSearch(int low, int high, T[] typeArray, T searchItem) {
        if (high >= low) {
            int mid = (high + low) / 2;
            if (typeArray[mid].compareTo(searchItem) != 0) {
                if (typeArray[mid].compareTo(searchItem) > 0) return recursiveSearch(low, mid - 1, typeArray, searchItem);
                return recursiveSearch(mid + 1, high, typeArray, searchItem);
            } 
            return mid;
        }
        return -1;
    }
}