use std::collections::hash_map::DefaultHasher;
use std::hash::{Hash, Hasher};


#[derive(Debug)]
struct BloomFilter {
    bits: Vec<bool>,
    hash_functions: usize,
}


impl BloomFilter {

    /// Creates a new BloomFilter which can contain up to size items
    /// with a false positive rate less than max_false_positive.
    fn new(size: usize, max_false_positive: f64) -> Self {
        let bit_count = (-1.44 * size as f64 * max_false_positive.log2()).ceil() as usize;
        let hash_functions = -max_false_positive.log2().ceil() as usize;
        let bits = vec![false; bit_count];

        Self { bits, hash_functions }
    }

    /// Inserts the item into the BloomFilter
    fn insert<T: Hash>(&mut self, item: &T) {
        for i in 0..self.hash_functions {
            let ix = self.get_index(item, i);
            self.bits[ix] = true;
        }
    }

    /// Returns true if the BloomFilter may contain the item,
    /// or false if it does not contain the item.
    fn contains<T: Hash>(&self, item: &T) -> bool {
        (0..self.hash_functions).all(|i| self.bits[self.get_index(item, i)])
    }

    /// Calculates the index into the bit vector for a given
    /// item and seed. The given seed should be unique
    /// for each hash function.
    fn get_index<T: Hash>(&self, item: &T, seed: usize) -> usize {
        let length = self.bits.len() as u64;
        let mut hasher = DefaultHasher::new();

        hasher.write_usize(seed);
        item.hash(&mut hasher);

        (hasher.finish() % length) as usize
    }

    /// Returns an approximation of the number of items
    /// inserted into the BloomFilter. 
    fn approx_item_count(&self) -> usize {
        let bits_set = self.bits.iter().filter(|&b| *b).count() as f64;
        let length = self.bits.len() as f64;
        
       ((-length / self.hash_functions as f64) * (1.0 - bits_set / length).ln()).round() as usize
    }
}



fn main() {
    let mut filter = BloomFilter::new(10_000, 0.0001);

    println!("Bits: {}, Hash functions: {}", filter.bits.len(), filter.hash_functions);

    let elem1 = 312312;
    let elem2 = "asdsd";
    let elem3 = (23, "cool");
    let elem4 = (22, "cool");

    filter.insert(&elem1);
    filter.insert(&elem2);
    filter.insert(&elem3);

    assert!(filter.contains(&elem1));
    assert!(filter.contains(&elem2));
    assert!(filter.contains(&elem3));

    // Could potentially fail, but very unlikely
    assert!(!filter.contains(&12));
    assert!(!filter.contains(&elem4));

    assert_eq!(filter.approx_item_count(), 3);
}
