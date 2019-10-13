
let head = (str) => String.get(str, 0);

let tail = (str) => String.sub(str, 1, String.length(str) - 1);

let of_char = (char) => String.make(1, char);