package exercise;

public class AccountExample {

	public static void main(String[] args) {
		Account account = new Account();

		account.setBalance(10000);

		if (account.getBalance() == 10000) {
			System.out.println("케이스 통과 - pass");
		} else {
			System.out.println("케이스 실패 - fail");
		}

		// 예상결과값
		int expectedValue = account.getBalance();
		account.setBalance(-100);
		if (account.getBalance() == expectedValue) {
			System.out.println("케이스 통과 - pass");
		} else {
			System.out.println("케이스 실패 - fail");
		}

		// 0~1,000,000
		// 경계값 테스트를 해야함
		// -1, 0, 1
		// 999,999 , 1,000,000, 1,000,001

	}

}
