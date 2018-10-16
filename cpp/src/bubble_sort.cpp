/*
  Example usage:
    #include "sort_order.hpp"
    #include "bubble_sort.hpp"

    list<short> ar = {3, 1, 4, 2, -1, 0};
    bubble_sort(ar);
    // ar: -1 0 1 2 3 4
    bubble_sort(ar, SortOrder::DESCENDING);
    // ar: 4 3 2 1 0 -1
*/
template<class sequence>
void bubble_sort(sequence& seq, SortOrder order) {
  int N = seq.size();
  if (N <= 1)
    return;

  bool isOrdered = false;
  while (!isOrdered) {
    isOrdered = true;
    // we cannot compare it < seq.end(), because this has to work for all containers,
    // eg. std::list::iterator does not support < operator
    for (auto it = next(seq.begin()); it != seq.end(); ++it) {
        auto prevIter = prev(it);
        if (order == ASCENDING? *prevIter > *it: *prevIter < *it) {
            std::swap(*prevIter, *it);
            isOrdered = false;
        }
     }
  }
}
