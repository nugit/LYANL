module type Trie = {
  type node = {
    isWord: bool,
    children: Hashtbl.t(char, node),
  };
  let insert: (node, string) => node;
  let make: option(list(string)) => node;
  let has: (node, string) => bool;
  let getWords: node => list(string);
  let getWordsWithPrefix: (node, string) => list(string);
  let isEmpty: node => bool;
};

type node = {
  isWord: bool,
  children: Hashtbl.t(char, node),
};

let rec insert = (trie: node, word) => {
  switch(String.length(word)) {
  | 0 => trie
  | 1 =>
    let head = StrUtils.head(word);
    switch(Hashtbl.mem(trie.children, head)) {
    | true =>
      let node = Hashtbl.find(trie.children, head);
      Hashtbl.replace(trie.children, head, { ...node, isWord: true });
      trie;
    | false =>
      Hashtbl.add(trie.children, head, { isWord: true, children: Hashtbl.create(26) });
      trie;
    };
  | _ =>
    let head = StrUtils.head(word);
    let tail = StrUtils.tail(word);
    switch(Hashtbl.mem(trie.children, head)) {
    | true =>
      let node = Hashtbl.find(trie.children, head);
      let newNode = insert(node, tail);
      Hashtbl.replace(trie.children, head, newNode);
      trie;
    | false =>
      let node = { isWord: false, children: Hashtbl.create(26) };
      let newNode = insert(node, tail);
      Hashtbl.add(trie.children, head, newNode);
      trie;
    };
  };
};

let make = (~words=[], ()): node => {
  let base = {
    isWord: false,
    children: Hashtbl.create(26),
  };
  List.fold_left(insert, base, words);
};

let rec has = (node: node, word: string): bool => {
  switch(Hashtbl.find(node.children, StrUtils.head(word))) {
  | subNode =>
    switch(String.length(word) == 1) {
    | false => has(subNode, StrUtils.tail(word));
    | true => true
    };
  | exception Not_found => false
  };
};

let rec getWords = (node, ~soFar="", ()) => {
  Hashtbl.fold((char, subNode, acc) => {
    let currentWord = soFar ++ StrUtils.of_char(char);
    let subWords = getWords(subNode, ~soFar=currentWord, ());
    let allWords = subNode.isWord ? [currentWord, ...subWords] : subWords;
    List.append(allWords, acc);
  }, node.children, []);
};

let rec getWordsWithPrefix = (node, prefix, ~remaining=?, ()) => {
  let rest = switch(remaining) {
  | None => prefix
  | Some(str) => str
  };
  switch(String.length(rest) === 0) {
  | true when String.length(prefix) === 0 => getWords(node, ~soFar=prefix, ())
  | true when node.isWord == true => [prefix, ...getWords(node, ~soFar=prefix, ())]
  | true => getWords(node, ~soFar=prefix, ())
  | false =>
    switch(Hashtbl.find(node.children, StrUtils.head(rest))) {
    | subNode => getWordsWithPrefix(subNode, prefix, ~remaining=StrUtils.tail(rest), ())
    | exception Not_found => []
    };
  };
};

let isEmpty = (node) => Hashtbl.length(node.children) == 0;

