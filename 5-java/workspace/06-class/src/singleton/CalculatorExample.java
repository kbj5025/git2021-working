package singleton;

public class CalculatorExample {

	public static void main(String[] args) {
//		Calculator calc = new Calculator();
		// 이미 생성되어있는 Calculator 객체를 가져와서 사용
		Calculator calc = Calculator.getInstancce();
		System.out.println(calc);
		calc.getAreaCircle(5);
		calc.plus(10, 5);

	}

}
