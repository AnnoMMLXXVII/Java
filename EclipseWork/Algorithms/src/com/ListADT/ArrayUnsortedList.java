package com.ListADT;

public class ArrayUnsortedList<T> {
    protected final int DECAP = 100;
    protected int origCap;
    protected T[] list;
    protected int numElements = 0;
    protected int currentPos, location;
    // location --> indeicates location of element if found

    protected boolean found; // true if element found. otherwise false

    @SuppressWarnings("unchecked")
    public ArrayUnsortedList() {
        list = (T[]) new Object[DECAP];
        origCap = DECAP;
    }

    @SuppressWarnings("unchecked")
    public ArrayUnsortedList(int origCap) {
        list = (T[]) new Object[DECAP];
        this.origCap = origCap;
    }

    protected void enlarge() {
        // increments the cpaacity of the list by an amount
        // equals to the original capacity

        // Creates a larger array
        @SuppressWarnings("unchecked")
        T[] larger = (T[]) new Object[list.length + origCap];

        // copy contents of the array
        for (int i = 0; i < numElements; i++) {
            larger[i] = list[i];
        }

        // resassign list reference;
        list = larger;
    }

    protected void find(T target) {
        location = 0;
        found = false;
        while (location < numElements) {
            if (list[location].equals(target)) {
                found = true;
                return;
            } else {
                location++;
            }
        }
    }

    public void add(T ele) {
        if (numElements == list.length) {
            enlarge();
        }
        list[numElements] = ele;
        numElements++;
    }

    public boolean remove(T element) {
        find(element);
        if (found) {
            list[location] = list[numElements - 1];
            list[numElements - 1] = null;

            numElements--;
        }
        return found;
    }

    public int size() {
        return numElements;
    }

    public boolean contains(T ele) {
        find(ele);
        return found;
    }

    public T get(T ele) {
        find(ele);
        if (found) {
            return list[location];
        } else {
            return null;
        }
    }

    public String toString() {
        String listString = "List:\n";
        for (int i = 0; i < numElements; i++) {
            listString = listString + " " + list[i] + "\n";
        }
        return listString;
    }

    public void reset() {
        currentPos = 0;
    }

    public T getNext() {
        T next = list[currentPos];
        if (currentPos == (numElements - 1)) {
            currentPos = 0;
        } else {
            currentPos++;
        }
        return next;
    }
}
