// Array is assumed to be sorted for the purposes of binary search. Return -1 if search item is not present
const binarySearch = (array, searchItem) => (
    recursiveSearch(0, array.length - 1, array, searchItem)
);

const recursiveSearch = (low, high, array, searchItem) => {
    if (high < low) return -1;
    const mid = Math.floor((high + low) / 2);
    if (array[mid] === searchItem) return mid;
    return array[mid] > searchItem
        ? recursiveSearch(low, mid - 1, array, searchItem)
        : recursiveSearch(mid + 1, high, array, searchItem);
};