enum SortOrder; // forward declaration

// sequence: STL container that is ordered
// order: how you want the container to be sorted by
template<class sequence>
void bubble_sort(sequence& seq, SortOrder order=SortOrder::ASCENDING);

#include "bubble_sort.cpp" // possible design choice to hide the implementation of template imelpementation
