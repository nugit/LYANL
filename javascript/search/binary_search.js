// This function should be implemented, array is assumed to be sorted
function binarySearch(array, searchItem) {
    var present = array.indexOf(searchItem);
    if (present == -1) {
        throw "Item not present in array";
    } else {
        return recursiveSearch(0, array.length - 1, array, searchItem);
    }
}

function recursiveSearch(low, high, array, searchItem) {
    var mid = Math.floor((high + low) / 2);
    if (array[mid] != searchItem) {
        if (array[mid] > searchItem) return recursiveSearch(low, mid - 1, array, searchItem);
        return recursiveSearch(mid + 1, high, array, searchItem);
    } else {
        return mid;
    }
}