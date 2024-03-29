package array;

public class CreateByNewExample {

	public static void main(String[] args) {
		// new 키워드를 이용하여 생성
		int[] arrInt = new int[3]; // 크기가 3인 int 배열 생성
		// 0 초기화 되어있음
		System.out.println(arrInt[0]);
		System.out.println(arrInt[1]);
		System.out.println(arrInt[2]);

		for (int i = 0; i < 3; i++) {
			System.out.println(arrInt[i]);
		}
		String[] arrStr = new String[3];
		// 크기가 3인 String 배열 생성
		// null 초기화 되어있음
		System.out.println(arrStr[0]);
		System.out.println(arrStr[1]);
		System.out.println(arrStr[2]);

		// 향상된 for-문
		// for(요소변수타입 요소변수 : 배열변수명){
		// }
		for (String str : arrStr) {
			str = "test";
			System.out.println(str);
		}
		// 배열 크기
		System.out.println(arrStr.length);
	}

}
