package assignments.avg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Sample {
	private Scanner z = new Scanner(System.in);

	public static void main(String[] args) {
		new Sample();
	}

	public Sample() {
//		System.out.print("Enter number of section: ");
//		int sectionCount = z.nextInt();

	}

	private void printExamples() {
		double[] sectionOne = { 80, 85, 90, 85, 94, 93, 80, 93, 82, 93, 82, 80 };
		double[] sectionTwo = { 85, 90, 87, 88, 90, 82, 88, 82 };
		double[] sectionThree = { 90, 92, 83, 85, 88, 90, 93, 80, 82, 81 };
		double[][] sections = { sectionOne, sectionTwo, sectionThree };
		print(sections);
		sort(sections, 0);
		sort(sections, 1);
		sort(sections, 2);
		print(sections);

		double[] dd = getMode(sections);
		for (Double d : dd) {
			System.out.println(d);
		}
		double median = getMedian(sections);
		System.out.println(median);
		System.err.println("---------N-Section-Avg---------------");
		double sectionAvg = getAverageSectionN(sections, 1);
		System.out.println(sectionAvg);
		sectionAvg = getAverageSectionN(sections, 2);
		System.out.println(sectionAvg);
		sectionAvg = getAverageSectionN(sections, 3);
		System.out.println(sectionAvg);
		System.err.println("----------Sections-Avg--------------");
		double allSectionsAvg = getAverageForAllSections(sections);
		System.out.println(allSectionsAvg);
		System.err.println("----------MIN | MAX--------------");
		double min = 0.0;
		double max = 0.0;
		for (int i = 0; i < sections.length; i++) {
			for (int j = 0; j < sections[i].length; j++) {
				min = lookForMinimum(sections[i], j - 1, sections[i][j]);
				if (sections[i][j] > max) {
					max = sections[i][j];
				}
			}
			System.out.printf("MIN for Section %s: %s\n", i, min);
			System.out.printf("MAX for Section %s: %s\n", i, max);
			max = 0.0;
		}
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
