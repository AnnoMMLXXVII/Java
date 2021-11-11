package com.file.handler.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.file.accounts.model.Accounts;
import com.file.accounts.model.Directory;
import com.file.accounts.model.IAccount;
import com.file.handler.FileHandlingManager;

public class WriteFile {

	private static WriteFile instance;
	private File newFile;
	private static final String WRITE_PATH = "src/main/resources/accounts/";

	public static WriteFile getInstance() {
		if (instance == null) {
			return new WriteFile();
		}
		return instance;
	}

	public void writeValidAccountsToNewFile(String fileName) {
		newFile = new File(WRITE_PATH + fileName);
		Accounts accounts = FileHandlingManager.getValidAccounts();
		try (FileWriter writer = new FileWriter(newFile)) {
			for (IAccount account : accounts.getAccounts()) {
				writer.write(account.getAccountNumber() + "\n");
			}
			System.out.println("NEW FILE CREATED: " + newFile.getPath());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeSourceFile(String fileName) {
		newFile = new File(fileName);
		try (FileWriter fw = new FileWriter(newFile)) {
			List<String> sourceLines = convertToUnderScore(FileHandlingManager.getSourceLines());
			for (String s : sourceLines) {
				fw.write(s + "\n");
			}
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getNewFile() {
		return newFile;
	}

	private List<String> convertToUnderScore(List<String> sourceLines) {
		List<String> temp = new ArrayList<>();
		for (String s : sourceLines) {
			String underScoredFormat = s.replace(" ", "_");
			temp.add(underScoredFormat);
		}
		return temp;
	}

	public void updateDirectoryFile() {
		List<Directory> directories = FileHandlingManager.getDirectoriesList();
		try (FileWriter fw = new FileWriter(FileHandlingManager.getDirectoryFile())) {
			for(Directory d: directories) {
				fw.write(d.toString()+"\n");
			}
			fw.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}
}
