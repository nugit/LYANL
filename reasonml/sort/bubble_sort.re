type passResult = {
  seq: list(int),
  perfectPass: bool,
};

let rec bubblePass = (~seq: list(int), ~perfectPass: bool=true, ()): passResult =>  {
  switch (seq) {
  | [] => { seq: [], perfectPass }
  | [a] => { seq: [a], perfectPass }
  | [a, b] when a <= b => { seq: [a, b], perfectPass }
  | [a, b] => { seq: [b, a], perfectPass: false }
  | [a, b, ...rest] when a <= b =>
    let { seq as newSeq, perfectPass } = bubblePass(~seq = [b, ...rest], ~perfectPass = perfectPass, ());
    { seq: [a, ...newSeq], perfectPass };
  | [a, b, ...rest] =>
    let { seq as newSeq } = bubblePass(~seq = [a, ...rest], ~perfectPass = false, ());
    { seq: [b, ...newSeq], perfectPass: false };
  };
};

let rec bubbleSort = (seq: list(int)): list(int) => {
  let { seq as newSeq, perfectPass } = bubblePass(~seq = seq, ());
  perfectPass ? seq : bubbleSort(newSeq);
};
