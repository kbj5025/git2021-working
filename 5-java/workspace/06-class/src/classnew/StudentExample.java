package classnew;

public class StudentExample {

	public static void main(String[] args) {
		// new Ŭ������()
		// : �ν��Ͻ��� ����
		// -> Ŭ���� ������ �޸� ������ ����
		// �ν��Ͻ� == ��ü

		// Student s1 = ������ �ν��Ͻ��� �����̵�
		// Ŭ������ �ν��Ͻ� ���� = new Ŭ������()

		// ȫ�浿�̶�� �л��� ���� ����
		Student s1 = new Student();
		// �ʵ� : �л��� ������
		// �ʵ忡 ���� : �ν��Ͻ�������.�ʵ��
		s1.name = "ȫ�浿";
		s1.age = 20;
		s1.semester = 2;
		s1.major = "������";
		System.out.println(s1.name + s1.age);

		// �޼��� : ȫ�浿�̶�� �л��� ������û ���
		// �޼��忡 ����: �ν��Ͻ�������.�޼����
		s1.joinCourse();

		Student s2 = new Student();
		s2.name = "ȫ��";
		s2.age = 30;
		s2.semester = 3;
		s2.major = "�������";
		System.out.println(s2.name + " " + s2.age);

		s2.joinCourse();
	}

}
