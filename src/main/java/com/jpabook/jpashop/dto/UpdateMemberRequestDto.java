package com.jpabook.jpashop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateMemberRequestDto {
    private Long id;
    private String userName;
}
