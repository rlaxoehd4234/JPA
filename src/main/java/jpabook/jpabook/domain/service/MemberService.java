package jpabook.jpabook.domain.service;

import jpabook.jpabook.domain.Member;
import jpabook.jpabook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findById(Long id){
        return memberRepository.findOne(id);
    }

    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id); // 불러오는 이유는 변경감지를 이용해서 등록하기 위해서 이다.
        member.setName(name);
    }
}
