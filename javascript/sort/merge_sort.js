const { prepend, drop, head, lte, length, take } = require('ramda');

const mergeSort = (comparisonPredicate, arr) => {
  if (length(arr) < 2) return arr;
  const pivot = Math.round((length(arr) - 1) / 2);
  return merge(
    comparisonPredicate,
    mergeSort(comparisonPredicate, take(pivot, arr)),
    mergeSort(comparisonPredicate, drop(pivot, arr)),
  );
};

// Merges two sorted array into a new sorted array
// :: (a -> a -> Bool) -> Array(Int) -> Array(Int) -> Array(Int)
const merge = (comparisonPredicate, first, second) => {
  if (length(first) === 0) return second;
  if (length(second) === 0) return first;
  const hf = head(first);
  const hs = head(second);
  return comparisonPredicate(hf, hs)
    ? prepend(hf, merge(drop(1, first), second))
    : prepend(hs, merge(first, drop(1, second)));
};
