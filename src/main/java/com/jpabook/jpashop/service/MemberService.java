package com.jpabook.jpashop.service;

import com.jpabook.jpashop.dto.MemberJoinDto;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.dto.UpdateMemberRequestDto;

import java.util.List;

public interface MemberService {
    Long saveMember(Member member);
    Member saveMember(MemberJoinDto memberJoinDto);

    void updateMember(Long id, UpdateMemberRequestDto requestDto);

    Member findOne(Long id);

    List<Member> findAll();
}
