package jpabook.jpabook.repository;

import jpabook.jpabook.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }
    @Transactional(readOnly = true)
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }
    @Transactional(readOnly = true)
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

    }
    @Transactional(readOnly = true)
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name= :name",Member.class).getResultList();
    }
}
