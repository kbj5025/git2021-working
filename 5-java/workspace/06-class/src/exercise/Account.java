package exercise;

public class Account {
	private int balance;
	private final int MIN_BALANCE = 0;
	private final int MAX_BALANCE = 100000;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
