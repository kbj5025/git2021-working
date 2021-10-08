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

	// Autowired ������̼��� �Ű������� �ʵ� Ÿ�Կ� �´� ��ü��
	// Spring���� �����Ͽ� �����Ͽ���(������ ����, ������ü����, DI, Dependency Injection)
	// Repository �������̽� ������ �´� ��ü�� Spring�� �����Ͽ� �־���
	@Autowired
	public ContactController(ContactRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/contacts")
	public List<Contact> getContacts() throws InterruptedException {
		// repository.findAll();
		// SELECT * FROM photo;
		// �⺻������ PK ������(asc, ascending)�ǰ� �ִ� ��Ȳ
		// 1 2 3 .....
//		return repo.findAll();

		// id�÷� ������(clusted index)
		// Sort.by("�����÷�").desceding() ������
		// Sort.by("�����÷�").ascending() ������
		return repo.findAll(Sort.by("id").descending());
	}

	// ��) �������� 2��, 1��° ������
	// ��) GET /photos/paging?page=0&size=2

// �����ȸ
//	@GetMapping(value = "/contacts")
//	public List<Contact> getContacts() {
//		// �� �����
//		return new ArrayList<Contact>(contacts.values());
//	}
	@GetMapping("/contacts/paging")
	public Page<Contact> getContactsPaging(@RequestParam int page, @RequestParam int size) {
		// findAll(Pageable page)
		// findAll(PageRequest.of(page, size, Sort sort));
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	// contact 1�� �߰�
	@PostMapping(value = "/contacts")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) throws InterruptedException {
		// ��
		if (TextProcesser.isEmpyText(contact.getName())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// �±׵� �� �������� ���ڿ�
//		String name = getPlainText(contact.getName());
//		if (name.isEmpty()) {
//			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			return null;
//		}

		// id ���� ����
		// Long currentId = maxId.incrementAndGet();
		// �Է¹��� �����ͷ� contact ��ü�� ����
		// id���� �����Ͻô� �������� ������ ������ ó����

		Contact contactItem = Contact.builder().id(contact.getId()).name(contact.getName())
				.createdTime(new Date().getTime()).build();

		Contact contactSaved = repo.save(contactItem);

		// ���ҽ� ����
		res.setStatus(HttpServletResponse.SC_CREATED);
		// �߰��� ��ü�� ��ȯ
		return contactSaved;
	}

	// contact 1�� ����
	// id���� path variable��
	@DeleteMapping(value = "/contacts/{id}")
	public boolean removeContact(@PathVariable long id, HttpServletResponse res) throws InterruptedException {

		// �ش� id�� ������ 1���� ������
		Optional<Contact> contact = repo.findById(id);
		// �ش� id�� �����Ͱ� ������
		if (contact.isEmpty()) {
			// res.setStatus(404);
			// Not Found : �ش� ��ο� ���ҽ��� ����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		// ��������
		repo.deleteById(id);

		return true;
	}

	// 1�� ����
	// id���� path variable��
	@PutMapping(value = "/contacts/{id}")
	public Contact modifyContact(@PathVariable long id, @RequestBody Contact contact, HttpServletResponse res)
			throws InterruptedException {

		// �ش� id�� �����Ͱ� ������
		Optional<Contact> contactItem = repo.findById(id);
		if (contactItem.isEmpty()) {
			// res.setStatus(404);
			// Not Found : �ش� ��ο� ���ҽ��� ����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		// ������ ���� ����
		// ���� ������ ����ó����
		// if (contact.getName() == null || contact.getName().isEmpty()) {
		// Ŭ���̾�Ʈ���� ���� ���� �����ų� ������ ���� ����
		// Ŭ���̾�Ʈ ����, 4xx
		// ��û���� �߸����� ���� - Bad Request (400)
		// res.setStatus(400);

		// Dispatcher Servlet�� ������ ���䰴ü�� status�ڵ带 �־���

		// Ÿ��Ʋ�� ��
		if (TextProcesser.isEmpyText(contact.getName())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Contact contactToSave = contactItem.get();

		contactToSave.setName(contact.getName());

		// repository.save(entity)
		// id�� ������ UPDATE, ������ INSERT
		// UPDATE
		// SET title=?, descript=?,......
		// WHERE id = ?

		Contact contactSaved = repo.save(contactToSave);

		return contactSaved;
	}

}