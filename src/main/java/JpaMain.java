import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch(Exception e ) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(EntityManager em) {
        String id = "id1";

        Member member = new Member();
        member.setId(id);
        member.setUsername("yoojin");
        member.setAge(20);

        // 1. 등록
        em.persist(member);

        Member findMember = em.find(Member.class, id);
        System.out.println("findMember: " + findMember.getId() + ", " + findMember.getUsername() + ", " + findMember.getAge());

        // 2. 수정
        member.setAge(30);  // update sql을 사용하지 않아도, DB값 바뀜

        Member findMember2 = em.find(Member.class, id);
        System.out.println("findMember2: " + findMember2.getId() + ", " + findMember2.getUsername() + ", " + findMember2.getAge());

    }
}
