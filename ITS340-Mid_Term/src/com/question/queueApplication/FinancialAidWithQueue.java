package com.question.queueApplication;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FinancialAidWithQueue {

	private static FinancialAidWithQueue instance;
	
	public static FinancialAidWithQueue getInstance() {
		if(instance == null) {
			synchronized(FinancialAidWithQueue.class) {
				if(instance == null) {
					return new FinancialAidWithQueue();
				}
			}
		}
		return instance;
	}
	
	private FinancialAidWithQueue() {
		application();
	}

	private void application() {
		Queue<String> queue = new LinkedList<>();
		try (Scanner z = new Scanner(System.in)) {
			System.out.println("Enter up At least 5 Names: ");
			String name = z.next();
			while (true) {
				queue.add(name);
				if (queue.size() > 5) {
					String s = queue.remove();
					System.out.printf("What is the Income for: %s\n", s);
					int income = z.nextInt();
					if (isQualifiedForAid(income)) {
						System.out.printf("%s IS QUALIFIED FOR FINANCIAL AID!!\n", s);
					}
				}
				System.out.println("Enter Another Name: ");
				name = z.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean isQualifiedForAid(int income) {
		return (income < 35000) ? true : false;
	}
}
