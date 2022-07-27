package com.jpabook.jpashop.service;

import com.jpabook.jpashop.dto.MemberJoinDto;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.springframework.stereotype.Service;

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
                .username(memberJoinDto.getUsername())
                .build();

        return memberRepository.save(member);
    }
}
