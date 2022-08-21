package tester2.airplance;

import java.util.Scanner;

public class Flight {

	public static void main(String[] args) {
		menu();
		
	}

	public static void menu() {
		System.out.println(" Welcome to our Airplane seats  ^-^");
		System.out.println(" We afford five options , So please choose the option you want:");

		Scanner in = new Scanner(System.in);
		int option;
		System.out.println("(1). Reserve a new empty seat.~ ");
		System.out.println("(2). Delete a reserved seat.~");
		System.out.println("(3). Delete all reserved seats.~");
		System.out.println("(4). Print out flight seats map.~");
		System.out.println("(5). Quit.~");
		System.out.println(" Enter your option by choosing a number from 1-5 : ");
		option = in.nextInt();
		
		AirplaneSeats.isValid(34);
	}
}
