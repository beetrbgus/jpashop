package com.jpabook.jpashop.controller;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.dto.MemberJoinDto;
import com.jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/")
    public MemberJoinDto saveMemberV1(@RequestBody @Validated Member member) {
        Member savedMember = memberService.saveMember(member);
        MemberJoinDto joinDto = MemberJoinDto.builder()
                .username(savedMember.getUsername())
                .build();;
        return joinDto;
    }

    @PostMapping("/")
    public MemberJoinDto saveMemberV2(@RequestBody @Validated MemberJoinDto requestDto) {
        Member savedMember = memberService.saveMember(requestDto);
        MemberJoinDto joinDto = MemberJoinDto.builder()
                .username(savedMember.getUsername())
                .build();;
        return joinDto;
    }

}
