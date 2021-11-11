import java.util.Random;

public class EmployeeArrayHours {

	private int[][] employeeHours;
	
	public static void main(String... args) {
		new EmployeeArrayHours(8);
	}

	public EmployeeArrayHours(int employeeCount) {
		employeeHours = new int[employeeCount][7];
		employeeHours = randomizeData(employeeHours);
		printArray(employeeHours);
		employeeHours = sortDoubleArray(employeeCount);
		System.out.println();
		printArray(employeeHours);
	}

	private Employee[] sort(Employee[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j].getTotalHours() > arr[j + 1].getTotalHours()) {
					Employee temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}

	private int[][] sortDoubleArray(int employeeCount) {
		Employee[] employees = new Employee[employeeCount];
		for (int i = 0; i < employees.length; i++) {
			Employee temp = new Employee(employeeHours[i]);
			temp.setTotalHours(getSum(employeeHours[i]));
			employees[i] = (temp);
		}
		sort(employees);
		for (int i = 0; i < employeeHours.length; i++) {
			employeeHours[i] = employees[i].getHours();
		}
		return employeeHours;
	}

	private int[][] randomizeData(int[][] arr) {
		Random r = new Random();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = r.nextInt(8) + 0;
			}
		}
		return arr;
	}

	private void printArray(int[][] arr) {
		System.out.printf("\t   |_Su__M___Tu__W___Th__F___Sa___| Sum\n");
		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			System.out.printf("Employee %d | ", i);
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + "   ");
				sum += arr[i][j];
				if (j == arr[i].length - 1) {
					System.out.printf(" | %s ", sum);
				}
			}
			System.out.println();
		}
	}

	private int getSum(int[] hours) {
		int sum = 0;
		int i = 0;
		while (i < hours.length) {
			sum += hours[i];
			i++;
		}
		return sum;
	}

}

class Employee {
	private int[] hours;
	private int totalHours;

	public Employee(int[] hours) {
		this.hours = hours;
	}

	public int[] getHours() {
		return hours;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}
}
