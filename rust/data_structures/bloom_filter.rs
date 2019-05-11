use std::collections::hash_map::RandomState;
use std::hash::{Hash, Hasher, BuildHasher};


#[derive(Debug)]
pub struct BloomFilter {
    /// The internal bit vector
    bits: Vec<bool>,
    /// The RandomState which is used to build
    /// the hash functions. Is initialized
    /// randomly when creating a new BloomFilter.
    /// Could make it generic over this type to
    /// support any type of hash function.
    hash_state: RandomState,
    /// The number of hash functions
    number_of_hashes: usize
}


impl BloomFilter {

    /// Creates a new BloomFilter which can contain up to size items
    /// with a false positive rate less than max_false_positive.
    pub fn new(size: usize, max_false_positive: f64) -> Self {
        let bit_count = (-1.44 * size as f64 * max_false_positive.log2()).ceil() as usize;
        let number_of_hashes = -max_false_positive.log2().ceil() as usize;
        let bits = vec![false; bit_count];
        let hash_state = RandomState::new();

        Self { bits, hash_state, number_of_hashes }
    }

    /// Inserts the item into the BloomFilter
    pub fn insert<T: Hash>(&mut self, item: &T) {
        for i in 0..self.number_of_hashes {
            let ix = self.get_index(item, i);
            self.bits[ix] = true;
        }
    }

    /// Returns true if the BloomFilter may contain the item,
    /// or false if it does not contain the item.
    pub fn contains<T: Hash>(&self, item: &T) -> bool {
        (0..self.number_of_hashes).all(|i| self.bits[self.get_index(item, i)])
    }

    /// Calculates the index into the bit vector for a given
    /// item and seed. The given seed should be unique
    /// for each hash function.
    fn get_index<T: Hash>(&self, item: &T, seed: usize) -> usize {
        let length = self.bits.len() as u64;
        let mut hasher = self.hash_state.build_hasher();

        hasher.write_usize(seed);
        item.hash(&mut hasher);

        (hasher.finish() % length) as usize
    }

    /// Returns an approximation of the number of items
    /// inserted into the BloomFilter.
    /// Reference: https://en.wikipedia.org/wiki/Bloom_filter#Approximating_the_number_of_items_in_a_Bloom_filter
    pub fn approx_item_count(&self) -> usize {
        let bits_set = self.bits.iter().filter(|&b| *b).count() as f64;
        let length = self.bits.len() as f64;
        
       ((-length / self.number_of_hashes as f64) * (1.0 - bits_set / length).ln()).round() as usize
    }
}



fn main() {
    let mut filter = BloomFilter::new(10_000, 0.0001);
    println!("Bits: {}, Hash functions: {}", filter.bits.len(), filter.number_of_hashes);

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
