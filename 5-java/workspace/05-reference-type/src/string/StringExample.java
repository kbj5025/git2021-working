package string;

public class StringExample {

	public static void main(String[] args) {
		// 자바에서는 문자열 쌍따옴표만 가능
		String name1 = "고봉준";
		String name2 = "고봉준";
		System.out.println(name1 == name2);

		String name3 = new String("고봉준");
		String name4 = new String("고봉준");
		System.out.println(name3 == name4);// 동치성 비교에 이방법 쓰지말것 !
		// !! String인 경우 동치 비교에 eqaul함수를 사용
		System.out.println(name3.equals(name4));
	}

}
