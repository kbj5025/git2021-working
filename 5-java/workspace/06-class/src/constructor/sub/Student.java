package constructor.sub;

public class Student {

	String name;
	int age;
	int semester;
	String major;

	// 오버로딩
	// : 메서드 이름 + 매개 변수
	// 매서드 이릉믄 동일하고 매개변수의 개수, 타입 , 순서가
	// 다른 메서드를 선언하는 것
	// 메서드 시그니처

	// 객체지향 프로그래밍의 다형성 원리를 적용
	// 다형성 = 다양한 형태를 가진다.
	// 메서드 오버로딩
	// = 객체의 메서드가 다양한 형태를 가지는 것

	// 매개변수가 없는 기본 생성자는 클래스에 내장됨
	// 생성자
	// 객체 생성시 초기화 역할 담당
	// 클래스명과 동일한(대문자로 시작하는 메서드)
	public Student() {
		// 아무것도 처리안 함
		// 객체 생성만 함
	}

	// 생성자를 임의로 만들면, 기본생성자는 제거됨
	// 이름과 나이를 매개변수로 받아서
	// 객체(인스턴스)를 생성하는 생성자 메서드
	Student(String name, int age) {
		// this.필드
		// 만들어질 객체의 필드에 접근
		this.name = name;
		this.age = age;
	}

	void joinCourse() {

	}
}
