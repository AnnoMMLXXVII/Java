package com.file.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.file.accounts.model.Accounts;
import com.file.handler.reader.ReadFile;
import com.file.handler.reader.ValidateCheckDigits;

@TestInstance(Lifecycle.PER_CLASS)
class ReadFileTests {

	private ReadFile instance;
	private String validAccount = "223355";
	private String invalidAccount = "555551";
	private String expectedFile = "src/main/resources/accounts/accounts.txt";

	@BeforeAll
	void setUp() throws Exception {
		instance = ReadFile.getInstance();
	}

	@Test
	void testIfInstanceIsAlreadyExists() {
		instance = ReadFile.getInstance();
		assertNotNull(instance);
	}

	@Test
	void testIsReadingFile() {
		instance.readFile(expectedFile);
		Accounts accounts = instance.getAllAccounts();
		assertFalse(accounts.getAccounts().isEmpty());
	}

	@Test
	void testIsValidAccountMethodValid() {
		boolean isValid = ValidateCheckDigits.isValidAccount(validAccount.trim());
		assertEquals(true, isValid);
	}

	@Test
	void testIsValidAccountMethodInValid() {
		boolean isNotValid = ValidateCheckDigits.isValidAccount(invalidAccount.trim());
		assertEquals(false, isNotValid);
	}

}
