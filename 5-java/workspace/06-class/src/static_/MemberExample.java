package static_;

public class MemberExample {

	public static void main(String[] args) {

		// static �޼��忡 �����ϴ� ����� Ŭ������,�޼����(...)
		System.out.println(Member.serviceName);
		Member.printServiceName();

		// �̸�, id�� �Ű������� �޾Ƽ� ��ü ����
		// �����ڸ� ����
		Member member1 = new Member("ȫ�浿", "hong");
		Member member2 = new Member("�����", "bong");

//		System.out.println(Member.serviceName + "�̸� " + member1.name);
//		System.out.println(Member.serviceName + "�̸� " + member2.name);

		Member.printNameWithServiceName(member1.name);
		Member.printNameWithServiceName(member2.name);
	}

}
