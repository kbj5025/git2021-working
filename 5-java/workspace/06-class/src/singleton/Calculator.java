package singleton;

// ��ü�� ���� Ŭ������ �ƴ�
// �޼��带 ������� �������� �����ϴ� Ŭ����

// �̱��� Ŭ������ ����� �ܺο��� ��ü ������ ���ϰ� ��
public class Calculator {
	// 2. private static �ʵ�� ��ü�� ������ <- ���α׷��� ����� �� ���� �ʱ�ȭ�� �Ͼ
	private static Calculator calc = new Calculator();

	private final static double PI = 3.141592;

	// 1. �⺻�����ڸ� �ܺο��� ���� ���ϰ� ��
	private Calculator() {
	}

	// 3. �ܺο��� private static���� ������ ��ü�� ������ �� �ְ���
	public static Calculator getInstancce() {
		return calc;
	}

	int plus(int a, int b) {
		return a + b;
	}

	int minus(int a, int b) {
		return a - b;

	}
	// �̱���(singleton)
	// ���α׷� ���ο� ������ ��ü�� ������ ���� ��ü ���� ����

	public void getAreaCircle(int i) {
		// TODO Auto-generated method stub

	}

}
