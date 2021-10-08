package com.git.myworkspace.contact;

import org.springframework.data.jpa.repository.JpaRepository;

//contact ���̺� �����ϴ� ��ü

//ContactRepository -�� JpaRepository -�� PagingAndSortingRepository -�� CrudRepository
//JpaRepository���� ������ ó���� ���� �⺻���� �޼������ ����Ǿ�����
//JpaRepository<Contact, Long>
//JpaRepository<��ƼƼŸ��, idŸ��>
//��ƼƼ(SE, �����Ͱ�ü) == ���̺�(DB, �����Ͱ�ü)

//Contact ���̺� ������ �� �ִ� �⺻���� �޼������ ����� �� ����

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
