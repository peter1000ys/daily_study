package jpabook.jpashop.repository;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

// 스프링 빈으로 자동으로 등록
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // JPA가 제공하는 표준 어노테이션
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    // 한명 조회
    public Member findOne(Long id) {

        return em.find(Member.class, id);
    }

    // 리스트 조회
    public List<Member> findAll() {
        // 첫 번째가 jpaquery 조회 , 두번째가 반환 타입

        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
