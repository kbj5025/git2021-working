package exercise;

public class AccountExample {

	public static void main(String[] args) {
		Account account = new Account();

		account.setBalance(10000);

		if (account.getBalance() == 10000) {
			System.out.println("���̽� ��� - pass");
		} else {
			System.out.println("���̽� ���� - fail");
		}

		// ��������
		int expectedValue = account.getBalance();
		account.setBalance(-100);
		if (account.getBalance() == expectedValue) {
			System.out.println("���̽� ��� - pass");
		} else {
			System.out.println("���̽� ���� - fail");
		}

		// 0~1,000,000
		// ��谪 �׽�Ʈ�� �ؾ���
		// -1, 0, 1
		// 999,999 , 1,000,000, 1,000,001

	}

}
