package string;

public class StringExample {

	public static void main(String[] args) {
		// �ڹٿ����� ���ڿ� �ֵ���ǥ�� ����
		String name1 = "�����";
		String name2 = "�����";
		System.out.println(name1 == name2);

		String name3 = new String("�����");
		String name4 = new String("�����");
		System.out.println(name3 == name4);// ��ġ�� �񱳿� �̹�� �������� !
		// !! String�� ��� ��ġ �񱳿� eqaul�Լ��� ���
		System.out.println(name3.equals(name4));
	}

}
