//**************************************************************************************************
// CLASS: Roster
//
// AUTHOR
// Kevin R. Burger (burgerk@asu.edu)
// Computer Science & Engineering Program
// Fulton Schools of Engineering
// Arizona State University, Tempe, AZ 85287-8809
// (c) Kevin R. Burger 2014-2021
//**************************************************************************************************
package proj3;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * The Roster class encapsulates an ArrayList<Student> object named mStudentList
 * which stores the information for each student that was read from
 * "gradebook.txt" when the app started.
 */
public class Roster {

	/**
	 * Declare mStudentList as ArrayList<Student>
	 */
	private ArrayList<Student> mStudentList;

	/**
	 * Roster()
	 *
	 * PSEUDOCODE: method Roster() -- Note that mStudentList was already declared so
	 * we do not need to declare it here. -- What we need to do here is create the
	 * ArrayList<Student> object that mStudentList -- will refer to. create an
	 * ArrayList<Student> object and then pass that object as the argument to
	 * setStudentList() to make mStudentList refer to the ArrayList end Roster
	 */
	public Roster() {
		mStudentList = new ArrayList<Student>();
		setStudentList(mStudentList);
	}

	/**
	 * addStudent()
	 *
	 * Adds pstudent to the ArrayList
	 *
	 * PSEUDOCODE: method addStudent(pStudent : Student) : void add (will append)
	 * pStudent to mStudentList end method
	 */
	public void addStudent(Student pStudent) {
		mStudentList.add(pStudent);
	}

	/**
	 * getStudent()
	 *
	 * Searches mStudentList for a Student with pLastName.
	 *
	 * PSEUDOCODE: method getStudent(pLastName : String) : Student -- Get the index
	 * of the student in the student list index = call
	 * Searcher.search(getStudentList(), pLastName) -- If index is -1 then no
	 * student with that last name could be found so we return -- null. Otherwise,
	 * we get the Student from the student list at the index and return -- the
	 * Student. if index == -1 then return null else return the Student object in
	 * getStudentList() at index 'index' end getStudent
	 */
	public Student getStudent(String pLastName) {
		int index = Searcher.search(mStudentList, pLastName);
		if (index == -1) {
			return null;
		} else {
			return mStudentList.get(index);
		}
	}

	/**
	 * getStudentList()
	 *
	 * Accessor method for mStudentList.
	 *
	 * Note: it is extremely sleazy to provide public access to the entire private
	 * student list (mStudentList) in this way because it gives whoever calls this
	 * method the ability to modify any Student in the roster. It would be better to
	 * have the Roster class implement an iterator that would permit other objects
	 * to iterate over the elements of the list, but in an effort to keep the
	 * project as simple as possible, I am taking the sleazy route.
	 *
	 * If you are so inclinded, by all means, implement the iterator.
	 */
	public ArrayList<Student> getStudentList() {
		return mStudentList;
	}

	/**
	 * setStudentList()
	 *
	 * Mutator method for mStudentList.
	 */
	private void setStudentList(ArrayList<Student> pStudentList) {
		mStudentList = pStudentList;
	}

	/**
	 * sortRoster() Called to sort the roster by last name.
	 *
	 * PSEUDOCODE: method sortRoster() -- Note that all of the methods in Sorter are
	 * class methods, so we call the sort() -- method on the class Sorter. call
	 * Sorter.sort() passing the list of students returned from getStudentList() end
	 * sortRoster
	 */
	public void sortRoster() {
		Sorter.sort(getStudentList());
	}

	/**
	 * Returns a String representation of this Roster. toString() methods are very
	 * handy for debugging because given access to a Roster object, say named
	 * roster, then you can print the entire roster in one statement:
	 * System.out.println(roster);
	 */
	@Override
	public String toString() {
		String result = "";
		for (Student student : getStudentList()) {
			result += student + "\n";
		}
		return result;
	}

	static class Searcher {

		/**
		 *
		 * Search Function & method to find the student by last name
		 */

		public static int search(ArrayList<Student> pList, String pKey) {
			System.out.printf("Searching for %s...\n", pKey); // can remove print
			int low = 0;
			int high = pList.size() - 1;
			long start = System.nanoTime(); // can remove time
			while (low <= high) {
				int mid = (low + high) / 2;
				if (pKey.trim().equalsIgnoreCase(pList.get(mid).getLastName().trim())) {
					System.out.printf("Search Time: %d ns\n", (System.nanoTime() - start)); // can remove print
					return mid;
				}
				if (pKey.compareTo(pList.get(mid).getLastName()) < 0) {
					high = mid - 1;
//					System.out.println("Update Max: " + high); // can remove print
				} else {
					low = mid + 1;
//					System.out.println("Update Min: " + low); // can remove print
				}
//				System.out.printf("RANGE : [%d - %d]\n", low, high); // can remove print
			}
			return -1;
		}
	}

