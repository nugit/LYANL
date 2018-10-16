type treeNode = {
  value: int,
  left: option(treeNode),
  right: option(treeNode),
};

let rec insert = (tree: option(treeNode), newValue: int): option(treeNode) => {
  switch(tree) {
  | None => Some({ left: None, right: None, value: newValue })
  | Some(node) => switch(node.value == newValue) {
    | true => Some(node)
    | false => switch(node.value > newValue) {
      | true => Some({ ...node, right: insert(node.right, newValue) })
      | false => Some({ ...node, left: insert(node.left, newValue) })
      };
  }
  };
};

let create = List.fold_left(insert, None);

let rec size = (tree: option(treeNode)): int => {
  switch(tree) {
  | None => 0
  | Some(node) => 1 + size(node.left) + size(node.right)
  };
};

let rec sum = (tree: option(treeNode)): int => {
  switch(tree) {
  | None => 0
  | Some(node) => node.value + sum(node.left) + sum(node.right)
  };
};

let rec toArray = (tree: option(treeNode)): list(int) => {
  switch(tree) {
  | None => []
  | Some(node) => [node.value, ...List.rev_append(toArray(node.left), toArray(node.right))]
  };
};

let overrideRoot = (tree: treeNode): option(treeNode) => {
  switch(tree.left) {
  | Some(node) => Some({ value: node.value, left: node.left, right: tree.right })
  | None => switch(tree.right) {
    | Some(node) => Some({ value: node.value, left: tree.left, right: node.right })
    | None => None
    };
  };
};

let rec remove = (target: int, tree: option(treeNode)): option(treeNode) => {
  switch(tree) {
  | None => None
  | Some(node) => switch(node.value == target) {
    | false => Some({ ...node, left: remove(target, node.left), right: remove(target, node.right)})
    | true => overrideRoot(node)
    };
  };
};

