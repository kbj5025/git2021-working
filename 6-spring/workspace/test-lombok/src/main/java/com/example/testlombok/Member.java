package com.example.testlombok;

import lombok.Data;

// 롬복 플러그인이 java 코드를 컴파일할때(저장할때)
// 롬복 어노테이션들(예-@Data)이있는 클래스/인터페이스,필드을 탐색
// getter, setter, equals/hashcode, toString 메서드를
// 컴파일되는 class파일에 추가해줌
@Data 
public class Member {
	private int id;
	private String name;
}
