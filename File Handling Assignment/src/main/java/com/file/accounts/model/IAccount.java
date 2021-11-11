package com.file.accounts.model;

public interface IAccount {

	public String getAccountNumber();
	public void setAccountNumber(String accountNumber);

	public int hashCode();
	public boolean equals(Object obj);
	public String toString();

}
