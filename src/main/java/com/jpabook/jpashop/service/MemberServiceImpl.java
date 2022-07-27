package com.jpabook.jpashop.service;

import com.jpabook.jpashop.dto.MemberJoinDto;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.dto.UpdateMemberRequestDto;
import com.jpabook.jpashop.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
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
}
