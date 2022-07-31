package com.jpabook.jpashop.controller;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.dto.CommonResponse;
import com.jpabook.jpashop.dto.MemberDto;
import com.jpabook.jpashop.dto.MemberJoinDto;
import com.jpabook.jpashop.dto.UpdateMemberRequestDto;
import com.jpabook.jpashop.dto.UpdateMemberResponseDto;
import com.jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/v1/members")
    public List<Member> getMemberV1() {
        memberService.findAll();
        return null;
    }

    @GetMapping("/v2/members")
    public CommonResponse getMemberV2() {
        List<Member> findMembers = memberService.findAll();
        List<MemberDto> memberDtos = findMembers.stream()
                .map(member -> new MemberDto(member.getId(), member.getUserName()))
                .collect(Collectors.toList());
        return CommonResponse.builder()
                .data(memberDtos)
                .build();
    }


    @PostMapping("/v1/members")
    public MemberJoinDto saveMemberV1(@RequestBody @Validated Member member) {
        Long savedMemberId = memberService.saveMember(member);
        Member findMember = memberService.findOne(savedMemberId);

        MemberJoinDto joinDto = MemberJoinDto.builder()
                .username(findMember.getUserName())
                .build();;
        return joinDto;
    }

    @PostMapping("/v2/members")
    public MemberJoinDto saveMemberV2(@RequestBody @Validated MemberJoinDto requestDto) {
        Member savedMember = memberService.saveMember(requestDto);
        MemberJoinDto joinDto = MemberJoinDto.builder()
                .username(savedMember.getUserName())
                .build();;
        return joinDto;
    }

    @PutMapping("/v2/member/{id}")
    public UpdateMemberResponseDto updateMemberV2(@PathVariable(name = "id") Long id,
                                        @RequestBody @Validated UpdateMemberRequestDto requestDto) {
        memberService.updateMember(id, requestDto);
        Member findMember = memberService.findOne(id);

        UpdateMemberResponseDto responseDto = UpdateMemberResponseDto.builder()
                .id(findMember.getId())
                .userName(findMember.getUserName())
                .build();

        return responseDto;
    }

}
