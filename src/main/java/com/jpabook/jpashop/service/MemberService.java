package com.jpabook.jpashop.service;

import com.jpabook.jpashop.dto.MemberJoinDto;
import com.jpabook.jpashop.domain.Member;

public interface MemberService {
    public Member saveMember(Member member);

}
