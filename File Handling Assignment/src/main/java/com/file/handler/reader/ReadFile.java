package com.file.handler.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.file.accounts.model.Account;
import com.file.accounts.model.Accounts;
import com.file.accounts.model.Directory;
import com.file.handler.FileHandlingManager;

public class ReadFile {

	private static ReadFile instance;
	private File file = null;
	private Accounts accounts;
	private List<Directory> directories;

	public static ReadFile getInstance() {
		if (instance == null) {
			return new ReadFile();
		}
		return instance;
	}

	private ReadFile() {
		accounts = new Accounts(new ArrayList<>());
	}

	public void readFile(String filePath) {
		file = new File(filePath);
		parseThroughFile();
	}

	public void readDirectoryFile() {
		directories = new ArrayList<>();
		try(Scanner z = new Scanner(new FileReader(FileHandlingManager.getDirectoryFile()))) {
			while(z.hasNextLine()) {
				String line = z.nextLine();
				String[] info = line.split(",");
				directories.add(new Directory(info[0].trim(), info[1].trim()));
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Directory> getDirectories() {
		return directories;
	}

	public void readSourceFile(String filePath) {

		file = new File(filePath);
		parseThroughSourceFile();
	}

	public File getFile() {
		return file;
	}

	public Accounts getAllAccounts() {
		return accounts;
	}

	private void parseThroughFile() {
		try (Scanner z = new Scanner(new FileReader(file))) {
			System.out.println("Reading through file: "+file.getPath());
			while (z.hasNextLine()) {
				String accountLine = z.nextLine().trim();
				System.out.printf("Validating Account [%s] ...%n", accountLine);
				if (ValidateCheckDigits.isValidAccount(accountLine)) {
					accounts.addValidAccount(new Account(accountLine));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void parseThroughSourceFile() {
		List<String> source = new ArrayList<>();
		try (Scanner z = new Scanner(new FileReader(file))) {
			while (z.hasNextLine()) {
				String srcLine = z.nextLine();
				source.add(srcLine);
			}
			FileHandlingManager.setSourceLines(source);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
