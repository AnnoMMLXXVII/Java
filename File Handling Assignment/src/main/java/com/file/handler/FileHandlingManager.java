package com.file.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import com.file.accounts.model.Accounts;
import com.file.accounts.model.Directory;
import com.file.handler.reader.ReadFile;
import com.file.handler.writer.WriteFile;

public class FileHandlingManager {

	private static final String ACCOUNTS_TXT_PATH = "src/main/resources/accounts/accounts.txt";
	private static final String SRC_RESOURCE_PATH = "src/main/resources/";
	private static final File NAME_FILE = new File(SRC_RESOURCE_PATH + "/directory/names.txt");
	private static final File PHONE_NUMBER_FILE = new File(SRC_RESOURCE_PATH + "/directory/phoneNumbers.txt");
	private static final File DIRECTORY_FILE = new File(SRC_RESOURCE_PATH + "directory/directory.txt");
	private static Scanner z;
	private static ReadFile reader;
	private static WriteFile writer;
	private static Accounts accounts;
	private static List<String> srcLines;
	private static Map<String, Integer> isNewNumber;
	private static List<String> numbers;
	private static List<Directory> directories;
	private static Random r = new Random();
	private static boolean updatedDirectory = false;

	public static void run() {
		String input = displayPrograms();
		while(!input.equalsIgnoreCase("x")) {
			input = displayPrograms();
		}
	}

	private static String displayPrograms() {
		System.out.println("Choose Program to Run: ");
		System.out.println("[2] -> Validate Account Numbers (Module 2)");
		System.out.println("[3] -> Source Destination (Module 3)");
		System.out.println("[4] -> Directory Program (Module 4)");
		System.out.println("[X] -> Terminate Program (EXIT)");
		z = new Scanner(System.in);
		String input = z.next();
		if(input.equals("2")) {
			readAccountsProgram();
		}
		else if(input.equals("3")) {
			promptFileSrcAndDest();
		}
		else if(input.equals("4")) {
			phoneDirectoryProgram();
		}
		return input;
	}

	public static String getWriteFileName() {
		return "ValidAccounts" + System.currentTimeMillis() + ".txt";
	}

	public static Accounts getValidAccounts() {
		return accounts;
	}

	public static void setSourceLines(List<String> sourceLines) {
		srcLines = sourceLines;
	}

	public static List<String> getSourceLines() {
		return srcLines;
	}

	public static File getDirectoryFile() {
		return DIRECTORY_FILE;
	}

	public static List<Directory> getDirectoriesList() {
		return directories;
	}

