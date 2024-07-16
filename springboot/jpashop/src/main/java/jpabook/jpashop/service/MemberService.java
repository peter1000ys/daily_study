package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 트렌젝션 안에서 데이터 변경해라
@RequiredArgsConstructor
// final 있는 필드만 생성자를 만들어준다
public class MemberService {

    // 인젝션 과정

    private final MemberRepository memberRepository;

    // 생성자 인젝션
    // 생성자가 하나만 있는 살황에서는
    // Authwired 없어도 스프링에서 알아서 인젝션 해줌
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional
    // 읽기가 아닌 쓰기에서는 readOnly=true를 추기하면 안된다.
    public Long join(Member member) {
        // 중복회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }
    // 회원 전체 조회
    // 읽기 전용은 리소를 많이 쓰지 말고 단순히 읽기용으로
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 단일 회원 조회
    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
