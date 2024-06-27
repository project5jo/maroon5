package com.spring.mood.projectmvc.dto.requestDto;

import com.spring.mood.projectmvc.entity.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class ModifyPwDto {
    private String account;
    private String name;

    @NotBlank(message = "비밀번호는 필수입력정보입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이로 입력해주세요.")
    private String newPassword;

    private String newPasswordCk;



}
