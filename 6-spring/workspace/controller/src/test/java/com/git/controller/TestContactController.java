package com.git.controller;

//���� �������� ���ϴ� static �޼���
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.git.controller.contact.Contact;
import com.git.controller.contact.ContactController;

@SpringBootTest
public class TestContactController {

	// test case : �� �� �߰�
	// event flow(ó���帧): �� �� 1���� �߰���
	// pre-condition(��������): ���ξ���
	// expected result(������): �� �� ��Ͽ� �߰��� �����Ͱ� �����ؾ���

	@Test
	void addContact() {
		ContactController controller = new ContactController();
		// given
		// �׽�Ʈ ������ �� ��ü �غ�
		Contact expected = Contact.builder().name("test").phone(0).build();
		// when
		// �׽�Ʈ ���̽��� event flow�� ����
		controller.addContact(expected, new MockHttpServletResponse());
		// then
		// ���� ����� ��ü����� ��
		// ��ü ��Ͽ� �߰��Ѿָ� ������
		List<Contact> contacts = controller.getContacts();
		Contact actual = contacts.get(0); // arrayList.get(�ε���)

		// ������ �����Ϳ� ���� �����͸� ����
		assertEquals(1, actual.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getPhone(), actual.getPhone());
	}
	// test case : �� �� ����
	// event flow(ó���帧): �� �� 1���� ������
	// pre-condition(��������): �� �� ������ �ּ� 1�� �̻� �־����
	// expected result(������): �� �� ��Ͽ� ������ �����Ͱ� �����ϸ� �� ��

	@Test
	void removeContact() {
		// given
		// �׽�Ʈ ������ �� ��ü �غ� , ���� ������ �ִٸ� ���������� �غ��ϴ� �ܰ�
		// ���⼱ 1�� �߰��� �Ǿ��־����
		ContactController controller = new ContactController();

		Contact testItem = Contact.builder().name("test").phone(0).build();
		controller.addContact(testItem, new MockHttpServletResponse());
		List<Contact> beforeContacts = controller.getContacts();
		assertEquals(1, beforeContacts.size()); // ���������� ��� ũ�Ⱑ 1
		// when
		// �׽�Ʈ ���̽��� event flow�� ����
		controller.removeContact(1, new MockHttpServletResponse()); // id�� 1�� contact 1���� ����
		// then
		// �������� ��������� ��
		// ����� ��ȸ���� �� ����� ũ�Ⱑ 0�̾����
		List<Contact> afterContacts = controller.getContacts();
		assertEquals(0, afterContacts.size()); // �����Ŀ��� ��� ũ�Ⱑ 0
	}
	// test case : �� �� ����
	// event flow(ó���帧):
	// basic flow(�����帧):
	// 1. �� �� 1�ǿ� ���ؼ� �޸��� ������
	// alternative flow(�����帧): 1. ��Ͽ� ���� id������ ������ ���� - 404 2. �޸��� �� �� �Ǵ� ������ ��ü��
	// �Ⱥ����� - 400
	// pre-condition(��������): �� �� ������ �ּ� 1�� �̻� �־����
	// expected result(������): �� �� ��Ͽ� ������ �޸����� ����Ͽ�����

	@Test
	void modifyContact() {

		ContactController controller = new ContactController();

		Contact testItem = Contact.builder().name("test").phone(0).build();
		controller.addContact(testItem, new MockHttpServletResponse());

		// ������ �׽�Ʈ ������
		String expectedResult = "modify test name";
		int expectedResult1 = 010 - 0000 - 0000;
		Contact modifyData = Contact.builder().name(expectedResult).phone(expectedResult1).build();

		HttpServletResponse res = new MockHttpServletResponse();

		// when : basic flow -�������� ��Ȳ
		// �׽�Ʈ ���̽��� event flow�� ���� // id�� 1�� contact name�� ����
		controller.modifyContact(1, modifyData, res);

		// then - �������� ��������� ��
		// ����� ��ȸ���� �� �ش� �������� ���� �������� ��ġ�ؾ���
		List<Contact> contacts = controller.getContacts();
		assertEquals(expectedResult, contacts.get(0).getName());

		// altanative flow - 1. id���� ���� ���
		// when - id�� 2�� �����غ�
		Contact resultContactId = controller.modifyContact(2, modifyData, res);
		// then - �������� ��������� ��
		// ��ȯ ��ü�� null, Status code 404
		assertNull(resultContactId);
		assertEquals(HttpServletResponse.SC_NOT_FOUND, res.getStatus());

		// when : altanative flow - 2-1. ���� null�� ���
		Contact resultContactNameNull = controller.modifyContact(1, new Contact(), res);
		// then - �������� ��������� ��
		// ��ȯ ��ü�� null, Status code 400
		assertNull(resultContactNameNull);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());

		// when : altanative flow - 2-2. �޸��� ��("")�� ���
		Contact resultContactNameEmpty = controller.modifyContact(1, Contact.builder().name("").build(), res);
		// then - �������� ��������� ��
		// ��ȯ ��ü�� null, Status code 400
		assertNull(resultContactNameEmpty);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());
	}
}
