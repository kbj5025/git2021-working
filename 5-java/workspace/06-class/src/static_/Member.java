package static_;

public class Member {
	// static �ʵ�
	// Member Ŭ���� ��ü���� ��� ����� �� �ִ� ���� ��
	static String serviceName = "����� ����";

	String name;
	String id;
	String password;
	int age;

	// �����ڸ� ����
	// �̸��� id�� �ʱ�ȭ�ϴ� ������
	Member(String name, String id) {
		this.name = name;
		this.id = id;
	}

	// static �޼���
	// ��ü �������� ȣ���ؼ� ����� �� �ִ� �޼���
	static void printServiceName() {
		// static �������� this ��� �Ұ�
		// this�� ������ ��ü�� ����Ű�� ����

		// static ������ ��ü ������ �ƴ� Ŭ���� ����(�޼��� ����)�� ������
		System.out.println(serviceName);
	}

	// static �޼��忡�� non-static �ʵ带 ���� �� ���� -> this.name X
	// �Ű������� ���� �޾Ƽ� ó���ؾ���
	static void printNameWithServiceName(String name) {
		System.out.println(serviceName + ": " + name);
	}
}
