package search;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BinarySearchTest {
    @Test
    public void assertReturnIndex() throws IOException {
        BinarySearch binarySearch = new BinarySearch();
        int[] intArray = {31,1,2,3,5,99,7,8,9,20,101,0,4};
        int index20 = binarySearch.recursiveSearch(intArray, 20);
        Assert.assertEquals(9, index20);
        int index0 = binarySearch.recursiveSearch(intArray, 0);
        Assert.assertEquals(0, index0);
        int index101 = binarySearch.recursiveSearch(intArray, 101);
        Assert.assertEquals(12, index101);
    }
    @Test(expected=IOException.class)
    public void assertThrowException() throws IOException{
        BinarySearch binarySearch = new BinarySearch();
        int[] intArray = {31,1,2,3,5,99,7,8,9,20,101,0,4};
        try {
            int index = binarySearch.recursiveSearch(intArray, 111);
        } catch(IOException e) {
            throw new IOException("Not in index.");
        }
    }
}