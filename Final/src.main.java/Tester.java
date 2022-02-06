public class Tester {
	public static void main(String... args) {
		House h = new House("Cabin", 200);
		System.out.println(h.toString());
		h.upgrade();
		System.out.println(h.toString());
		h.upgrade();
		System.out.println(h.toString());
		try {
			Thread thread = new Thread();
			thread.sleep(1000);
		}catch(InterruptedException e) {
			e.getLocalizedMessage();
		}
		h.upgrade();
		System.out.println(h.toString());
		System.out.println("-------------------");
		try {
			Thread thread = new Thread();
			thread.sleep(1000);
		}catch(InterruptedException e) {
			e.getLocalizedMessage();
		}
		House h1 = new House(null, 200);
		h1.upgrade();
		System.out.println(h1.toString());

//		Map.loadHouse();
//		Map.upgradeHouses(1, 2);

//		Scrabs.normalize(new int[][] { { 1, 2, 3 }, { 3, 4, 5 } });
//		Scrabs.scrabble(new String[] { "HI", "BEN" });
//		Scrabs.scrabble(new String[] { "java" });

	}
}
