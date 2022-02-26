import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Module5 {
	private List<Integer> list = new LinkedList<>();
	private Random r = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		new Module5();
	}

	public Module5() {
		for (int i = 0; i < 25; i++) {
			list.add(0 + r.nextInt(9999999));
		}
		System.out.println(list.toString());
		Collections.sort(list, (e, v) -> e.compareTo(v));
		System.out.println(list.toString());
		Integer sum = sum(list);
		System.out.println("Sum: " + sum);
		System.out.println("Average: " + average(sum, list.size()));
	}

	public Integer sum(List<?> list) {
		AtomicInteger total = new AtomicInteger(0);
		list.forEach(e -> total.addAndGet((int) e));
		return total.get();
	}

	public Float average(Integer total, Integer divisor) {
		return (float) (Math.floorDiv(total, divisor));
	}

}
