package com.git.myworkspace.contact;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.git.myworkspace.lib.TextProcesser;

@RestController
public class ContactController {
	private ContactRepository repo;

	// Autowired 어노테이션은 매개변수나 필드 타입에 맞는 객체를
	// Spring에서 생성하여 주입하여줌(의존성 주입, 의존객체주입, DI, Dependency Injection)
	// Repository 인터페이스 구조에 맞는 객체를 Spring에 생성하여 넣어줌
	@Autowired
	public ContactController(ContactRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/contacts")
	public List<Contact> getContacts() throws InterruptedException {
		// repository.findAll();
		// SELECT * FROM photo;
		// 기본적으로 PK 순정렬(asc, ascending)되고 있는 상황
		// 1 2 3 .....
//		return repo.findAll();

		// id컬럼 역정렬(clusted index)
		// Sort.by("정렬컬럼").desceding() 역정렬
		// Sort.by("정렬컬럼").ascending() 순정렬
		return repo.findAll(Sort.by("id").descending());
	}

	// 예) 한페이지 2개, 1번째 페이지
	// 예) GET /photos/paging?page=0&size=2

// 목록조회
//	@GetMapping(value = "/contacts")
//	public List<Contact> getContacts() {
//		// 맵 값목록
//		return new ArrayList<Contact>(contacts.values());
//	}
	@GetMapping("/contacts/paging")
	public Page<Contact> getContactsPaging(@RequestParam int page, @RequestParam int size) {
		// findAll(Pageable page)
		// findAll(PageRequest.of(page, size, Sort sort));
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	// contact 1건 추가
	@PostMapping(value = "/contacts")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) throws InterruptedException {
		// 빈값
		if (TextProcesser.isEmpyText(contact.getName())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 태그들 다 지웠더니 빈문자열
//		String name = getPlainText(contact.getName());
//		if (name.isEmpty()) {
//			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			return null;
//		}

		// id 값을 생성
		// Long currentId = maxId.incrementAndGet();
		// 입력받은 데이터로 contact 객체를 생성
		// id값과 생성일시는 서버에서 생성한 것으로 처리함

		Contact contactItem = Contact.builder().id(contact.getId()).name(contact.getName())
				.createdTime(new Date().getTime()).build();

		Contact contactSaved = repo.save(contactItem);

		// 리소스 생성
		res.setStatus(HttpServletResponse.SC_CREATED);
		// 추가된 객체를 반환
		return contactSaved;
	}

	// contact 1건 삭제
	// id값이 path variable로
	@DeleteMapping(value = "/contacts/{id}")
	public boolean removeContact(@PathVariable long id, HttpServletResponse res) throws InterruptedException {

		// 해당 id의 데이터 1건을 가져옴
		Optional<Contact> contact = repo.findById(id);
		// 해당 id의 데이터가 없으면
		if (contact.isEmpty()) {
			// res.setStatus(404);
			// Not Found : 해당 경로에 리소스가 없음
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		// 삭제수행
		repo.deleteById(id);

		return true;
	}

	// 1건 수정
	// id값이 path variable로
	@PutMapping(value = "/contacts/{id}")
	public Contact modifyContact(@PathVariable long id, @RequestBody Contact contact, HttpServletResponse res)
			throws InterruptedException {

		// 해당 id의 데이터가 없으면
		Optional<Contact> contactItem = repo.findById(id);
		if (contactItem.isEmpty()) {
			// res.setStatus(404);
			// Not Found : 해당 경로에 리소스가 없음
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		// 데이터 검증 로직
		// 값이 없으면 에러처리함
		// if (contact.getName() == null || contact.getName().isEmpty()) {
		// 클라이언트에서 값이 없이 보내거나 빈값으로 보낸 것임
		// 클라이언트 오류, 4xx
		// 요청값을 잘못보낸 것임 - Bad Request (400)
		// res.setStatus(400);

		// Dispatcher Servlet이 생성한 응답객체에 status코드를 넣어줌

		// 타이틀이 빈값
		if (TextProcesser.isEmpyText(contact.getName())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Contact contactToSave = contactItem.get();

		contactToSave.setName(contact.getName());

		// repository.save(entity)
		// id가 있으면 UPDATE, 없으면 INSERT
		// UPDATE
		// SET title=?, descript=?,......
		// WHERE id = ?

		Contact contactSaved = repo.save(contactToSave);

		return contactSaved;
	}

}