	private static void promptFileSrcAndDest() {
		System.out.println("STARTING UP SOURCE/DESTINATION PROGRAM...");
		System.out.println("----------------------------------------------");
		reader = ReadFile.getInstance();
		writer = WriteFile.getInstance();
		System.out.println("Enter the Source File Name: ");
		try (Scanner z = new Scanner(System.in)) {
			String srcFileName = z.nextLine();
			System.out.println("Enter the Destination File Name: ");
			String destFileName = z.nextLine();
			reader.readSourceFile(SRC_RESOURCE_PATH + srcFileName + ".txt");
			writer.writeSourceFile(SRC_RESOURCE_PATH + destFileName + ".txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void readAccountsProgram() {
		System.out.println("STARTING UP VALIDATING ACCOUNTS PROGRAM...");
		System.out.println("----------------------------------------------");
		reader = ReadFile.getInstance();
		reader.readFile(ACCOUNTS_TXT_PATH);
		accounts = reader.getAllAccounts();
		writer = WriteFile.getInstance();
		writer.writeValidAccountsToNewFile(getWriteFileName());
	}

	private static void phoneDirectoryProgram() {
		System.out.println("STARTING UP DIRECTORY PROGRAM...");
		System.out.println("----------------------------------------------");
		z = new Scanner(System.in);
		disableAllPermission();
		reader = ReadFile.getInstance();
		allowDirectoryFileEdit();
		writer = WriteFile.getInstance();
		allowDirectoryFileEdit();
		reader.readDirectoryFile();
		directories = reader.getDirectories();
		displayIntroDirectoryPrompt();
		displayRandomNames();
		System.out.println("Please Enter Name to display information: ");
		String input = z.next();
		String option = startPromptSequence(input);
		while (!option.equalsIgnoreCase("x")) {
			updateDirectory(input, option);
			System.out.println("Please Enter Name to display information (OR 'X' to exit): ");
			input = z.next();
			if (input.equalsIgnoreCase("x")) {
				break;
			}
			option = startPromptSequence(input);
		}
		if (updatedDirectory) {
			writer.updateDirectoryFile();
			disableAllPermission();
			displayOutroDirectoryPrompt(true);

		} else {
			disableAllPermission();
			displayOutroDirectoryPrompt(false);
		}
	}

	private static String startPromptSequence(String input) {
		String option = "x";
		Optional<Directory> optRes = directories.stream().filter(e -> e.getName().equalsIgnoreCase(input.trim()))
				.findFirst();
		Directory result = (optRes.isPresent()) ? result = optRes.get() : null;
		String printResults = (result == null) ? "NO RESULTS FOUND!"
				: String.format("Nam\t| Phone Number\t%n%s\t| %s%n", result.getName(), result.getPhoneNumber());
		if (!printResults.equalsIgnoreCase("NO RESULTS FOUND!")) {
			System.out.println(printResults);
			System.out.println("What would you like to Update: Name[N], Phone Number [P], or Exit [X]");
			option = z.next();
		}
		return option;
	}

	private static void displayIntroDirectoryPrompt() {
		System.out.println("Welcome to the Directory");
		System.out.println("--------------------------------------");
	}

	private static boolean updateDirectory(String oldName, String input) {
		boolean flag = false;
		if (input.equalsIgnoreCase("x") || input.equalsIgnoreCase("exit")) {
			return flag;
		} else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("name")) {
			System.out.println("Enter New Name to Update: ");
			String value = z.next();
			Directory temp = null;
			List<Directory> tempList = directories;
			tempList.stream().forEach(e -> {
				if (e.getName().equalsIgnoreCase(oldName) && !e.getName().equalsIgnoreCase(value)) {
					e.setName(value);
				}
			});
			temp = tempList.stream().filter(e -> e.getName().equalsIgnoreCase(value)).findFirst().get();
			if (temp != null) {
				System.out.println("NAME HAS BEEN UPDATED..." + " -> " + temp.toString());
				updatedDirectory = true;
				flag = true;
				directories = tempList;
			}
		} else if (input.equalsIgnoreCase("p")) {
			System.out.println("Enter New Phone Number to Update: ");
			String value = z.next();
			Directory temp = null;
			List<Directory> tempList = directories;
			tempList.stream().forEach(e -> {
				if (e.getName().equalsIgnoreCase(oldName)) {
					e.setPhoneNumber(value);
				}
			});
			temp = directories.stream().filter(e -> e.getPhoneNumber().equals(value)).findFirst().get();
			if (temp != null) {
				System.out.println("NUMBER HAS BEEN UPDATED..." + " -> " + temp.toString());
				updatedDirectory = true;
				flag = true;
				directories = tempList;
			}
		} else {
			System.out.println("WRONG INPUT!");
			flag = true;
		}
		return flag;
	}

	private static void displayRandomNames() {
		int rand = r.nextInt(directories.size()) + 0;
		for (int i = 0; i < 10; i++) {
			System.out.println(directories.get(rand));
			rand = r.nextInt(directories.size()) + 0;
		}
	}

	private static void displayOutroDirectoryPrompt(boolean isUpdated) {
		String outro = (isUpdated) ? "Updating File...\nTerminating Directory...\nGOODBYE!"
				: "Terminating Directory...\nGOODBYE!";
		System.out.println(outro);
	}

	private static void generateNewNumbersAndUpdateFiles() {
		writePhoneNumbers();
		writeToPhoneNumbersFile();
		populateDirectoryTextFile();
	}

	private static void writePhoneNumbers() {
		if (!PHONE_NUMBER_FILE.canRead() && !PHONE_NUMBER_FILE.canWrite()) {
			allowPhoneNumberFileEdit();
		}
		numbers = new ArrayList<>();
		isNewNumber = new HashMap<>();
		for (int i = 0; i < 1000; i++) {
			String number = generatePhoneNumber();
			boolean flag = isDuplicate(number);
			while (flag) {
				number = generatePhoneNumber();
			}
			numbers.add(number);
		}
		disablePhoneNumberFileEdit();
	}

	private static String generatePhoneNumber() {
		StringBuilder str = new StringBuilder("");
		for (int i = 0; i < 10; i++) {
			str.append(r.nextInt(9) + 0);
		}
		return str.toString();
	}

	private static boolean isDuplicate(String number) {
		if (isNewNumber.containsKey(number)) {
			isNewNumber.put(number, isNewNumber.get(number) + 1);
			return true;
		} else {
			isNewNumber.put(number, 1);
			return false;
		}
	}

	private static void writeToPhoneNumbersFile() {
		try (FileWriter fw = new FileWriter(PHONE_NUMBER_FILE)) {
			for (String s : numbers) {
				fw.write(s + "\n");
			}
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void populateDirectoryTextFile() {
		if (!DIRECTORY_FILE.canRead() && !DIRECTORY_FILE.canWrite()) {
			allowAllPermission();
		}
		String nameLine;
		String phoneLine;
		try (Scanner z = new Scanner(new FileReader(NAME_FILE));
				Scanner x = new Scanner(new FileReader(PHONE_NUMBER_FILE))) {
			try (FileWriter fw = new FileWriter(DIRECTORY_FILE)) {
				while (z.hasNextLine() && x.hasNextLine()) {
					nameLine = z.nextLine();
					phoneLine = x.nextLine();
					fw.write(new Directory(nameLine, phoneLine).toString() + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			disableAllPermission();
		}
	}

	private static void allowAllPermission() {
		allowDirectoryFileEdit();
		allowNameFileEdit();
		allowPhoneNumberFileEdit();
	}

	private static void disableAllPermission() {
		disableDirectoryFileEdit();
		disableNameFileEdit();
		disablePhoneNumberFileEdit();
	}

	private static void allowDirectoryFileEdit() {
		DIRECTORY_FILE.setReadable(true);
		DIRECTORY_FILE.setWritable(true);
	}

	private static void disableDirectoryFileEdit() {
		DIRECTORY_FILE.setReadable(false);
		DIRECTORY_FILE.setWritable(false);
	}

	private static void allowNameFileEdit() {
		NAME_FILE.setReadable(true);
		NAME_FILE.setWritable(true);
	}

	private static void disableNameFileEdit() {
		NAME_FILE.setReadable(false);
		NAME_FILE.setWritable(false);
	}

	private static void allowPhoneNumberFileEdit() {
		PHONE_NUMBER_FILE.setReadable(true);
		PHONE_NUMBER_FILE.setWritable(true);
	}

	private static void disablePhoneNumberFileEdit() {
		PHONE_NUMBER_FILE.setReadable(false);
		PHONE_NUMBER_FILE.setWritable(false);
	}

}
