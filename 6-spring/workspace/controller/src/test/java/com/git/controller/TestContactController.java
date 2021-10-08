package com.git.controller;

//값이 같은지를 비교하는 static 메서드
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

	// test case : 할 일 추가
	// event flow(처리흐름): 할 일 1건을 추가함
	// pre-condition(사전조건): 따로없음
	// expected result(예상결과): 할 일 목록에 추가한 데이터가 존재해야함

	@Test
	void addContact() {
		ContactController controller = new ContactController();
		// given
		// 테스트 데이터 및 객체 준비
		Contact expected = Contact.builder().name("test").phone(0).build();
		// when
		// 테스트 케이스의 event flow를 수행
		controller.addContact(expected, new MockHttpServletResponse());
		// then
		// 예상 결과와 실체결과를 비교
		// 전체 목록에 추가한애를 가져옴
		List<Contact> contacts = controller.getContacts();
		Contact actual = contacts.get(0); // arrayList.get(인덱스)

		// 예상결과 데이터와 실제 데이터를 비교함
		assertEquals(1, actual.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getPhone(), actual.getPhone());
	}
	// test case : 할 일 삭제
	// event flow(처리흐름): 할 일 1건을 삭제함
	// pre-condition(사전조건): 할 일 데이터 최소 1건 이상 있어야함
	// expected result(예상결과): 할 일 목록에 삭제한 데이터가 존재하면 안 됨

	@Test
	void removeContact() {
		// given
		// 테스트 데이터 및 객체 준비 , 사전 조건이 있다면 사전조건을 준비하는 단계
		// 여기선 1건 추가가 되어있어야함
		ContactController controller = new ContactController();

		Contact testItem = Contact.builder().name("test").phone(0).build();
		controller.addContact(testItem, new MockHttpServletResponse());
		List<Contact> beforeContacts = controller.getContacts();
		assertEquals(1, beforeContacts.size()); // 삭제전에는 목록 크기가 1
		// when
		// 테스트 케이스의 event flow를 수행
		controller.removeContact(1, new MockHttpServletResponse()); // id가 1인 contact 1건을 삭제
		// then
		// 예상결과와 실제결과를 비교
		// 목록을 조회했을 때 목록의 크기가 0이어야함
		List<Contact> afterContacts = controller.getContacts();
		assertEquals(0, afterContacts.size()); // 삭제후에는 목록 크기가 0
	}
	// test case : 할 일 수정
	// event flow(처리흐름):
	// basic flow(정상흐름):
	// 1. 할 일 1건에 대해서 메모값을 수정함
	// alternative flow(예외흐름): 1. 목록에 없는 id값으로 삭제를 실행 - 404 2. 메모값을 빈 값 또는 데이터 객체를
	// 안보냈음 - 400
	// pre-condition(사전조건): 할 일 데이터 최소 1건 이상 있어야함
	// expected result(예상결과): 할 일 목록에 수정한 메모값으로 출력하여야함

	@Test
	void modifyContact() {

		ContactController controller = new ContactController();

		Contact testItem = Contact.builder().name("test").phone(0).build();
		controller.addContact(testItem, new MockHttpServletResponse());

		// 변경할 테스트 데이터
		String expectedResult = "modify test name";
		int expectedResult1 = 010 - 0000 - 0000;
		Contact modifyData = Contact.builder().name(expectedResult).phone(expectedResult1).build();

		HttpServletResponse res = new MockHttpServletResponse();

		// when : basic flow -정상적인 상황
		// 테스트 케이스의 event flow를 수행 // id가 1인 contact name를 수정
		controller.modifyContact(1, modifyData, res);

		// then - 예상결과와 실제결과를 비교
		// 목록을 조회했을 때 해당 아이템의 값이 예상결과와 일치해야함
		List<Contact> contacts = controller.getContacts();
		assertEquals(expectedResult, contacts.get(0).getName());

		// altanative flow - 1. id값이 없는 경우
		// when - id를 2로 수정해봄
		Contact resultContactId = controller.modifyContact(2, modifyData, res);
		// then - 예상결과와 실제결과를 비교
		// 반환 객체가 null, Status code 404
		assertNull(resultContactId);
		assertEquals(HttpServletResponse.SC_NOT_FOUND, res.getStatus());

		// when : altanative flow - 2-1. 값이 null인 경우
		Contact resultContactNameNull = controller.modifyContact(1, new Contact(), res);
		// then - 예상결과와 실제결과를 비교
		// 반환 객체가 null, Status code 400
		assertNull(resultContactNameNull);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());

		// when : altanative flow - 2-2. 메모값이 빈값("")인 경우
		Contact resultContactNameEmpty = controller.modifyContact(1, Contact.builder().name("").build(), res);
		// then - 예상결과와 실제결과를 비교
		// 반환 객체가 null, Status code 400
		assertNull(resultContactNameEmpty);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());
	}
}
