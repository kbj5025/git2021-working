package static_;

public class Member {
	// static 필드
	// Member 클래스 객체에서 모두 사용할 수 있는 변수 값
	static String serviceName = "배달의 민족";

	String name;
	String id;
	String password;
	int age;

	// 생성자를 선언
	// 이름과 id를 초기화하는 생성자
	Member(String name, String id) {
		this.name = name;
		this.id = id;
	}

	// static 메서드
	// 객체 생성없이 호출해서 사용할 수 있는 메서드
	static void printServiceName() {
		// static 변수에는 this 사용 불가
		// this는 생성된 객체를 가르키기 때문

		// static 변수는 객체 공간이 아닌 클래스 공간(메서드 공간)에 생성됨
		System.out.println(serviceName);
	}

	// static 메서드에는 non-static 필드를 읽을 수 없음 -> this.name X
	// 매개변수로 값을 받아서 처리해야함
	static void printNameWithServiceName(String name) {
		System.out.println(serviceName + ": " + name);
	}
}
