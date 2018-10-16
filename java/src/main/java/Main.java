import search.BinarySearch;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] intArray = {31,1,2,3,5,99,7,8,9,20,33,6,10};
        try {
            System.out.println(binarySearch.recursiveSearch(intArray, 31));
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}