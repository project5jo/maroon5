package com.spring.mood.projectmvc.dto.requestDto;

import com.spring.mood.projectmvc.entity.Member;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class FindIdDto {
    private String account; // 유저 계정
    private String name; // 유저 이름
    private String email; // 유저 이메일
    private String phoneNum; // 유저 폰번호

    public FindIdDto(Member foundmember) {
        this.account = foundmember.getUserAccount();
        this.name=foundmember.getUserName();
        this.email=foundmember.getUserEmail();
    }
}
