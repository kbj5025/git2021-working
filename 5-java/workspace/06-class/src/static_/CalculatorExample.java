package static_;

public class CalculatorExample {

	public static void main(String[] args) {
		// 인스턴스(객체) 생성 안하고 바로 사용가능
		// 주로 자주 쓰는 값이나 기능들을 static으로 정의하여 사용
		System.out.println(Calculator.pi);
		System.out.println(Calculator.plus(10, 5));

		// static 변수 값은 수정 가능함
		// Calculator.pi = 3.14;

		// 가능은 하다 But
		// 객체를 생성해서 쓴다는게 큰 의미가 없음
//		Calculator calc = new Calculator();

	}

}
