package singleton;

public class CalculatorExample {

	public static void main(String[] args) {
//		Calculator calc = new Calculator();
		// �̹� �����Ǿ��ִ� Calculator ��ü�� �����ͼ� ���
		Calculator calc = Calculator.getInstancce();
		System.out.println(calc);
		calc.getAreaCircle(5);
		calc.plus(10, 5);

	}

}
