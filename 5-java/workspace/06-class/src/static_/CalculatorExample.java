package static_;

public class CalculatorExample {

	public static void main(String[] args) {
		// �ν��Ͻ�(��ü) ���� ���ϰ� �ٷ� ��밡��
		// �ַ� ���� ���� ���̳� ��ɵ��� static���� �����Ͽ� ���
		System.out.println(Calculator.pi);
		System.out.println(Calculator.plus(10, 5));

		// static ���� ���� ���� ������
		// Calculator.pi = 3.14;

		// ������ �ϴ� But
		// ��ü�� �����ؼ� ���ٴ°� ū �ǹ̰� ����
//		Calculator calc = new Calculator();

	}

}
