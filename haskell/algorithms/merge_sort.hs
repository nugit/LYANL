module MergeSort (mergeSort) where

merge :: Ord a => (a -> a -> Bool) -> [a] -> [a] -> [a]
merge predicate xs [] = xs
merge predicate [] ys = ys
merge predicate xs ys
  | predicate (head xs) (head ys) = (head xs) : merge predicate (tail xs) ys
  | otherwise = (head ys) : merge predicate (tail ys) xs

mergeSort :: Ord a => (a -> a -> Bool) -> [a] -> [a]
mergeSort predicate xs =
  case xs of
    [] -> []
    all@(x:[]) -> all
    xs ->
      let pivot = (length xs) `div` 2
          leftSide = mergeSort predicate (take pivot xs)
          rightSide = mergeSort predicate (drop pivot xs)
      in merge predicate leftSide rightSide
