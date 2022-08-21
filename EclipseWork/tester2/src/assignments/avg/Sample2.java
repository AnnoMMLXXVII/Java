package assignments.avg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Sample2 {

	public static void main(String[] args) {
		new Sample2();
	}

	public Sample2() {
//		double[] sectionOne = { 80, 85, 90, 85, 94, 93, 80, 93, 82, 93, 82, 80 };
//		double[] sectionTwo = { 85, 90, 87, 88, 90, 82, 88, 82 };
//		double[] sectionThree = { 90, 92, 83, 85, 88, 90, 93, 80, 82, 81 };
//		double[][] sections = { sectionOne, sectionTwo, sectionThree };
		Scanner input = new Scanner(System.in);

		int numberOfRows, numberOfcolumns;
		System.out.println("My first JAVA! Good day\n");
		System.out.print("Enter number of Section");
		numberOfRows = input.nextInt();
		double[][] sections = new double[numberOfRows][0];
		for (int i = 0; i < numberOfRows; i++) {
			System.out.printf("Enter number of students in section %s\n", (i + 1));
			numberOfcolumns = input.nextInt();
			sections[i] = new double[numberOfcolumns];
		}
		System.out.println();
		double max = 0.0;
		double min = 0.0;
		double allMax = 0.0;
		double[] allMins = new double[sections.length];
		for (int rowCounter = 0; rowCounter < sections.length; rowCounter++) {
			System.out.println("Grades of Students " + rowCounter + ":");
			for (int columnCounter = 0; columnCounter < sections[rowCounter].length; columnCounter++) {
				sections[rowCounter][columnCounter] = input.nextDouble();
				input.nextLine();
				if (sections[rowCounter][columnCounter] > max) {
					max = sections[rowCounter][columnCounter];
				}
				min = lookForMinimum(sections[rowCounter], columnCounter - 1, sections[rowCounter][columnCounter]);
			}
			if (max > allMax) {
				allMax = max;
			}
			allMins[rowCounter] = min;
			System.out.printf("Average Score in Section %s: %.2f\n", 1, getAverageSectionN(sections, 1));
			System.out.printf("Lowest  Score in Section %s: %s\n", 2, min);
			System.out.printf("Highest Score in Section %s: %s\n", 3, max);
			max = 0.0;
		}

		for (int i = 1; i < allMins.length; i++) {
			min = lookForMinimum(allMins, i - 1, allMins[i]);
		}
		System.out.println("--------------------------------------");
		System.out.printf("Average Score in All Sections : %.2f\n", getAverageForAllSections(sections));
		System.out.printf("Highest Score in All Sections : %s\n", allMax);
		System.out.printf("Lowest Score in All Sections : %s\n", min);
		double[] mode = getMode(sections);
		String str = "";
		for (int i = 0; i < mode.length; i++) {
			str = str + mode[i] + ",";
		}
		System.out.printf("Mode: %s\n", str.substring(0, str.length() - 1));
		System.out.printf("Median: %s\n", getMedian(sections));
		input.close();
		System.out.println("Display of Grades");
		print(sections);
	}

	private void print(double[][] sections) {
		for (int i = 0; i < sections.length; i++) {
			for (int j = 0; j < sections[i].length; j++) {
				System.out.printf("%.2f, ", sections[i][j]);
			}
			System.out.println();
		}
	}

	private double lookForMinimum(double[] subSet, int high, double value) {
		double min = value;
		if (min == -1) {
			return subSet[0];
		}
		for (; high >= 0; high--) {
			if (subSet[high] < value) {
				min = subSet[high];
			}
		}
		return min;
	}

	private double getAverageForAllSections(double[][] sections) {
		double sum = 0.0;
		int count = 0;
		for (int i = 0; i < sections.length; i++) {
			for (int j = 0; j < sections[i].length; j++) {
				sum = sum + (sections[i][j]);
				count++;
			}
		}
		return (sum / count);
	}

	private double getAverageSectionN(double[][] sections, int sectionNumber) {
		if (sectionNumber < 1) {
			sectionNumber = 1;
		}
		double sum = 0.0;
		int count = 0;
		for (int j = 0; j < sections[sectionNumber - 1].length; j++) {
			sum = sum + (sections[sectionNumber - 1][j]);
			count++;
		}
		return (sum / count);
	}

	private double getMedian(double[][] sections) {
		List<Double> temp = new ArrayList<>();
		for (int i = 0; i < sections.length; i++) {
			for (int j = 0; j < sections[i].length; j++) {
				temp.add(sections[i][j]);
			}
		}
		Arrays.sort(temp.toArray());
		return temp.get(temp.size() / 2);
	}

	private double[] getMode(double[][] sections) {
		Map<Double, Integer> modeSet = new HashMap<>();
		for (int i = 0; i < sections.length; i++) {
			for (int j = 0; j < sections[i].length; j++) {
				modeSet = addToModeSet(modeSet, sections[i][j]);
			}
		}
		Integer max = (Integer) modeSet.values().toArray()[0];
		double[] modes = new double[modeSet.keySet().size()];
		int i = 0;
		int size = 0;
		for (Map.Entry<Double, Integer> entry : modeSet.entrySet()) {
			if (i > 0) {
				if (entry.getValue() >= max) {
					max = entry.getValue();
					addToMode(entry.getKey(), modes);
					size++;
				}
			}
			i++;
		}
		return Arrays.copyOf(modes, size);
	}

	private Map<Double, Integer> addToModeSet(Map<Double, Integer> modeSet, double key) {
		if (modeSet.containsKey(key)) {
			modeSet.put(key, modeSet.get(key) + 1);
		} else {
			modeSet.put(key, 1);
		}
		return modeSet;
	}

	private double[] addToMode(double mode, double... modes) {
		for (int i = 0; i < modes.length; i++) {
			if (modes[i] == 0.0) {
				modes[i] = mode;
				break;
			}
		}
		return modes;
	}

}
