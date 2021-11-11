package com.ListADT;

public interface ListInterface<T> {
    int size();

    // returns size or number of elements in the list
    void add(T ele);

    // adds elements to the list
    boolean contains(T ele);

    // returns true if list contains an element e such that
    // e.equals(element); otherwise, return false
    boolean remove(T ele);

    // removes an element e from this list such that e.equals(elemtn)
    // and returns true; if no such element exists, returns false
    T get(T ele);

    // returns an elemtn e from this list such that e.eqauls(element);
    // if no, such element exists, returns null;
    String toString();

    // returns a nnicely formated stirng that represents this list
    void reset();

    // initalizes current position to an interation through this list
    // to the first element on this list
    T getNext();
    // preconditions:
    // 	--> List is !empty
    // 	--> List has been reset
    // 	--> list has not been modified since most recent reset
    // returns the element at the current position on this list.
    // if the current position is the last element. then it advances the value
    // of the current position to the first element: otherwise.
    // itadvances the value of the current position to the next element;


}
