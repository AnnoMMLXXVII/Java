package com.ListADT;

public class ArrayIndexedList<T> extends ArrayUnsortedList<T> implements IndexedListInterface<T> {


    public ArrayIndexedList() {
        super();
    }

    public ArrayIndexedList(int origCap) {
        super(origCap);
    }

    @Override
    public void add(int index, T ele) {
        if ((index < 0) || (index > size())) {
            throw new IndexOutOfBoundsException("Illegal index of" + index + " passed to ArrayIndexedList add method.\n");
        }
        if (numElements == list.length) {
            enlarge();
        }

        for (int i = numElements; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = ele;
        numElements++;
    }

    @Override
    public T set(int index, T ele) {
        if ((index < 0) || (index > size())) {
            throw new IndexOutOfBoundsException("Illegal index of" + index + " passed to ArrayIndexedList add method.\n");
        }
        T hold = list[index];
        list[index] = ele;
        return hold;
    }

    public T get(int index) {
        if ((index < 0) || (index > size())) {
            throw new IndexOutOfBoundsException("Illegal index of" + index + " passed to ArrayIndexedList add method.\n");
        }
        return list[index];
    }

    @Override
    public int indexOf(T ele) {
        find(ele);
        if (found) {
            return location;
        } else {
            return -1;
        }
    }

    @Override
    public T remove(int index) {
        if ((index < 0) || (index > size())) {
            throw new IndexOutOfBoundsException("Illegal index of" + index + " passed to ArrayIndexedList add method.\n");
        }
        T hold = list[index];
        for (int i = index; i < (numElements - 1); i++) {
            list[i] = list[i + 1];
        }
        list[numElements - 1] = null;
        numElements--;
        return hold;
    }

    public String toString() {
        String listString = "List: \n";
        for (int i = 0; i < numElements; i++) {
            listString = listString + "[" + i + "] " + list[i] + "\n";
        }
        return listString;
    }

}
