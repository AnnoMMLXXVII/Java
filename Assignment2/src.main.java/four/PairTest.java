package four;

public class PairTest {

	private Pair<Integer, String> p1;
	private Pair<String, Integer> p2;
	private Pair<String, Pair<Double, Double>> p3;
	private Pair<Pair<Integer, String>, Pair<String, Integer>> p4;

	public static void main(String[] args) {
		new PairTest();
	}

	public PairTest() {
		p1 = new Pair<>(9999, "ABCD");
		p2 = new Pair<>("EGFH", 0000);
		p3 = new Pair<>("ALPHA", new Pair<>(0.0, 9.9));
		p4 = new Pair<>(p1, p2);
		System.err.println("-------------------------------");
		System.out.printf("P1 : %s\nP2 : %s\n", p1.toString(), p2.toString());
		p1.setKey(1111);
		p1.setValue("ZYXW");
		System.err.println("Updated P1 Key-Values {111,ZXYW}");
		System.out.printf("P1 : %s\nP2 : %s\n", p1.toString(), p2.toString());
		p2.setKey("TANGO");
		p2.setValue(8888);
		System.err.println("Updated P2 Key-Values {TANGO, 8888}");
		System.out.printf("P1 : %s\nP2 : %s\n", p1.toString(), p2.toString());
		System.err.println("------------------------------");
		System.err.println("NEW PAIR WITH PAIR OBJECT AS THE VALUE");
		System.out.printf("P1 : %s\nP2 : %s\nP3 : %s\n", p1.toString(), p2.toString(), p3.toString());
		System.err.println("------------------------------");
		System.err.println("NEW PAIR WITH PAIR OBJECT WITH EXISTING PAIRS FOR KEY-VALUE");
		System.out.printf("P1 : %s\nP2 : %s\nP3 : %s\nP4 : %s\n", p1.toString(), p2.toString(), p3.toString(),
				p4.toString());
		Pair<Integer, String> temp = new Pair<Integer, String>(p4.getKey().getKey(), p4.getKey().getValue());
		temp.setKey(777777);
		p4.setKey(temp);
		Pair<String, Integer> tmp = new Pair<String, Integer>(p4.getValue().getKey(), p4.getValue().getValue());
		tmp.setValue(77777777);
		p4.setValue(tmp);
		System.err.println("------------------------------");
		System.err.println("Updated P4 Key-Values -- Did not update the original P1 and P2 values");
		System.out.printf("P1 : %s\nP2 : %s\nP3 : %s\nP4 : %s\n", p1.toString(), p2.toString(), p3.toString(),
				p4.toString());
	}

}
