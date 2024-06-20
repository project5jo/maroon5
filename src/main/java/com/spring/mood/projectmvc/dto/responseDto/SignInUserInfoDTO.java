package com.spring.mood.projectmvc.dto.responseDto;

import com.spring.mood.projectmvc.entity.Member;
import lombok.*;

@Getter @ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class SignInUserInfoDTO {

    private String account;
    private String nickName;
    private String email;
//    private String auth; //권한

    public SignInUserInfoDTO(Member member){
        this.account = member.getUserAccount();
        this.nickName = member.getUserName();
        this.email = member.getUserEmail();


    }
}
