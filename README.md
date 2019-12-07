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
