// Array is assumed to be sorted for the purposes of binary search. Return -1 if search item is not present
function binarySearch(array, searchItem) {
    return recursiveSearch(0, array.length - 1, array, searchItem);
}

function recursiveSearch(low, high, array, searchItem) {
    if (high >= low) {
        const mid = Math.floor((high + low) / 2);
        if (array[mid] !== searchItem) {
            if (array[mid] > searchItem) return recursiveSearch(low, mid - 1, array, searchItem);
            return recursiveSearch(mid + 1, high, array, searchItem);
        } 
        return mid;
    }
    return -1;
}