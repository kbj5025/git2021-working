package method;

public class Student {

	String name;
	int age;
	int semester;
	String major;

	// �����ڴ� ��ȯ������ ����
	// Ŭ���� �̸��� ����(�빮�ڷ� ����)
	// ������ �޼���� camel-case
	Student() {

	}

	Student(String name, int age, int semester, String major) {

		this.name = name;
		this.age = age;
		this.semester = semester;
		this.major = major;

	}

	// void ��ȯ������ ����
	// ��ȯ���� �޼����() {...}
	void printPersonInfo() {
		System.out.println(this.name + " " + this.age);
	}

	// ���� �����͸� �������� �޼���� get.... �̷�����
	String getMajorInfo() {
		return this.major + ", �б�: " + this.semester;
	}
}
