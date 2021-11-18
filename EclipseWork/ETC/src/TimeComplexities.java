import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeComplexities {

	private static Map<String, Integer> mappedComplexities = new HashMap<>();
	private static String[] complexities = { "O(nLog2(n))", "O(n^2)", "O(n)", "O(Log2(n))", "O(n^2Log2(n))", "O(1)",
			"O(n^3)", "O(n^n)", "O(2^n)", "O(Log2(Log2(n)))" };

	public static void main(String... args) {
		new TimeComplexities();
	}

	public TimeComplexities() {
		initializeComplexities();
		List<Complexity> results = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j <= i; j++) {
//				long n = (long) Math.pow(10, i);
//			System.out.printf("%s\n",
//					new Complexity(complexities[j], mapCalculations(mappedComplexities.get(complexities[j]), 999)));
				System.out.printf("%s", i);
			}
			System.out.println();
		}
	}

	private void initializeComplexities() {
		mappedComplexities.put("O(nLog2(n))", 1);
		mappedComplexities.put("O(n^2)", 2);
		mappedComplexities.put("O(n)", 3);
		mappedComplexities.put("O(Log2(n))", 4);
		mappedComplexities.put("O(n^2Log2(n))", 5);
		mappedComplexities.put("O(1)", 6);
		mappedComplexities.put("O(n^3)", 7);
		mappedComplexities.put("O(n^n)", 8);
		mappedComplexities.put("O(2^n)", 9);
		mappedComplexities.put("O(Log2(Log2(n)))", 10);
	}

	private long mapCalculations(int temp, long N) {
		switch (temp) {
		case 1:
			return OnLog2n(N);
		case 2:
			return On2(N);
		case 3:
			return On(N);
		case 4:
			return OLog2n(N);
		case 5:
			return On2Log2n(N);
		case 6:
			return O1();
		case 7:
			return On3(N);
		case 8:
			return OnN(N);
		case 9:
			return O2n(N);
		case 10:
			return Olog2log2n(N);
		default:
			return -1;
		}

	}

	private long OnLog2n(long n) {
		return (Math.multiplyExact(n, log2(n)));
	}

	private long On2(long n) {
		return (long) Math.pow(n, 2);
	}

	private long On(long n) {
		return n;
	}

	private long OLog2n(long n) {
		return log2(n);
	}

	private long On2Log2n(long n) {
		return (long) Math.multiplyExact((long) Math.pow(n, 2), log2(n));
	}

	private long O1() {
		return 1;
	}

	private long On3(long n) {
		return (long) Math.pow(n, 3);
	}

	private long OnN(long n) {
		return BigInteger.valueOf(((long) Math.pow(n, n))).longValue();
	}

	private long O2n(long n) {
		return (long) Math.pow(2, n);
	}

	private long Olog2log2n(long n) {
		return log2(log2(n));
	}

	private long log2(long n) {
		return (long) (Math.log(n) / Math.log(2));
	}
}

class Complexity implements Comparable<Complexity> {
	private String name;
	private long N;

	public Complexity(String name, long N) {
		this.name = name;
		this.N = N;
	}

	public String getName() {
		return name;
	}

	public long getN() {
		return N;
	}

	public String toString() {
		return String.format("%15s%32s", name, N);
	}

	@Override
	public int compareTo(Complexity o) {
		return (int) (this.getN() - o.getN());
	}

}