	static class Sorter {
		private static ArrayList<Student> temp;

//		// implement and sort Students Last name

		private static int partition(ArrayList<Student> pList, int pFromIdx, int pToIdx) {
			Student pivot = pList.get(pToIdx);
			int i = pFromIdx - 1;

			for (int j = pFromIdx; j <= pToIdx - 1; j++) {
				if (pList.get(j).compareTo(pivot) < 0) {
					i++;
					swap(pList, i, j);
				}
			}
			swap(pList, i + 1, pToIdx);
			return (i + 1);
		}

		// the quicksort algorithm picks a specific element as a pivot and partitions
		// the array around it
		private static void quickSort(ArrayList<Student> pList, int pFromIdx, int pToIdx) {
			if (pFromIdx < pToIdx) {
				int pi = partition(pList, pFromIdx, pToIdx);

				quickSort(pList, pFromIdx, pi - 1);
				quickSort(pList, pi + 1, pToIdx);
			}
		}

		private static void quickSort2(ArrayList<Student> pList, int pFromIdx, int pToIdx) {
			int i = pToIdx; // start at N-1
			int j = pFromIdx; // start at beginning
			int mid = pToIdx + (pFromIdx - pToIdx) / 2;
			Student pivot = pList.get(mid);
//			System.out.printf("PIVOT --> %d-[%s]\n", mid, pivot.getLastName()); // can remove print
			boolean flag = j <= i;

			while (flag) {
				while (pList.get(i).compareTo(pivot) > 0) {
//					System.out.printf("i = [%d] = %s\n", i, pList.get(i).getLastName()); // can remove print
					i--;
				}
				while (pList.get(j).compareTo(pivot) < 0) {
//					System.out.printf("j = [%d] = %s\n", j, pList.get(j).getLastName()); // can remove print
					j++;
				}

				if (j <= i) {
					swap(pList, j, i);
					i--;
					j++;
					break;
				}
			}
			flag = pFromIdx < i;
//			System.out.println("pFromIdx < i --> " + flag);	// can remove print
			if (flag) {
				quickSort2(pList, pFromIdx, i);
			}
			flag = j < pToIdx;
//			System.out.println("j < pToIdx --> " + flag);	// can remove print
			if (flag) {
				quickSort2(pList, j, pToIdx);
			}

		}

		public static void sort(ArrayList<Student> pList) {
			temp = pList;
			System.out.println("INITAL ARRAY");
			IntStream.range(0,pList.size()).forEach(i -> {
				System.out.printf("[%d] = %s, ",i, pList.get(i).getLastName());
			});
			System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------");
//			temp.stream().forEach(e -> System.out.println(e.toString())); // can remove print
			long start = System.nanoTime(); // can remove time
//			quickSort2(temp, 0, temp.size() - 1);
			quickSort(temp, 0, temp.size() - 1);
			System.out.printf("Sort Time: %d ns\n", (System.nanoTime() - start)); // can remove print
//			System.out.println("SORTED!"); // can remove print
//			pList.stream().forEach(e -> System.out.println(e.toString())); // can remove print
		}

		private static void swap(ArrayList<Student> pList, int pIdx1, int pIdx2) {
			System.out.printf("BEFORE: [%d] = %s <--> [%d] = %s\n",pIdx1, pList.get(pIdx1).getLastName(), pIdx2, pList.get(pIdx2).getLastName()); // can remove print
			Student temp = pList.get(pIdx1);
			pList.set(pIdx1, pList.get(pIdx2));
			pList.set(pIdx2, temp);
			System.out.printf("After: [%d] = %s <--> [%d] = %s\n",pIdx1, pList.get(pIdx1).getLastName(), pIdx2, pList.get(pIdx2).getLastName()); // can remove print
//			pList.stream().forEach(e -> {
//				System.out.printf("%s, ", e.getLastName());
//			});
			IntStream.range(0,pList.size()).forEach(i -> {
				System.out.printf("[%d] = %s, ",i, pList.get(i).getLastName());
			});
			System.out.println();
		}

	}

}
