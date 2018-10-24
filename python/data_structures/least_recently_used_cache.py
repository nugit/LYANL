# Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

# get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
# put(key, value) - put or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least recently used item before inserting the new item.
# The LRUCache will be initialized with an integer corresponding to its capacity. Capacity indicates the maximum number of unique keys it can hold at a time.

# Definition of “least recently used” : An access to an item is defined as a get or a put operation of the item. “Least recently used” item is the one with the oldest access time.

#  NOTE: If you are using any global variables, make sure to clear them in the constructor.

#  Input :
#          capacity = 2
#          put(1, 10)
#          put(5, 12)
#          get(5)        returns 12
#          get(1)        returns 10
#          get(10)       returns -1
#          put(6, 14)    this pushes out key = 5 as LRU is full.
#          get(5)        returns -1


class LRU:
    def __init__(self,capacity):
        self.capacity = capacity
        self.cache = {}
        self.lru = []

    def reorder(self,key):
        self.lru.remove(key)
        self.lru.append(key)

    def get(self,key):
        if key in self.lru:
            self.reorder(key)
            return self.cache[key]
        else:
            return -1

    def put(self, key, value):
        def add(key, value):
            self.lru.append(key)
            self.cache[key] = value

        if key in self.lru:
            self.reorder(key)
            self.cache[key] = value
        elif len(self.lru) == self.capacity:
            k = self.lru.pop(0)
            del self.cache[k]
            add(key,value)
        else:
            add(key,value)

lru = LRU(2)
print         (lru.put(1, 10))
print         (lru.put(5, 12))
print         (lru.get(5))        #returns 12
print         (lru.get(1))        #returns 10
print         (lru.get(10))       #returns -1
print         (lru.put(6, 14))    #this pushes out key = 5 as LRU is full.
print         (lru.get(5))        #returns -1
