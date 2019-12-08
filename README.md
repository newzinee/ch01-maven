자바 ORM 표준 JPA 프로그래밍(김영한) 책 참조 

persistence.xml : JPA 설정 정보 관리. 반드시 **META-INF/persistence.xml**위치에 존재해야 한다. 

persistence-unit에서 정해준 이름을 EntityManagerFactory에 가져다 쓴다. 
일반적으로 DB당 하나 등록  

    <persistence-unit name="jpabook">


EntityManagerFactory 는 애플리케이션 전체에서 한 번만 생성.

EntityManger를 이용해서 DB에 접근, 통신하기 때문에 스레드 간 공유, 재사용 절대 금지 

JPA는 항상 트랜잭션 안에서 데이터 변경이 이뤄져야 함.

JPQL은 SQL을 추상화한 객체지향 쿼리 언어. DB랑 상관 없이 엔티티 객체를 대상으로 쿼리.

--- 

내 기준에서 이 프로젝트에서 눈 여겨 봐야할 점은 **SQL을 사용하지 않았다**는 점이다. 


---

hibernate orm 5.3.14.Final document: https://docs.jboss.org/hibernate/stable/orm/userguide/html_single/Hibernate_User_Guide.html

@Temporal(TemporalType.TIMESTAMP) 는 java.util.Date 나 java.util.Calendar 쓸 때 사용

java8 시간인 java.time.LocalDateTime 쓰고 싶다면,  **@org.hibernate.annotations.Type(type = "LocalDateTime")**

책에 나온 org.hibernate.cfg.ImprovedNamingStrategy 를 사용해서, 이름을 자바의 카멜 표기법에서 언더스코어 표기법으로 하는 매핑은 hibernate에서 deprecated 됨.
