open Jest;
open StrUtils;

describe("head", () => {
  open Expect;

  test("should return first char", () => {
    let r = head("abc");
    expect(r) |>  toBe('a')
  })
});

describe("tail", () => {
    open Expect;

    test("should return tail string", () => {
      let r = tail("abc");
      expect(r) |>  toBe("bc")
    })
  }
);

describe("of_char", () => {
    open Expect;

    test("should return tail string", () => {
      let r = of_char('a');
      expect(r) |>  toBe("a")
    })
  }
);