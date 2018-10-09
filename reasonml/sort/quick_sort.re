let rec quickSort = (list: list(int)): list(int) => {
  switch(list) {
  | [] => []
  | [a] => [a]
  | _ =>
    let [pivot, ...rest] = list;
    let left = List.filter(item => item <= pivot, rest);
    let right = List.filter(item => item > pivot, rest);
    List.concat([quickSort(left), [pivot], quickSort(right)]);
  };
};
