

public class UseLinkedList {

	private static void printlList(LinkedList<String> l) {
		System.out.print("LinkedList: | ");

		int counter = 0;
		String val = l.get(counter);
		while (val != null) {
			System.out.print(val + " | ");
			counter++;
			val = l.get(counter);
		}

		System.out.println();
	}

	private static void test1() {

		System.out.println(" ------------- Test 1 -------------");
		LinkedList<String> l = new LinkedList<String>();

		l.addFirst("mustStay4");
		l.addFirst("mustStay3");
		l.addFirst("removeMe");
		l.addFirst("mustStay2");
		l.addFirst("mustStay1");
		l.addFirst("removeMe");

		printlList(l);

		l.noFirstOrSimilar();

		printlList(l);
	}

	private static void test2() {

		System.out.println();
		System.out.println(" ------------- Test 2  -------------");
		LinkedList<String> l = new LinkedList<String>();

		l.addFirst("thisOne");
		l.addFirst("keep3");
		l.addFirst("keep2");
		l.addFirst("keep1");
		l.addFirst("thisOne");

		printlList(l);

		l.noFirstOrSimilar();

		printlList(l);
	}

	private static void test3() {

		System.out.println();
		System.out.println(" ------------- Test 3 -------------");
		LinkedList<String> l = new LinkedList<String>();

		l.addFirst("stays3");
		l.addFirst("stays2");
		l.addFirst("stays1");
		l.addFirst("tobeDeleted");
		l.addFirst("tobeDeleted");
		l.addFirst("tobeDeleted");

		printlList(l);

		l.noFirstOrSimilar();

		printlList(l);
	}

	public static void main(String[] args) {

		System.out.println("====================================================");
		System.out.println();
		test1();
		System.out.println("====================================================");
		test2();
		System.out.println("====================================================");
		test3();
		System.out.println("====================================================");
	}

}
