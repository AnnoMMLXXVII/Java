
public class WesternGPACalc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// declaring ARRAY

		double[] grades = new double[7];

		grades[0] = 4.0; // A
		grades[1] = 3.5; // AB
		grades[2] = 3.0; // B
		grades[3] = 2.5; // BC
		grades[4] = 2.0; // C
		grades[5] = 1.0; // D
		grades[6] = 0.0; // F

		double[] credit = new double[7];

		credit[0] = .5;
		credit[1] = 3;
		credit[2] = 2;
		credit[3] = 3;
		credit[4] = 3;
		credit[5] = 1;
		credit[6] = 2;

		// printGradeStats(grades[]); //?

		// printGPA(grades, credit); //entire array or just indecies?

		double maxGrade = maxLetterGrade(grades);
		String maxLetterGrade = convertToLetterGrade(maxGrade);
		System.out.println("The maximum grade you can get is.. " + maxLetterGrade);

		double minGrade = minLetterGrade(grades);
		String minLetterGrade = convertToLetterGrade(minGrade);
		System.out.println("The minimum grade you can get is.. " + minLetterGrade);

		double totalGPA = printGPA(grades, credit);
		String finalGPA = convertToLetterGrade(totalGPA);
		System.out.println("Your final Grade Point Average is... " + finalGPA);

	}

	private static double maxLetterGrade(double[] arr) {

		double maxGrade = 0; //// won't find less than 0

		for (int i = 0; i < arr.length; i++) {

			double scanGrades = arr[i];

			if (scanGrades > maxGrade) { // More than zero it will max
				maxGrade = scanGrades;
			}

		}

		return maxGrade;
	}

	private static double minLetterGrade(double[] arr) {

		double minGrade = 5.0; // won't find more than 5

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < minGrade) { // More than zero it will max
				minGrade = arr[i];
			}
		}
		return minGrade;
	}

	// TODO:
	// Finish this method which will convert
	// a grade on the 4.0 scale and return a letter grade
	// Use the following scale...
	// A = 4.0
	// 4.0 > A/B >= 3.5
	// 3.5 > B >= 3.0
	// 3.0 > B/C >= 2.5
	// 2.5 > C >= 2.0
	// 2.0 > D >= 1.0
	// F < 1.0
	private static String convertToLetterGrade(double grade) {

		// how would i call max grade into this? using a variable?
		// how to output both letter AND numerical?

		if (grade == 4.0) {
			return "4.0 A";
		} else if (grade >= 3.5) {
			return "3.5 AB";
		} else if (grade >= 3.0) {
			return "3.0 B";
		} else if (grade == 2.5) {
			return "2.5 BC";
		} else if (grade >= 2.0) {
			return "2.0 C";
		} else if (grade >= 1.0) {
			return "1.0 D";
		} else if (grade == 0.0) {
			return "0.0 F";
		} else {
			return "Error...:)";
		}
	}

	// TODO:
	// Finish this method which will accept an array of grades and credits
	// and print the cumulative GPA as a letter grade
	private static double printGPA(double[] grades, double[] credit) {

		// direct array
		double sum = 0;
		double totalGradePoints = 0;

		// need to make it so that credit goes up to 7 just like the gradedPoints

		// loop calculates sum GP
		// sum credits
		for (int i = 0; i < grades.length; i++) {

			double ttlGradePoints = grades[i] * credit[i];
			totalGradePoints += ttlGradePoints;
			sum += credit[i];

		}

		double averageGPA = totalGradePoints / sum;

		return averageGPA;

	}

	// Recall...GPA is just a weighted average...
	// Cumulative GPA is the sum of all grade points -- grade[i] * credit[i]
	// divided by the sum of all credits[i]

	// TODO:
	// Calculate cumulative GPA

	// TODO:
	// Output Cumulative GPA as both a number and a grade
}