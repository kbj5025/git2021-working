package com.git.myworkspace.contact;

import org.springframework.data.jpa.repository.JpaRepository;

//contact 테이블에 접근하는 객체

//ContactRepository -▷ JpaRepository -▷ PagingAndSortingRepository -▷ CrudRepository
//JpaRepository에는 데이터 처리를 위한 기본적인 메서드들이 선언되어있음
//JpaRepository<Contact, Long>
//JpaRepository<엔티티타입, id타입>
//엔티티(SE, 데이터객체) == 테이블(DB, 데이터객체)

//Contact 테이블에 접근할 수 있는 기본적인 메서드들을 사용할 수 있음

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
