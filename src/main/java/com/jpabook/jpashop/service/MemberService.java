package com.jpabook.jpashop.service;

import com.jpabook.jpashop.dto.MemberJoinDto;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.dto.UpdateMemberRequestDto;

public interface MemberService {
    public Member saveMember(Member member);
    public Member saveMember(MemberJoinDto memberJoinDto);

    void updateMember(Long id, UpdateMemberRequestDto requestDto);

    Member findOne(Long id);
}
