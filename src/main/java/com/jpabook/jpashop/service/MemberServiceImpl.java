package com.jpabook.jpashop.service;

import com.jpabook.jpashop.dto.MemberJoinDto;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.dto.UpdateMemberRequestDto;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
// public 붙은 메소드는 다 적용이 되긴 함.
// 쓰기 가능한 메소드에는 readOnly를 true로 주면 데이터 추가/변경이 안 되기 때문에 따로 어노테이션 추가해야 함.
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    //final 로 설정하면 컴파일 시점에 검사해 줌.
    private final MemberRepository memberRepository;

    private void validateDuplicateMember(Member member) {
        // 멀티 쓰레드 환경에서 동시에 같은 이름의 회원이 가입할 수 있기 때문에 중복체크를 했어도 DB에 유니크 제약조건을 추가하는게 안전.
        List<Member> memberList = memberRepository.findByUserName(member.getUserName());

        if(memberList.size() > 0) {
            // Exception
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Override
    @Transactional
    public Long saveMember(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        // save 하고 member에 영속성에 띄워줘서 찾기 가능.
        return member.getId();
    }

    @Override
    @Transactional
    public Member saveMember(MemberJoinDto memberJoinDto) {
        Member member = Member.builder()
                .userName(memberJoinDto.getUsername())
                .build();

        return memberRepository.save(member);
    }

    @Override
    @Transactional
    public void updateMember(Long id, UpdateMemberRequestDto requestDto) {
        Optional<Member> optionalMember = memberRepository.findById(id);

        if(optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setUserName(requestDto.getUserName());
        }
    }

    @Override
    public Member findOne(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        Member member = new Member();
        if(memberOptional.isPresent()) {
            member = memberOptional.get();
        }
        return member;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
