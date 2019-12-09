package start;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
//            logic(em);
//            sequenceGenerator(em);
            tableGenerator(em);
            tx.commit();
        } catch(Exception e ) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void tableGenerator(EntityManager em) {
        Memo memo = new Memo();
        em.persist(memo);
        System.out.println("memo: " + memo.getId());
        memo.setTitle("title " + memo.getId() );
    }

    private static void logic(EntityManager em) {
        String id = "id2";

        Member member = new Member();
        member.setId(id);
        member.setUsername("yoojin");
        member.setAge(20);

        // 1. 등록
        em.persist(member);
        System.out.println("memberid: " + member.getId());


        // 2. 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember: " + findMember.getId() + ", " + findMember.getUsername() + ", " + findMember.getAge());

        // 3. 수정
        member.setAge(30);  // update sql을 사용하지 않아도, DB값 바뀜

        Member findMember2 = em.find(Member.class, id);
        System.out.println("findMember2: " + findMember2.getId() + ", " + findMember2.getUsername() + ", " + findMember2.getAge());

        // 4. 목록 조회 JPQL - 여기서 Member는 DB 테이블이 아니라 Entity 객체..!
        List<Member> members = em.createQuery("select m from start.Member as m", Member.class).getResultList();
        System.out.println("members.size: " + members.size());

        // 5. 삭제
        em.remove(member);
    }

    private static void sequenceGenerator(EntityManager em) {
        Board board = new Board();
        System.out.println("board.getId1: "+board.getId()); // null
        em.persist(board);
        System.out.println("board.getId2: "+board.getId());

        board.setData("data " + board.getId());

    }
}
