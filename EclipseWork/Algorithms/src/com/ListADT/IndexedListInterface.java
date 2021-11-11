package com.ListADT;

public interface IndexedListInterface<T> extends ListInterface<T> {
    void add(int index, T ele);

    // Throws index out of bound exception if passed an index argument
    // such that index < 0 or index > size();
    // Other wise, adds element to list at position index; all current
    // elements at theat posistino or higher have 1 addded to their index;
    T set(int index, T ele);

    // Throws index out of bound exception if passed an index argument
    // such that index < 0 or index > size();
    // Other wise returns eleent on this list at position index
    int indexOf(T ele);
    // if this list contains an element e such that e.equals(element).
    // then retursn the index of the first such element.
    // other wise returns -1;

    T remove(int index);
    // throws INDEXOUTOFBOUNDEXCEPTION if passed an idnex argument
    // such that index < 0 or index >= size();
    // Otherwise, removes element on this list at postiion index and
    // returns the removed element; all current elements at position
    // higher than taht position have 1 subtracted from their index;
}
