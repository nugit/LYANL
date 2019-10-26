module BloomFilter (empty, insert, member, BloomFilter.null) where

import System.Random (random, StdGen)
import qualified Data.BitSet as BitSet (BitSet, empty, insert, member, null, size)
import Data.Hashable (hashWithSalt)

-- n: expected number of items in the Bloom Filter
-- p: acceptable probability of a false positive
-- m: max number of bits the Bloom Filter will use
-- k: number of hashing functions

data BloomFilter = BloomFilter {
  n :: Int, p :: Float, bitset :: BitSet.BitSet Int, m :: Int, k :: Int, hashSeed :: Int
} deriving (Eq, Show)

getMaxSize :: Int -> Float -> Int
getMaxSize n p = abs $ ceiling $ fromIntegral n * (log p) / (log (1 / (log 2 ^ 2)))

getNumberOfHashFunctions :: Int -> Int -> Int
getNumberOfHashFunctions n m = round $ fromIntegral (m `div` n) * log 2

empty :: Int -> Float -> StdGen -> BloomFilter
empty n p randGen =
  let m = getMaxSize n p
      k = getNumberOfHashFunctions n m
      hashSeed = fst $ random randGen
  in BloomFilter n p BitSet.empty m k hashSeed

null :: BloomFilter -> Bool
null = BitSet.null . bitset

getHashes :: Show a => BloomFilter -> a -> [Int]
getHashes bloomFilter elem =
  let str = show elem
      seed = hashSeed bloomFilter
  in map ((flip hashWithSalt str) . (seed +)) [1..(k bloomFilter)]

insert :: Show a => a -> BloomFilter -> BloomFilter
insert elem bloomFilter =
  let hashes = getHashes bloomFilter elem
      newBitSet = foldl (flip BitSet.insert) (bitset bloomFilter) hashes
  in bloomFilter { bitset = newBitSet }

member :: Show a => a -> BloomFilter -> Bool
member elem bloomFilter =
  let hashes = getHashes bloomFilter elem
      bs = bitset bloomFilter
  in all (flip BitSet.member bs) hashes
