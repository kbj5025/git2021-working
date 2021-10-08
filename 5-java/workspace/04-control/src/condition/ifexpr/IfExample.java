package condition.ifexpr;

public class IfExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String empty = "";

		// Java 허용안됨
//		if(empty) {
//			
//		}

		// boolean 값의 연산만 if문의 조건식으로 올 수 있음
		int a = 11;
		if (a > 9) {
			System.out.println(a);
		}
		if (true) {
			System.out.println(a);
		}

	}

}
