package com.question.hashtable_linearprobing;

// hash.java
//demonstrates hash table with linear probing
//to run this program: C:>java HashTableApp
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

////////////////////////////////////////////////////////////////
class DataItem<T> { // (could have more data)
	private int iData; // data item (key)
	private String iStrData;
	private T data;
	// --------------------------------------------------------------

	public DataItem(int ii) // constructor
	{
		iData = ii;
	}

	public DataItem(String lastName) {
		iStrData = lastName;
	}

	public DataItem(T data) {
		this.data = data;
	}

	// --------------------------------------------------------------
	public int getKey() {
		return iData;
	}

	public String getStrKey() {
		return iStrData;
	}

	public T getDataKey() {
		return data;
	}
	// --------------------------------------------------------------
} // end class DataItem
////////////////////////////////////////////////////////////////

class HashTable {
	private DataItem[] hashArray; // array holds hash table
	private int arraySize;
	private DataItem nonItem; // for deleted items
	// -------------------------------------------------------------

	public HashTable(int size) // constructor
	{
		arraySize = size;
		hashArray = new DataItem[arraySize];
//		nonItem = new DataItem(-1); // deleted item key is -1
		nonItem = new DataItem(""); // delete item key is -1;
	}

	// -------------------------------------------------------------
	public void displayTable() {
		System.out.print("Table: ");
		for (int j = 0; j < arraySize; j++) {
			if (hashArray[j] != null)
				System.out.printf("%d = {%d:%s} ", j, hashFunc(hashArray[j].getStrKey()), hashArray[j].getStrKey());
			else
				System.out.print("** ");
		}
		System.out.println("");
	}

	// -------------------------------------------------------------
	public int hashFunc(int key) {
		return key % arraySize; // hash function
	}

	public int hashFunc(String key) {
		return key.toLowerCase().compareTo("aaaaa".toLowerCase()) % arraySize; // hash function
	}

	// -------------------------------------------------------------
//	// insert a DataItem
//	public void insert(DataItem<Integer> item) {
//		// (assumes table not full)
//		int key = item.getKey(); // extract key
//		int hashVal = hashFunc(key); // hash the key
//										// until empty cell or -1,
//		while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
//			++hashVal; // go to next cell
//			hashVal %= arraySize; // wraparound if necessary
//		}
//		hashArray[hashVal] = item; // insert item
//	} // end insert()

	// -------------------------------------------------------------
	// insert a DataItem
	public void insert(DataItem<String> item) {
		// (assumes table not full)
		String key = item.getStrKey(); // extract key
		int hashVal = hashFunc(key); // hash the key
										// until empty cell or -1,
		while (hashArray[hashVal] != null && !hashArray[hashVal].getStrKey().equals("-1")) {
			++hashVal; // go to next cell
			hashVal %= arraySize; // wraparound if necessary
		}
		hashArray[hashVal] = item; // insert item
	} // end insert()

	// delete a DataItem
	public DataItem delete(int key) {
		int hashVal = hashFunc(key); // hash the key

		while (hashArray[hashVal] != null) // until empty cell,
		{ // found the key?
			if (hashArray[hashVal].getKey() == key) {
				DataItem temp = hashArray[hashVal]; // save item
				hashArray[hashVal] = nonItem; // delete item
				return temp; // return item
			}
			++hashVal; // go to next cell
			hashVal %= arraySize; // wraparound if necessary
		}
		return null; // can't find item
	} // end delete()

	// -------------------------------------------------------------
	// delete a DataItem
	public DataItem delete(String key) {
		int hashVal = hashFunc(key); // hash the key

		while (hashArray[hashVal] != null) // until empty cell,
		{ // found the key?
			if (hashArray[hashVal].getStrKey().equals(key)) {
				DataItem temp = hashArray[hashVal]; // save item
				hashArray[hashVal] = nonItem; // delete item
				return temp; // return item
			}
			++hashVal; // go to next cell
			hashVal %= arraySize; // wraparound if necessary
		}
		return null; // can't find item
	} // end delete()
		// -------------------------------------------------------------

	// find item with key
	public DataItem find(int key) {
		int hashVal = hashFunc(key); // hash the key
		return hashArray[hashVal];
//		while (hashArray[hashVal] != null) // until empty cell,
//		{ // found the key?
//			if (hashArray[hashVal].getKey() == key)
//				return hashArray[hashVal]; // yes, return item
//			++hashVal; // go to next cell
//			hashVal %= arraySize; // wraparound if necessary
//		}
//		return null; // can't find item
	}
	// -------------------------------------------------------------

	public DataItem find(String key) {
		int hashVal = hashFunc(key); // hash the key
//		return hashArray[hashVal];
		System.out.println("HashVal --> " + hashVal);
//
		long start = System.nanoTime();
		while (hashArray[hashVal] != null && hashVal < arraySize) // until empty cell,
		{ // found the key?
			if (hashArray[hashVal].getStrKey().equalsIgnoreCase(key)) {
				System.out.printf("%s ms -->\t ", System.nanoTime() - start);
				return hashArray[hashVal]; // yes, return item
			}
			++hashVal; // go to next cell
			hashVal %= arraySize; // wraparound if necessary
		}
		return null; // can't find item
	}
	// -------------------------------------------------------------
} // end class HashTable
////////////////////////////////////////////////////////////////

class HashTableApp {
	public long time;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		DataItem<String> aDataItem;
		int size, n, keysPerCell;
		String aKey = generateStringsWithSizeN(5);
		// get sizes
		System.out.print("Enter size of hash table: ");
		size = getInt();
		System.out.print("Enter initial number of items: ");
		n = getInt();
		keysPerCell = 10;
		// make table
		HashTable theHashTable = new HashTable(size);

		for (int j = 0; j < n; j++) // insert data
		{
			aDataItem = new DataItem<>(aKey);
			theHashTable.insert(aDataItem);
			aKey = generateStringsWithSizeN(5);
		}

		while (true) // interact with user
		{
			System.out.print("Enter first letter of ");
			System.out.print("show, insert, delete, or find: ");
			char choice = getChar();
			switch (choice) {
			case 's':
				theHashTable.displayTable();
				break;
			case 'i':
				System.out.print("Enter key value to insert: ");
				aKey = getString();
				aDataItem = new DataItem<>(aKey);
				theHashTable.insert(aDataItem);
				break;
			case 'd':
				System.out.print("Enter key value to delete: ");
				aKey = getString();
				theHashTable.delete(aKey);
				break;
			case 'f':
				System.out.print("Enter key value to find: ");
				aKey = getString();
				aDataItem = theHashTable.find(aKey);
				if (aDataItem != null) {
					System.out.println("Found " + aKey);
				} else {
					System.out.println("Could not find " + aKey);
				}
				break;
			default:
				System.out.print("Invalid entry\n");
			} // end switch
		} // end while
	} // end main()
		// --------------------------------------------------------------

	public static String generateStringsWithSizeN(int n) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = n;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}

		return buffer.toString();
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	// --------------------------------------------------------------
	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	// -------------------------------------------------------------
	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
	// --------------------------------------------------------------
} // end class HashTableApp
////////////////////////////////////////////////////////////////
