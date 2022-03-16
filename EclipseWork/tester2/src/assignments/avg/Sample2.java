package assignments.avg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Sample2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		double[][] array2d;
		int numberOfRows, numberOfcolumns;
		double sum = 0;
		System.out.println("My first JAVA! Good day\n");
		System.out.print("Enter number of Section");
		numberOfRows = input.nextInt();
		System.out.print("Enter number of Students : ");
		numberOfcolumns = input.nextInt();
		System.out.println();
		array2d = new double[numberOfRows][numberOfcolumns];
		double max = 0.0;
		double min = 0.0;
		for (int rowCounter = 0; rowCounter < numberOfRows; rowCounter++) {
			System.out.println("Grades of Students " + rowCounter + ":");
			for (int columnCounter = 0; columnCounter < numberOfcolumns; columnCounter++) {
				array2d[rowCounter][columnCounter] = input.nextDouble();

				if (array2d[rowCounter][columnCounter] < min) {
					min = array2d[rowCounter][columnCounter];
				}

				if (array2d[rowCounter][columnCounter] > max) {
					max = array2d[rowCounter][columnCounter];
				}
			}
			System.err.println("Current Min Value: " + min);
			System.err.println("Current Max Value: " + max);
		}

		System.out.println("Display of Grades");
		for (int rowCounter = 0; rowCounter < numberOfRows; rowCounter++) {
			for (int columnCounter = 0; columnCounter < numberOfcolumns; columnCounter++) {
				System.out.print(array2d[rowCounter][columnCounter] + " | ");
			}
			System.out.println();
		}
		for (double[] num : array2d) {
			for (double n : num) {
				sum = sum + n;
			}
		}

		double average = sum / array2d[0].length;
		System.out.format("The average is: %.2f", average);

		input.close();
	}
	private void print(double[][] sections) {
		for (int i = 0; i < sections.length; i++) {
			for (int j = 0; j < sections[i].length; j++) {
				System.out.printf("%.2f, ", sections[i][j]);
			}
			System.out.println();
		}
	}

	private void sort(double[][] sections, int row) {
		if (row > sections.length || row < 0) {
			return;
		}
		Arrays.sort(sections[row]);
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
