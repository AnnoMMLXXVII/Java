import java.util.Random;
import java.util.Scanner;

public class AssignGrades {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of students: ");
		int numStudents = new Random().nextInt(15);
		int[] sampleData = generateData(numStudents);
		int[] scores = new int[numStudents];
		System.out.println("Enter " + numStudents + " scores: ");
		for (int i = 0; i < scores.length; i++) {
			scores[i] = sampleData[i];
		}
		int best = scores[0];
		int i = 0;
		while (i < scores.length) {
			if (best < scores[i]) {
				best = scores[i];
			}
			i++;
		};
		
		for (int z = 0; z < scores.length; z++) {
			System.out.printf("Student %s score is %s and grade is %s\n", i, scores[i], getGrade(scores[i], best));
		}
		input.close();

//		String s = "hellokitty";
//		for (int i = s.length() - 1; i >= 0; i -= 2) {
//			if (s.charAt(i) == 'i') {
//				System.out.print("");
//			} else if (s.charAt(i) == 'y') {
//				System.out.print(s.charAt(i - 2));
//				i++;
//			} else {
//				System.out.print(s.charAt(i));
//			}
//			if (i < 2) {
//				System.out.print("t");
//			}
//		}
//
//		int x = -1;
//		while (x < 7) {
//			x += 3;
//			System.out.print(x);
//		}
//		System.out.print(x);
//
//		x = 0;
//		s = "";
//		while (x < 6) {
//			x++;
//			if (x % 3 == 0) {
//				x++;
//			}
//			x++;
//			s = s + x;
//		}
//		System.out.print(s);
//
//		int z = 1;
//		for (int i = 0; i < 8; i = i + 3) {
//			for (int j = 12; j >= 1; j = j - 2) {
//				z++;
//				j++;
//			}
//			z++;
//		}
//		System.out.print(z);
//		;
//
//		int count = 1;
//		for (int i = 0; i < 3; i += 2) {
//			for (int j = 0; j <= i + 1; j++) {
//				count++;
//			}
//		}
//		System.out.print(count);

	}

	
	
	private static int getBestScore(int[] scores) {
		int best = scores[0];
		int i = 0;
		while (i < scores.length) {
			if (best < scores[i]) {
				best = scores[i];
			}
			i++;
		}
		return best;
	}

	enum GRADE {
		A,B,C,D,F
	}
	
	private static GRADE getGrade(int scores, int best) {
		if (scores >= best - 10) {
			return GRADE.A;
		}
		if (scores >= best - 20) {
			return GRADE.B;
		}
		if (scores >= best - 30) {
			return GRADE.C;
		}
		if (scores >= best - 40) {
			return GRADE.D;
		}
		return GRADE.F;
	}

	private static int[] generateData(int size) {
		Random r = new Random();
		int[] temp = new int[size];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = r.nextInt(100) + 0;
		}
		return temp;
	}
}
