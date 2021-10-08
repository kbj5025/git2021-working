package exam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankApplication {

	// Map �������� ������ Map ������ Ÿ��(Inteface)
	// = HashMap
	// = HashTable
	// = TreeMap
	//
	// �����ϴ� �ڷᱸ���� ���� ���� �޼��带 ȣ���ϴ���
	// �������� ó������� �ٸ�

	// ���¸�� Map ��ü
	// Map<ŰŸ��, ��Ÿ��> ������ = new HashMap<ŰŸ��, ��Ÿ��>();
	private static Map<String, Account> accounts = new HashMap<String, Account>();

	private static Scanner scanner = new Scanner(System.in);

	private static Account account;

	private static Object ano;

	public static void main(String[] args) {
		boolean run = true;
		while (run) {
			System.out.println("----------------------------------------------------------");
			System.out.println("1.���»��� | 2.���¸�� | 3.���� | 4.��� | 5.����");
			System.out.println("----------------------------------------------------------");
			System.out.print("����> ");

			int selectNo = scanner.nextInt();

			if (selectNo == 1) {
				createAccount();
			} else if (selectNo == 2) {
				accountList();
			} else if (selectNo == 3) {
				deposit();
			} else if (selectNo == 4) {
				withdraw();
			} else if (selectNo == 5) {
				run = false;
			}
		}
		System.out.println("���α׷� ����");
	}

	// ���»����ϱ�(�����߰��ϱ�)
	private static void createAccount() {
		System.out.println("--------");
		System.out.println("���»���");
		System.out.println("--------");

		System.out.println("���¹�ȣ : ");
		String accountNum = scanner.next();

		System.out.println("������ : ");
		String accountOwner = scanner.next();

		System.out.println("�ʱ��Աݾ� : ");
		int accountBal = scanner.nextInt();

		Account newAccount = new Account(accountNum, accountOwner, accountBal);
		accounts.put(accountNum, newAccount);

		System.out.println("��� : ���°� �����Ǿ����ϴ�.");
	}

	// ���¸�Ϻ���
	private static void accountList() {

		System.out.println("--------");
		System.out.println("���¸��");
		System.out.println("--------");

		for (String id : accounts.keySet()) {
			String accountNum = accounts.get(id).getOwner();
			String accountOwner = accounts.get(id).getAno();
			int accountBal = accounts.get(id).getBalance();

			System.out.println(accountNum + " " + accountOwner + " " + accountBal);
		}
	}

	// �����ϱ�(�ʵ尪����)
	private static void deposit() {
		System.out.println("--------");
		System.out.println("����");
		System.out.println("--------");

		System.out.println("���¹�ȣ : ");
		String accountNum = scanner.next();

		System.out.println("���ݾ� : ");
		int accountBal = scanner.nextInt();

		Account amount = accounts.get(ano);
		amount.setBalance(amount.getBalance() + accountBal);
		System.out.println("���: ������ �����Ǿ����ϴ�.");
	}

	// ����ϱ�(�ʵ尪����)
	private static void withdraw() {
		System.out.println("--------");
		System.out.println("���");
		System.out.println("--------");

		System.out.println("���¹�ȣ : ");
		String accountNum = scanner.next();

		System.out.println("��ݾ� : ");
		int accountBal = scanner.nextInt();

		account.setBalance(account.getBalance() - accountBal);
		System.out.println("��� : ������ �����Ǿ����ϴ�.");

	}
}