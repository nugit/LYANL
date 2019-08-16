class LinkedList {
  constructor(value = null, next = null) {
    this.value = value;
    this.next = next;
  }

  prepend = value => new LinkedList(value, this)

  prependMany = (...values) => (
    values.reduce((acc, value) => acc.prepend(value), this)
  )

  toArray = (soFar = []) => (
    this.value === null
      ? soFar
      : this.next.toArray([...soFar, this.value])
  )

  length = (soFar = 0) => (
    this.next === null
      ? soFar
      : this.next.length(soFar + 1)
  )

  map = unaryFn => (
    this.next === null
      ? this
      : new LinkedList(unaryFn(this.value), this.next.map(unaryFn))
  )

  filter = (predicate) => {
    if (this.next === null) return this;
    if (!predicate(this.value)) return this.next.filter(predicate);
    return new LinkedList(this.value, this.next.filter(predicate));
  }

  flatten = (tabbed = null) => {
    if (this.next === null && tabbed === null) return this;
    if (this.next === null) return tabbed.flatten();
    if (this.value instanceof LinkedList) return this.value.flatten(this.next);
    return new LinkedList(this.value, this.next.flatten(tabbed));
  }

  flatMap = fn => this.map(fn).flatten()

  take = (until, hops = 1) => {
    if (this.value === null) return this;
    if (hops >= until) return new LinkedList(this.value, new LinkedList());
    return new LinkedList(this.value, this.next.take(until, hops + 1));
  }

  drop = (from, hops = 0) => (
    (hops >= from || this.value === null)
      ? this
      : this.next.drop(from, hops + 1)
  )

  find = (predicate) => {
    if (this.next === null) return null;
    if (predicate(this.value)) return this.value;
    return this.next.find(predicate);
  }

  remove = (predicate) => {
    if (this.next === null) return null;
    if (predicate(this.value)) return this.next;
    return new LinkedList(this.value, this.next.remove(predicate));
  }
}

module.exports = LinkedList;
