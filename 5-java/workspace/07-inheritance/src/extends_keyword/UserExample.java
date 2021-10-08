package extends_keyword;

public class UserExample {

	public static void main(String[] args) {
		// 일반사용자
		User user = new User();
		user.setId("hong");
		user.setName("홍길동");
		user.setPhone("0101223456");

		// 관리자
		Admin admin = new Admin();
		admin.setId("bong");
		admin.setName("고봉준");
		admin.setPhone("0103423456");

	}

}
