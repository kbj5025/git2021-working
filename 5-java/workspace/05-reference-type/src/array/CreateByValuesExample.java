package array;

public class CreateByValuesExample {

	public static void main(String[] args) {
		// 정수형 배열 선언 및 초기화
		// 배열타입[] 배열변수명 = {... 초기화 목록}
		int[] scores = { 83, 90, 87 };

		// !! Java의 배열은 처음 만들어진 크기가 고정됨
		System.out.println(scores[0]);
		System.out.println(scores[1]);
		System.out.println(scores[2]);

		// 배열 오소의 추가, 삭제 불가능함 변경만 가능
		scores[0] = 100;
		System.out.println(scores[0]);
		System.out.println(scores[1]);
		System.out.println(scores[2]);

		scores[0] = 0; // 기본형 데이터 타입은 null 처리가 불가능함
	}

}
