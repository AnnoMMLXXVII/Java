package assignments;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static List<Student> students;
	private static String filePath = "studentdata.txt";
	private static Scanner scanner;

	public static void main(String[] args) throws Exception {
		students = new LinkedList<>();
		scanner = new Scanner(System.in);
		if (!readFile(filePath)) {
			throw new Exception("Error Reading the File: " + filePath);
		}

		String[] options = { "1- Display student marks", "2- Display student grades", "3- Add a new student",
				"4- Remove a student", "5- Exit" };

		displayMenu(options);
		while (selectMenuOption() != 5) {
			displayMenu(options);
		}

	}

	private static int selectMenuOption() {
		int option = -1;
		try {
			option = scanner.nextInt();
			if (option == 1) {
				displayReportByMarks();
			} else if (option == 2) {
				displayReportByGrades();
			} else if (option == 3) {
				System.out.println("Enter the following fields: ");
				System.out.println("Enter the Student's Id: ");
				addNewStudent();
			} else if (option == 4) {
				removeStudent();
			} else if (option == 5) {
				System.out.println("**********************");
				System.out.println("* THANK YOU GOOD BYE!*");
				System.out.println("**********************");
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("Please enter a number value between 1 and 5");
		}
		return option;
	}

	public static void displayMenu(String[] options) {
		for (String option : options) {
			System.out.println(option);
		}
		System.out.print("Welcome to the main menu, please select an option and press enter : ");
	}

	public static boolean readFile(String studentData) {
		try (Scanner scanner = new Scanner(new FileReader(new File(studentData)))) {
			while (scanner.hasNextLine()) {
				String[] words = scanner.nextLine().split(",");
				addStudent(Integer.parseInt(words[0]), words[1], words[2], Integer.parseInt(words[3]),
						Integer.parseInt(words[4]), Integer.parseInt(words[5]), Integer.parseInt(words[6]),
						Integer.parseInt(words[7]), Integer.parseInt(words[8]));
			}
		} catch (IOException e) {
			System.out.println("Failed to read file " + studentData);
			return false;
		}
		return true;
	}

	private static void addStudent(int id, String firstName, String lastName, int mathsMark1, int mathsMark2,
			int mathsMark3, int englishMark1, int englishMark2, int englishMark3) {
		AssignmentMarks math = new AssignmentMarks("Math", mathsMark1, mathsMark2, mathsMark3);
		AssignmentMarks english = new AssignmentMarks("English", englishMark1, englishMark2, englishMark3);
		Student student = new Student(id, firstName, lastName, math, english);
		((LinkedList<Student>) students).push(student);
	}

	private static void removeStudent() {
		if (students.size() == 0) {
			System.out.println("No more students left");
			return;
		}
		System.out.printf("%-2s | %-12s | %-12s |\n", "Id", "First Name", "Last Name");
		for (Student s : students) {
			System.out.printf("%-2s | %-12s | %-12s |\n", s.getId(), s.getFirstName(), s.getLastName());
		}
		System.out.println("Which Student do you want to Remove (Enter the Id): ");
		int id = scanner.nextInt();
		int idx = -1;
		String temp = "";
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getId() == id) {
				idx = i;
				temp = students.get(i).getFirstName() + " " + students.get(i).getLastName();
				break;
			}
		}
		students.remove(idx);
		System.out.println("Removed Student : " + temp);
	}

	private static void addNewStudent() {

		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the Student's first name: ");
		String firstName = scanner.nextLine();
		System.out.println("Enter the Student's last name: ");
		String lastName = scanner.nextLine();
		System.out.println("Enter the Student's Math mark 1: ");
		int mathsMark1 = scanner.nextInt();
		System.out.println("Enter the Student's Math mark 2: ");
		int mathsMark2 = scanner.nextInt();
		System.out.println("Enter the Student's Math mark 3: ");
		int mathsMark3 = scanner.nextInt();
		System.out.println("Enter the Student's English mark 1: ");
		int englishMark1 = scanner.nextInt();
		System.out.println("Enter the Student's English mark 2: ");
		int englishMark2 = scanner.nextInt();
		System.out.println("Enter the Student's English mark 3: ");
		int englishMark3 = scanner.nextInt();
		addStudent(id, firstName, lastName, mathsMark1, mathsMark2, mathsMark3, englishMark1, englishMark2,
				englishMark3);
	}

	private static void displayReportByMarks() {
		displayColumnHeaders();
		for (Student s : students) {
			System.out.printf(
					"%-24s%-8s" + "%-5s" + "%-5s" + "%-5s" + "%-8s" + "%-8s" + "%-5s" + "%-5s" + "%-5s" + "%-8s\n",
					s.getFirstName() + " " + s.getLastName(), "", s.getMathsMarks().getMark(1),
					s.getMathsMarks().getMark(2), s.getMathsMarks().getMark(3), s.getMathsMarks().getAverageMark(), "",
					s.getEnglishMarks().getMark(1), s.getEnglishMarks().getMark(2), s.getEnglishMarks().getMark(3),
					s.getEnglishMarks().getAverageMark());
		}
	}

	private static void displayReportByGrades() {
		displayColumnHeaders();
		for (Student s : students) {
			System.out.printf(
					"%-24s%-8s" + "%-5s" + "%-5s" + "%-5s" + "%-8s" + "%-8s" + "%-5s" + "%-5s" + "%-5s" + "%-8s\n",
					s.getFirstName() + " " + s.getLastName(), "", s.getMathsMarks().getGrade(1),
					s.getMathsMarks().getGrade(2), s.getMathsMarks().getGrade(3), s.getMathsMarks().getAverageGrade(),
					"", s.getEnglishMarks().getGrade(1), s.getEnglishMarks().getGrade(2),
					s.getEnglishMarks().getGrade(3), s.getEnglishMarks().getAverageGrade());
		}
	}

	private static void displayColumnHeaders() {
		System.out.printf("%-24s%5s" + "%5s%5s%5s" + "%8s" + "%8s" + "%5s%5s%5s" + "%8s\n", "Name", "Maths", "A1", "A2",
				"A3", "Grade", "English", "A1", "A2", "A3", "Grade");
		System.out.println("------------------------------------------------------------------------------------");
	}
}
