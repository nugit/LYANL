class TrieNode(object):
    def __init__(self, char = ""):
        self.char = char
        self.next_chars = {} # dictionary of TrieNodes

class Trie(object):
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.root = TrieNode()


    def insert(self, word):
        """
        Inserts a word into the trie.
        :type word: str
        :rtype: void
        """
        ptr = self.root
        index = 0
        while index < len(word) and word[index] in ptr.next_chars:
            ptr = ptr.next_chars[word[index]]
            index += 1

        while index < len(word):
            ptr.next_chars[word[index]] = TrieNode(word[index])
            ptr = ptr.next_chars[word[index]]
            index += 1

        ptr.next_chars['#'] = None


    def search(self, word):
        """
        Returns if the word is in the trie.
        :type word: str
        :rtype: bool
        """
        ptr = self.root
        for index, val in enumerate(word):
            if val in ptr.next_chars:
                ptr = ptr.next_chars[val]
            else:
                return False

        return '#' in ptr.next_chars


    def startsWith(self, prefix):
        """
        Returns if there is any word in the trie that starts with the given prefix.
        :type prefix: str
        :rtype: bool
        """
        ptr = self.root
        for index, val in enumerate(prefix):
            if val in ptr.next_chars:
                ptr = ptr.next_chars[val]
            else:
                return False

        return True

word = "electricity"
obj = Trie()
obj.insert(word)
print(obj.search(word))

prefix = "electric"
print(obj.startsWith(prefix))
