package com.ListADT;

public class ArraySortedList<T> extends ArrayUnsortedList<T> implements ListInterface<T> {

    public ArraySortedList() {
        super();
    }

    public ArraySortedList(int origCap) {
        super(origCap);
    }

    public void add(T ele) {
        T listElement;
        int location = 0;
        if (numElements == list.length) {
            enlarge();
        }
        while (location < numElements) {
            listElement = (T) list[location];
            if (((Comparable<T>) listElement).compareTo(ele) < 0) {
                location++;
            } else {
                break;
            }
        }
        for (int index = numElements; index > location; index--) {
            list[index] = list[index - 1];
        }
        list[location] = ele;
        numElements++;
    }

    public boolean remove(T ele) {
        find(ele);
        if (found) {
            for (int i = location; i <= numElements - 2; i++) {
                list[i] = list[i + 1];
            }
            list[numElements - 1] = null;
            numElements--;
        }
        return found;
    }

    public void find(T target) {
        int first = 0, last = (numElements - 1), compareResult;
        @SuppressWarnings("unchecked")
        Comparable<T> targetElement = (Comparable<T>) target;
        found = false;
        while (first <= last) {
            location = (first + last) / 2;
            compareResult = targetElement.compareTo(list[location]);
            if (compareResult == 0) {
                found = true;
                break;
            } else if (compareResult < 0) {
                last = location - 1;
            } else {
                first = location + 1;
            }
        }
    }
}
