public class HashLinQuad {
    private Integer[] table;        //hash table
    private int size;           //current number of elements
    private int capacity;        //capacity of the hash table

    public HashLinQuad(int _size) {
        table = new Integer[_size];
        size = 0;
        capacity = _size;
        for (int i = 0; i < _size; i++) {
            table[i] = null;
        }
    }

    /*
     * int != Integer
     *
     * primitive data types
     * 		boolean, double, etc
     * Classes || Objects
     * 		Boolean, Double, etc
     */
    public int addLin(Integer obj) {
        int count = 0, hInd = hashCode(obj, capacity);

        if (hInd < 0) {
            hInd += table.length;
//        	System.out.println(" hInd < 0");
        }
//        System.out.println(hInd);
        while (table[hInd] != null) {
            if (count == capacity || size == capacity) {
                return -1;
            }

            hInd = (hInd + 1) % capacity;
            System.out.println("\tHIND : " + hInd);
            count++;
        }

        table[hInd] = obj;
        return count;
    }


    private static int hashCode(int value, int maxLength) {
        int hash = value % maxLength;
        if (hash < 0) {
            hash += maxLength;
        }
        return hash;
    }


}