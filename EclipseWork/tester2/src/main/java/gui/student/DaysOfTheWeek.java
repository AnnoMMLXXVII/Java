package main.java.gui.student;

import java.util.Scanner;

public class DaysOfTheWeek {

	private enum DAYS {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
	}

	private String[] hours = { "5:55am-12:12pm", "6:40am-1:11pm", "9:41am-2:32pm", "4:21am-5:30pm", "11am-11:59pm",
			"1:01am-1:01pm", "7:01am-00:59pm" };

	public static void main(String args[]) {
		new DaysOfTheWeek();
	}

	public DaysOfTheWeek() {
		System.out.println("Enter a Day of the week?");
		printDaysOfTheWeek();
		Scanner z = new Scanner(System.in);
		String input = z.next();
		String output = "";
		boolean found = false;
		while (found) {
			int i = 0;
			System.out.printf("%s\n", input.trim());
			for (DAYS d : DAYS.values()) {
				if (d.name().trim().compareToIgnoreCase(input.trim()) == 0) {
					output = String.format("%s Hours : %s", d.name(), hours[i]);
					found = true;
					break;
				}
				i++;
			}
			if (found) {
				break;
			}
			System.out.println("Enter a Day of the week: ");
			input = z.next();
		}
		if (found) {
			System.out.println(output);
		}
		z.close();
	}

	private void printDaysOfTheWeek() {
		for (DAYS d : DAYS.values()) {
			System.out.println(d);
		}
	}
}
