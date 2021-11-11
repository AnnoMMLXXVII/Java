package com.file.accounts.model;

import java.util.List;

public class Accounts {

	private List<IAccount> accounts;

	public Accounts(List<IAccount> accounts) {
		this.accounts = accounts;
	}

	public List<IAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<IAccount> accounts) {
		this.accounts = accounts;
	}

	public void addValidAccount(IAccount account) {
		accounts.add(account);
	}

	public IAccount getAccountByIndex(int index) {
		return accounts.get(index);
	}

	public IAccount getAccountByNumber(String number) {
		return accounts.stream().filter(e -> e.getAccountNumber().equalsIgnoreCase(number)).findFirst().orElseGet(null);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accounts other = (Accounts) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return accounts.toString();
	}



}
