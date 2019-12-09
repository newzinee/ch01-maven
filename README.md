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

등록, 수정일을 자동으로 넣어주고 싶다면, @CreationTimestamp, @UpdateTimestamp 사용

책에 나온 org.hibernate.cfg.ImprovedNamingStrategy 를 사용해서, 이름을 자바의 카멜 표기법에서 언더스코어 표기법으로 하는 매핑은 hibernate에서 deprecated 됨.

---

Member 클래스에 테이블 제약조건 추가(사실, auto-ddl일때만 유효)

---

키 할당 전략 
1. IDENTITY : 기본 키 생성을 DB에 위임. 예를 들어 MySQL의 AUTO_INCREMENT 쓸때 사용. 
    - @GeneratedValue(strategy = GenerationType.IDENTITY)
2. SEQUENCE : 시퀀스 사용하는 DB에서 사용 가능. 오라클, H2 등
    - 시퀀스 생성(H2): create sequence BOARD_SEQ start with 1 increment by 1;
    - @SequenceGenerator(name, sequenceName, initialValue, allocationSize)
    - @GeneratedValue(strategy, generator)
    - @GeneratedValue`generator` = @SequenceGenerator `name` 
    - @SequenceGenerator `sequenceName` DB 시퀀스명 쓰기 
3. TABLE : 시퀀스 테이블을 임의로 생성해서 사용. 모든 DB 가능
    ```roomsql
   create table MY_SEQUENCES (
       sequence_name varchar(255) not null, -- 시퀀스 명
       next_val bigint,                     -- 시퀀스 값
       primary key (sequence_name)
   )
   ```
   - @TableGenerator(name, table, pkColumnValue, allocationSize)
   - @GeneratedValue(strategy, generator)
   - `table` 시퀀스 명(sequence_name)컬럼에 `pkColumnValue` 이름으로 row 생성
   - @TableGenerator의 다른 속성
        - pkColumnName: 시퀀스 컬럼명(sequence_name)
        - valueColumnName: 시퀀스 값 컬럼명(next_val) 
4. AUTO : DB에 따라 자동 매치. 오라클-sequence, Mysql-identity...

---

@Transient : 이 필드는 매핑하지 않음. DB에 저장 안되고 조회도 안됨. 객체에 임시보관하고 싶을때 사용

@Access : JPA가 엔티티 데이터에 접근하는 방식 설정(field, property)
- `getXxx()`로 만들면, XXX로 컬럼명 생성(hbm2ddl.auto)해서 return 값을 table에 넣어줌
- 주의! `setXxx(param)`도 만들어줘야함. 안써도 만들어줘야함.
- `set` 메소드 param의 type은 안 맞춰도 되는..듯? 그래도 맞추는게 좋겠..지?