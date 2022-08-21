package assignments.avg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CaseStudy {
	private static List<List<Double>> array2D;

	private static Map<Integer, SectionInformation> sectionInfo;
	private static Map<Double, Integer> modeSet;

	public static void main(String[] args) {
		sectionInfo = new HashMap<>();
		modeSet = new HashMap<>();
		Scanner input = new Scanner(System.in);
		int numberOfRows, numberOfcolumns;
		double sum = 0.0;
		System.out.println("My first JAVA! Good day\n");
		System.out.print("Enter number of Section");
		numberOfRows = input.nextInt();
		for (int i = 0; i < numberOfRows; i++) {
			System.out.printf("Enter number of students in section %s\n", (i + 1));
			numberOfcolumns = input.nextInt();
			array2D = updateTableOfStudentsAndSections(numberOfcolumns);
		}
//		array2D = usePresetValues();
//		System.out.print("Enter number of Students : ");
//		System.out.println();
		double max = 0.0;
		double min = 0.0;
		double median = 0.0;
		int counter = 0;
		for (int rowCounter = 0; rowCounter < array2D.size(); rowCounter++) {
			System.out.println("Grades of Students " + (rowCounter + 1) + ":");
			for (int columnCounter = 0; columnCounter < array2D.get(rowCounter).size(); columnCounter++) {
				double temp = input.nextDouble();
				input.nextLine();
//				double temp = array2D.get(rowCounter).get(columnCounter);
				array2D.get(rowCounter).set(columnCounter, temp);
				sum = sum + array2D.get(rowCounter).get(columnCounter);
				if (temp > max) {
					max = temp;
				}
				min = lookForMinimum(array2D.get(rowCounter), columnCounter - 1, temp);
//				addToModeSet(temp);
				counter++;
			}
			sectionInfo.put(rowCounter + 1, new SectionInformation(calculateAverage(sum, counter), max, min));
			Collections.sort(array2D.get(rowCounter), new Comparator<Double>() {
				@Override
				public int compare(Double o, Double o1) {
					return o.compareTo(o1);
				}
			});
			print();
			median = array2D.get(rowCounter).get(array2D.get(rowCounter).size() / 2);
			max = 0.0;
//			System.out.println(median);
//			System.out.printf("Average Score in Section %s: %s\n", rowCounter + 1, min);
//			System.out.printf("Lowest  Score in Section %s: %s\n", rowCounter + 1, min);
//			System.out.printf("Highest Score in Section %s: %s\n", rowCounter + 1, max);
		}
//		ALL SECTIONS
		min = sectionInfo.get(1).getLowest();
		max = sectionInfo.get(1).getHighest();
		sum = 0.0;
		for (SectionInformation s : sectionInfo.values()) {
			if (s.getLowest() < min) {
				min = s.getLowest();
			}
			if (s.getHighest() > max) {
				max = s.getHighest();
			}
			sum = sum + s.getAverage();
		}
		sectionInfo.forEach((e, v) -> System.out.printf("Average Score in section %s: %.2f\n", e, v.getAverage()));
		System.out.printf("Average Score in All Sections : %.2f\n", calculateAverage(sum, array2D.size()));
		sectionInfo.forEach((e, v) -> System.out.printf("Highest Score in section %s: %.2f\n", e, v.getHighest()));
		System.out.printf("Highest Score in All Sections : %s\n", max);
		sectionInfo.forEach((e, v) -> System.out.printf("Lowest Score in section %s: %.2f\n", e, v.getLowest()));
		System.out.printf("Lowest Score in All Sections : %s\n", min);
//		double[] mode = new double[modeSet.values().size()];
//		max = 0;
//		for (Integer i : modeSet.values()) {
//			if (i > max) {
//				max = i;
//			}
//		}
//		System.out.printf("Mode: %s", mode);

//		System.out.println("Display of Grades");
//		for (int rowCounter = 0; rowCounter < numberOfRows; rowCounter++) {
//			for (int columnCounter = 0; columnCounter < numberOfcolumns; columnCounter++) {
//				System.out.print(array2D[rowCounter][columnCounter] + " | "+ "tsfsd"+ asdfasd);
//			}
//			System.out.println();
//		}
//		for (double[] num : array2D) {
//			for (double n : num) {
//				sum = sum + n;
//			}
//		}
//
//		double average = sum / array2D[0].length;
//		System.out.format("The average is: %.2f", average);

		input.close();
	}

	private static List<List<Double>> updateTableOfStudentsAndSections(int studentsPerSection) {
		if (array2D == null || array2D.size() == 0) {
			array2D = new ArrayList<>();
		}
		array2D.add(Arrays.asList(new Double[studentsPerSection]));
		return array2D;
	}

	private static void print() {
		array2D.forEach(e -> {
			e.forEach(v -> System.out.printf("%s, ", v));
			System.out.println();
		});
	}

	private static double lookForMinimum(List<Double> subSet, int high, double value) {
		double min = value;
		if (min == -1) {
			min = subSet.get(0);
		}
		for (; high >= 0; high--) {
			if (subSet.get(high) < value) {
				min = subSet.get(high);
			}
		}
		return min;
	}

	private static List<List<Double>> usePresetValues() {
		array2D = new ArrayList<>();
		List<Double> temp = Arrays.asList(80.0, 85.0, 90.0, 85.0, 94.0, 93.0, 80.0, 93.0, 82.0, 93.0, 82.0, 80.0);
		array2D.add(temp);
		temp = Arrays.asList(85.0, 90.0, 87.0, 88.0, 90.0, 82.0, 88.0, 82.0);
		array2D.add(temp);
		temp = Arrays.asList(90.0, 92.0, 83.0, 85.0, 88.0, 90.0, 93.0, 80.0, 82.0, 81.0, 55.9);
		array2D.add(temp);
		print();
		return array2D;
	}

	private static double calculateAverage(double sum, int divisor) {
		return (sum / divisor);
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

class SectionInformation {
	private double average;
	private double highest;
	private double lowest;

	/**
	 * @param average
	 * @param individAverage
	 * @param highest
	 * @param lowest
	 */
	public SectionInformation(double average, double highest, double lowest) {
		super();
		this.average = average;
		this.highest = highest;
		this.lowest = lowest;
	}

	/**
	 * @return the average
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * @return the highest
	 */
	public double getHighest() {
		return highest;
	}

	/**
	 * @return the lowest
	 */
	public double getLowest() {
		return lowest;
	}

}
