# Calling for Pull Requests for these algorithms 📣📣📣

### Sort Algorithms:
- Selection Sort
- Bubble Sort
- Insertion Sort
- Merge Sort
- Quick Sort
- Bucket Sort
- Heap Sort
- Counting Sort

### Search Algorithms:
- Depth First Search
- Breadth First Search
- Binary Search

### Data Structures:
- Stack
  - push(x): Pushes element x onto stack.
  - pop(): Removes the element on top of the stack.
  - top(): Gets the top element.
  - empty(): Returns whether the stack is empty.

- Queue
  - push(x): Pushes element x to the back of queue.
  - pop(): Removes the element from in front of queue.
  - peek(): Gets the front element.
  - empty(): Returns whether the queue is empty.

- Singly Linked List
  - insertAtEnd(x): Inserts given element at the end of the linked list
  - insertAtHead(x): Inserts given element at the start/head of the linked list
  - delete(x): Deletes given element from the linked list
  - deleteAtHead(x): Deletes first element of the linked list
  - search(x): Returns the given element from a linked list
  - isEmpty: Returns true if the linked list is empty

- Doubly Linked List
  - insertAtEnd(x): Inserts given element at the end of the linked list
  - insertAtHead(x): Inserts given element at the start/head of the linked list
  - delete(x): Deletes given element from the linked list
  - deleteAtHead(x): Deletes first element of the linked list
  - search(x): Returns the given element from a linked list
  - isEmpty: Returns true if the linked list is empty

- Priority Queue
  - insert(item, priority): Inserts an item with given priority.
  - getHighestPriority(): Returns the highest priority item.
  - deleteHighestPriority(): Removes the highest priority item.

- Balanced Binary Search Tree (AVL/ Red Black Tree)
  - search(x): Returns the given element in the tree
  - max(): Returns maximum element of the tree
  - min(): Returns minimum element of the tree
  - insert(x): Inserts given element into the tree
  - delete(x): Deletes given element of the tree

- Trie
  - insert(word) - Inserts a word into the trie.
  - search(word) - Returns if the word is in the trie.
  - startsWith(prefix) - Returns if there is any word in the trie that starts with the given prefix.
  - *Related Leetcode Question: https://leetcode.com/problems/implement-trie-prefix-tree*

- Least Recently Used Cache
  - get(key) - Gets the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
  - put(key, value) - Sets or inserts the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
  - *Related Leetcode Question: https://leetcode.com/problems/lru-cache/*

- Bloom Filter
  - append(item) - Adds one item to the bloom filter
  - concatenate(list of items) - Adds multiple items to the bloom filter
  - contains(item) - Returns whether the bloom filter may contain an item, or definitely does not

