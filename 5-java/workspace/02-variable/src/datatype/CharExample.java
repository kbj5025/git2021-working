package datatype;

public class CharExample {

	public static void main(String[] args) {

		char c1 = 'A'; // ���ڸ� ���� ����
		char c2 = 65; // 10���� �ڵ尪
		char c3 = '\u0041'; // 16���� �����ڵ�

		char c4 = '��';
		char c5 = 44032;
		char c6 = '\uac00';

		int unicodeA = c1;
		int unicodeGa = c4;
		System.out.println(unicodeA);
		System.out.println(unicodeGa);
	}

}