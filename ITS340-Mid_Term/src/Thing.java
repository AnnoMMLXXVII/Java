
public class Thing {

	protected double[] arr;

	public Thing() {
		arr = new double[10];
	}

	public Thing(int size) {
		arr = new double[size];
	}

	public double[] getArray() {
		return arr;
	}

	public boolean add(double element) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0.0) {
				arr[i] = element;
				return true;
			}
		}
		return false;
	}

	public void reset() {
		arr = new double[arr.length];
	}

	public void reverseHalf(boolean left) {
		int halfSize = arr.length / 2;
		if (left) {
			for (int i = 0, j = halfSize - 1; i < j; i++, j--) {
				double temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		} else {
			for (int i = halfSize, j = arr.length - 1; i < j; i++, j--) {
				double temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
	}
	
	private void swap(double a, double b) {
		double temp = a;
		a = b;
		b = temp;
	}

	public void reverse() {
		for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
			double temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	public static void main(String args[]) {
		Thing obj = new Thing(10);
		for (int i = 1; i < 11; i++) {
			obj.add(i);
		}

		System.out.print("[ ");
		for (int i = 0; i < obj.getArray().length; i++) {
			System.out.printf("%.2f , ",obj.getArray()[i]);
		}
		System.out.print(" ] ");
		System.out.println();
		obj.reverse(true);
		System.out.print("[ ");
		for (int i = 0; i < obj.getArray().length; i++) {
			System.out.printf("%.2f , ",obj.getArray()[i]);
		}
		System.out.print(" ] ");
		System.out.println();
		obj.reverse(false);
		System.out.print("[ ");
		for (int i = 0; i < obj.getArray().length; i++) {
			System.out.printf("%.2f , ",obj.getArray()[i]);
		}
		System.out.print(" ] ");

	}

}
