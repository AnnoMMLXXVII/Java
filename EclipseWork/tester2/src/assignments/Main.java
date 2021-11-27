package assignments;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	private Student student;
	private LinkedList<Student> students;
	private String filePath = "studentdata.txt";

	public static void main(String... args) {
		new Main();
	}

	public Main() {
		students = new LinkedList<>();
		readFile(filePath);
		displayColumnHeaders();
		for (Student s : students) {
			System.out.printf("%-15s%10s%4s%4s%4s%3s%3s\n", s.getFullName(), s.getMathsMarks().getGrade(1),
					s.getMathsMarks().getGrade(2), s.getMathsMarks().getGrade(3), s.getEnglishMarks().getGrade(1),
					s.getEnglishMarks().getGrade(2), s.getEnglishMarks().getGrade(3));
		}
	}

	public boolean readFile(String studentData) {
		try (Scanner scanner = new Scanner(new FileReader(new File(studentData)))) {
			while (scanner.hasNextLine()) {
				String[] words = scanner.nextLine().split(",");
				addStudent(Integer.parseInt(words[0]), words[1], words[2], Integer.parseInt(words[3]),
						Integer.parseInt(words[4]), Integer.parseInt(words[5]), Integer.parseInt(words[6]),
						Integer.parseInt(words[7]), Integer.parseInt(words[8]));
			}
		} catch (IOException e) {
			System.out.println("Failed to read file");
		}
		return true;
	}

	private void addStudent(int id, String firstName, String lastName, int mathsMark1, int mathsMark2, int mathsMark3,
			int englishMark1, int englishMark2, int englishMark3) {
		AssignmentMarks math = new AssignmentMarks("Math", mathsMark1, mathsMark2, mathsMark3);
		AssignmentMarks english = new AssignmentMarks("English", englishMark1, englishMark2, englishMark3);
		Student student = new Student(id, firstName, lastName, math, english);
		students.push(student);
	}

	private static void displayReportByMarks() {
//		System.out.printf("")

	}

	private static void displayReportByGrades() {
		// Displays a list of all the students with the student letter grades.
	}

	public static void printMenu(String[] options) {
		for (String option : options) {
			System.out.println(option);
		}
		System.out.print("Welcome to the main menu. Please choose an option: ");
	}

	public static void mainMenu(String[] args) {
		String[] options = { "1- Display student marks", "2- Display student grades", "3- Add a new student",
				"4- Remove a student", "5- Exit", };
		Scanner scanner = new Scanner(System.in);
		int option = 1;
		while (option != 5) {
			printMenu(options);
			try {
				option = scanner.nextInt();
				switch (option) {

				case 1: {
					System.out.println("\n");
					displayReportByMarks();
					break;
				}
				case 2: {
					System.out.println("\n");
					displayReportByGrades();
					break;
				}

				case 3: {
					System.out.println("\n");
//					addNewStudent();
					break;
				}

				case 4: {
					System.out.println("\n");
					removeStudent();
					break;
				}

				case 5: {
					System.out.println("\n");
					System.exit(5);
					break;
				}
				}
			} catch (Exception ex) {
				System.out.println("Please enter a value between 1 and 5" + options.length);
				scanner.next();
			}
		}
	}

	private static void getAverageGrade() {
		// TODO Auto-generated method stub

	}

	private static void removeStudent() {
		// Asks the user to enter an ID. Then removes the student with that ID.
	}

	private void displayColumnHeaders() {
		System.out.printf("%-15s%5s%5s%5s%5s%8s%8s%5s%5s%5s%8s\n", "Name", "Maths", "A1", "A2", "A3", "Grade",
				"English", "A1", "A2", "A3", "Grade");
		System.out.println(
				"-----------------------------------------------------------------------------------------------");
	}
}
