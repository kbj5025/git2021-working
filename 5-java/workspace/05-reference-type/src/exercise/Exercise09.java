package exercise;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		boolean run = true;

		int studentNum = 0;
		int[] scores = null;

		Scanner scanner = new Scanner(System.in); // System.int(standard input sctream, Ű���� �Է�)

		while (run) {
			System.out.println("-------------------");
			System.out.println("1.�л���|2.�����Է�|3.��������Ʈ|4.�м�|5.����");
			System.out.println("-------------------");
			System.out.println("����> ");

			// ���� �Է°��� �ܼ�â���� �Է� ����
			int selectNo = scanner.nextInt();

			switch (selectNo) {
			case 1:
				System.out.println("�л���> ");
				// �л����� �Է¹���
				// �Է��� �л��� ��ŭ �迭ũ�⸦ �ʱ�ȭ
				scores = new int[scanner.nextInt()];
				break;
			case 2:
				// �迭ũ�⸸ŭ �ݺ��ؼ� ������ �Է� ����
				for (int i = 0; i < scores.length; i++) {
					System.out.println("scores[" + i + "]>");
					scores[i] = scanner.nextInt();
				}
				break;
			case 3:
				// �迭ũ�⸸ŭ �ݺ��ؼ� ���� ����� ���
				for (int i = 0; i < scores.length; i++) {
					System.out.println("scores[" + i + "]: " + scores[i]);
				}
				break;
			case 4:
				int sum = 0;
				int max = 0;
				for (int score : scores) {
					sum += score;
					max = score > max ? score : max;
				}
				// �ְ����� ������� ���
				System.out.println("�ְ� ����:" + max);
				System.out.println("��� ����:" + sum * 1.0 / scores.length);
				break;
			case 5:
				// run false�� �ݺ��� Ż��
				run = false;
				break;
			}
		}
		System.out.println("���α׷� ����");
	}

}
