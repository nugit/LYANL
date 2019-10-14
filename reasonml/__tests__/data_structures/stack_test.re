open Jest;
open Stack;

describe("push", () => {
  open Expect;

  test("should add to an empty stack", () => {
    let r = push([], "abc");
    expect(r) |>  toEqual(["abc"])
  })

  test("should add to a non-empty stack", () => {
    let r = push([1, 2, 3], 4);
    expect(r) |>  toEqual([4, 1, 2, 3])
  })
});

describe("pop", () => {
  open Expect;

  test("should return remove top element", () => {
    let r = pop([1, 2, 3]);
    expect(r) |>  toEqual([2, 3])
  })

  test("should return do nothing for empty stack", () => {
    let r = pop([]);
    expect(r) |>  toEqual([])
  })
});

describe("top", () => {
  open Expect;

  test("should return top element", () => {
    let r = top([1, 2, 3]);
    expect(r) |>  toEqual(Some(1))
  })

  test("should return None for empty stack", () => {
    let r = top([]);
    expect(r) |>  toEqual(None)
  })
});

describe("empty", () => {
  open Expect;

  test("should return false when has elements", () => {
    let r = empty([1, 2, 3]);
    expect(r) |>  toEqual(false)
  })

  test("should return true when empty", () => {
    let r = empty([]);
    expect(r) |>  toEqual(true)
  })
});
