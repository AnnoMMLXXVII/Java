package assignments.sample;

import java.util.Random;
import java.util.Scanner;

public class SampleFinal {

	private final int Y = 8;
	private final int X = 8;
	private String[][] pass;
	private String[][] fail;
	private String[][] students;
	private int passCounter = 1;
	private int failCounter = 1;
	private Random r = new Random(new Random().nextInt());
	private int highestGrade = 0;
	private String[] highestStudent;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new SampleFinal();

	}

	public SampleFinal() {
		runProgram();
	}

	private void runProgram() {
		students = new String[X][Y];
		passCounter = X;
		failCounter = X;
		pass = new String[passCounter][Y];
		fail = new String[failCounter][Y];

		getInformation(students);
		for (int i = 0; i < X; i++) {
			passOrFail(students[i][Y - 1], students[i]);
		}
		printOuptutMessage();
	}

	public void getInformation(String[][] students) {
		Scanner input = new Scanner(System.in);
		for (int x = 0; x < X; x++) {
//			System.out.println("Student " + (x + 1) + ": First name: ");
			students[x][0] = getSaltString(r.nextInt(15) + 0);
//					input.nextLine();

//			System.out.println("Student " + (x + 1) + ": Last name: ");
			students[x][1] = getSaltString(r.nextInt(10) + 0);
//					input.nextLine();

//			System.out.println("Student " + (x + 1) + ": Student ID: ");
			students[x][2] = r.nextInt(999) + 100 + "";
//					input.nextLine();

//			System.out.println("Student " + (x + 1) + ": Exam 1: ");
			students[x][3] = r.nextInt(100) + 25 + "";
			;
//					input.next();

			// invalidGradeChecker(input.nextInt());
//			System.out.println("Student " + (x + 1) + ": Exam 2: ");
			students[x][4] = r.nextInt(100) + 25 + "";
//					input.next();

//			System.out.println("Student " + (x + 1) + ": Exam 3: ");
			students[x][5] = r.nextInt(100) + 25 + "";
//					input.next();

//			System.out.println("Student " + (x + 1) + ": Exam 4: ");
			students[x][6] = r.nextInt(100) + 25 + "";
//					input.next();
			int average = ((Integer.parseInt(students[x][3]) + Integer.parseInt(students[x][4])
					+ Integer.parseInt(students[x][5]) + Integer.parseInt(students[x][6])) / 4);
			findHighestGrade(students[x], Integer.parseInt(students[x][3]), Integer.parseInt(students[x][4]),
					Integer.parseInt(students[x][5]), Integer.parseInt(students[x][6]));
			students[x][7] = getLetter(average);
		}
	}

	private void printOuptutMessage() {
		printPassHeader();
//		sort(getPass());
		printData(getPass());
		printFailHeader();
//		sort(getFail());
		printData(getFail());
		printHighestGradeHeader();
		printData(getHighestStudent());

	}

	private String invalidGradeChecker(String grade, Scanner input) {
		int temp = Integer.parseInt(grade);
		while (temp < 0 || temp > 100) {
			temp = input.nextInt();
		}
		return temp + "";
	}

	private String getLetter(int grades) {
		if ((grades > 89 && grades < 101) || grades > 100) {
			return "A";
		}
		if (grades > 79 && grades < 90) {
			return "B";
		}
		if (grades > 69 && grades < 80) {
			return "C";
		}
		if (grades > 59 && grades < 70) {
			return "D";
		} else {
			return "F";
		}

	}

	private void passOrFail(String letter, String[] student) {
		if (letter.equalsIgnoreCase("C") || letter.equalsIgnoreCase("B") || letter.equalsIgnoreCase("A")) {
			pass = updatePassList(student);
			setPass(pass);
		} else {
			fail = updateFailList(student);
			setFail(fail);
		}
	}

	private String[][] updatePassList(String[] student) {
		for (int i = 0; i < pass.length; i++) {
			if (isRowNull(pass[i])) {
				for (int j = 0; j < pass[i].length; j++) {
					pass[i][j] = student[j];
				}
				break;
			}
		}
		updatePassCounter();
		return pass;
	}

	public String[][] updateFailList(String[] student) {
		for (int i = 0; i < fail.length; i++) {
			if (isRowNull(fail[i])) {
				for (int j = 0; j < fail[i].length; j++) {
					fail[i][j] = student[j];
				}
				break;
			}
		}
		updateFailCounter();
		return fail;
	}

	private void printData(String[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < 1; j++) {
				if (isRowNull(data[i])) {
					break;
				}
				System.out.printf("%s, %-12s\t%1s\n", data[i][1].trim().isBlank() ? "(BLANK)" : data[i][1].trim(),
						data[i][0].trim().isBlank() ? "(BLANK)" : data[i][0].trim(), data[i][7].trim());
			}
		}
	}

	private void printData(String[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.printf("%s,", data[i]);
		}
		System.out.println();

		System.out.printf("%s,%-12s\t%1s\n", data[1].trim().isBlank() ? "(BLANK)" : data[1].trim(), data[0].trim(),
				data[7].trim());
	}

	private boolean isRowNull(String[] row) {
		if (row == null) {
			return true;
		}
		for (int i = 0; i < row.length; i++) {
			if (row[i] == null) {
				return true;
			}
		}
		return false;
	}

	private boolean isHighestGrade(int temp) {
		return temp > getHighestGrade();
	}

	private void findHighestGrade(String[] student, int... grades) {
		int total = 0; 
		for (int i = 0; i < grades.length; i++) {
			total = grades[i] + total;
		}
		if (isHighestGrade(total)) {
			setHighestGrade(total);
			storeHighestStudent(student);
		}
	}

	private void storeHighestStudent(String[] student) {
		this.highestStudent = student;
	}

	private String[] getHighestStudent() {
		return this.highestStudent;
	}

	/**
	 * @return the highestGrade
	 */
	private int getHighestGrade() {
		return highestGrade;
	}

	/**
	 * @param highestGrade the highestGrade to set
	 */
	private void setHighestGrade(int highestGrade) {
		this.highestGrade = highestGrade;
	}

	private void sort(String[][] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < 1; j++) {
				if (compareArr(arr[i], arr[i + 1]) > 0 && arr[i] != null) {
					// swap arr[j+1] and arr[j]
					String[] temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
		}
	}

	private int compareArr(String[] a, String[] b) {
		return a[1].compareTo(b[1]);
	}

	private void printPassHeader() {
		System.out.println("--------------");
		System.out.println("PASS");
		System.out.println("----");
	}

	private void printFailHeader() {
		System.out.println("FAIL");
		System.out.println("----");
	}

	private void printHighestGradeHeader() {
		System.out.println("HIGHEST GRADE");
		System.out.println("----");
	}

	private void updatePassCounter() {
		this.passCounter = this.passCounter + 1;
	}

	private void updateFailCounter() {
		this.failCounter = this.failCounter + 1;
	}

	protected String getSaltString(int n) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < n) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		return salt.toString();

	}

	/**
	 * @return the pass
	 */
	private String[][] getPass() {
		return pass;
	}

	/**
	 * @return the fail
	 */
	private String[][] getFail() {
		return fail;
	}

	/**
	 * @param pass the pass to set
	 */
	private void setPass(String[][] pass) {
		this.pass = pass;
	}

	/**
	 * @param fail the fail to set
	 */
	private void setFail(String[][] fail) {
		this.fail = fail;
	}

}
