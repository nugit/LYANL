/// A Disjoint-set data structure (also called a union–find data structure or merge–find set) 
/// is a data structure that tracks a set of elements partitioned into a number of 
/// disjoint (non-overlapping) subsets. It provides near-constant-time operations 
/// (bounded by the inverse Ackermann function) to add new sets, to merge existing sets,
/// and to determine whether elements are in the same set.
/// - https://en.wikipedia.org/wiki/Disjoint-set_data_structure
use std::cmp::Ordering;

#[derive(Debug)]
struct Node {
    id: usize,
    parent_id: usize,
    rank: usize,
}

impl Node {
    /// Returns a new Node with itself as parent and rank zero
    fn new(id: usize) -> Node {
        Node {
            id: id,
            parent_id: id,
            rank: 0,
        }
    }
}

#[derive(Debug)]
struct DisjointSet {
    elems: Vec<Node>
}

impl DisjointSet {
    /// Create a new DisjointSet with fixed number of elements
    /// 0 to size - 1, each belonging to a seperate subset
    fn new(size: usize) -> DisjointSet {
        DisjointSet {
            elems: (0..size).map(Node::new).collect()
        }
    }

    /// Return the root id of the element with given id,
    /// also known as the representative node of that particular set
    fn find(&mut self, id: usize) -> usize {
        if id == self.parent(id) {
            id
        } else {
            let parent_id = self.parent(id);
            let root_id = self.find(parent_id);
            // Path compression: repoint node directly to root
            self.set_parent(id, root_id);

            root_id
        }
    }

    /// Union the sets of the two given elements (by rank)
    fn union(&mut self, x: usize, y: usize) {
        let root_x = self.find(x);
        let root_y = self.find(y);

        if root_x != root_y {
            match self.rank(root_y).cmp(&self.rank(root_x)) {
                Ordering::Less => self.set_parent(root_y, root_x),
                Ordering::Greater => self.set_parent(root_x, root_y),
                Ordering::Equal => {
                    self.set_parent(root_x, root_y);
                    self.elems[root_y].rank += 1
                }
            }
        }
    }

    /// Returns true if the two elements belong to the same set,
    /// which is true if and only if they have same root
    fn equiv(&mut self, x: usize, y: usize) -> bool {
        self.find(x) == self.find(y)
    }

    // Returns rank of element
    fn rank(&self, id: usize) -> usize {
        self.elems[id].rank
    }

    // Returns parent of element
    fn parent(&self, id: usize) -> usize {
        self.elems[id].parent_id
    }

    // Sets parent of element id to parent_id
    fn set_parent(&mut self, id: usize, parent_id: usize) {
        self.elems[id].parent_id = parent_id;
    }
}


fn main() {
    // Take the thing for a test ride
    let mut set = DisjointSet::new(4);
    println!("{:?}", set);

    assert!(!set.equiv(0, 1));
    assert!(!set.equiv(1, 2));

    set.union(0, 3);
    set.union(3, 2);

    assert!(set.equiv(0, 3));
    assert!(set.equiv(0, 2));
    assert!(!set.equiv(1, 2));

    set.union(1, 2);
    assert!(set.equiv(0, 2));
    assert!(set.equiv(1, 2));

    println!("{:?}", set);
}
