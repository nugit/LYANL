type stack('a) = list('a);

let make = () => [];

let push = (s: stack('a), e: 'a) => [e, ...s]

let pop = (s: stack('a)) => {
  switch s {
    | [_, ...tl] => [...tl]
    | _ => [];
  }
}

let top = (s: stack('a)) => {
  switch s {
    | [] => None;
    | [hd, ...tl] => Some(hd);
  }
}

let empty = (s: stack('a)) => {
  switch s {
  | [] => true
  | _ => false
  }
}