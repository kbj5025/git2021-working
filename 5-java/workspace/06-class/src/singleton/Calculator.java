package singleton;

// 객체로 찍어내는 클래스가 아님
// 메서드를 기능적인 관점에서 실행하는 클래스

// 싱글턴 클래스로 만들어 외부에서 객체 생성을 못하게 함
public class Calculator {
	// 2. private static 필드로 객체를 생성함 <- 프로그램이 실행될 때 변수 초기화가 일어남
	private static Calculator calc = new Calculator();

	private final static double PI = 3.141592;

	// 1. 기본생성자를 외부에서 접근 못하게 함
	private Calculator() {
	}

	// 3. 외부에서 private static으로 생성한 객체를 접근할 수 있게함
	public static Calculator getInstancce() {
		return calc;
	}

	int plus(int a, int b) {
		return a + b;
	}

	int minus(int a, int b) {
		return a - b;

	}
	// 싱글턴(singleton)
	// 프로그램 내부에 유일한 객체를 가지기 위한 객체 생성 패턴

	public void getAreaCircle(int i) {
		// TODO Auto-generated method stub

	}

}
