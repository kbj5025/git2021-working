package exercise;

public class MemberService {

	// login(Member)
	// Member 객체를 메서드 파라미터로 받음
	boolean login(Member member) {
		if (member.id == "hong" && member.password == "12345") {
			return true;
		}
		return false;
	}

	// 오버로딩: 메서드 이름은 동일하고 매개변수의 타입, 개수 , 순서 달라야함
	// login(String, String)
	boolean login(String id, String password) {
		if (id == "hong" && password == "12345") {
			return true;
		} else {
			return false;
		}

	}

	void logout(String id) {
		System.out.println("로그아웃 되었습니다");
	}

}
