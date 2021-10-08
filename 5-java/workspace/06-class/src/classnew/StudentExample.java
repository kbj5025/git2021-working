package classnew;

public class StudentExample {

	public static void main(String[] args) {
		// new 클래스명()
		// : 인스턴스를 생성
		// -> 클래스 구조의 메모리 공간을 생성
		// 인스턴스 == 객체

		// Student s1 = 생성된 인스턴스가 대입이됨
		// 클래스명 인스턴스 변수 = new 클래스명()

		// 홍길동이라는 학생에 대한 정보
		Student s1 = new Student();
		// 필드 : 학생의 데이터
		// 필드에 접근 : 인스턴스변수명.필드명
		s1.name = "홍길동";
		s1.age = 20;
		s1.semester = 2;
		s1.major = "경제학";
		System.out.println(s1.name + s1.age);

		// 메서드 : 홍길동이라는 학생의 수강신청 기능
		// 메서드에 접근: 인스턴스변수명.메서드명
		s1.joinCourse();

		Student s2 = new Student();
		s2.name = "홍동";
		s2.age = 30;
		s2.semester = 3;
		s2.major = "전기공학";
		System.out.println(s2.name + " " + s2.age);

		s2.joinCourse();
	}

}
