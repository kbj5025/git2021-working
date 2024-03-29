package static_;

public class MemberExample {

	public static void main(String[] args) {

		// static 메서드에 접근하는 방법은 클래스명,메서드명(...)
		System.out.println(Member.serviceName);
		Member.printServiceName();

		// 이름, id를 매개변수로 받아서 객체 생성
		// 생성자를 선언
		Member member1 = new Member("홍길동", "hong");
		Member member2 = new Member("고봉준", "bong");

//		System.out.println(Member.serviceName + "이름 " + member1.name);
//		System.out.println(Member.serviceName + "이름 " + member2.name);

		Member.printNameWithServiceName(member1.name);
		Member.printNameWithServiceName(member2.name);
	}

}
