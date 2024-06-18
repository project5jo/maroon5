package com.spring.mood.projectmvc.dto.requestDto;

import com.spring.mood.projectmvc.entity.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

// 회원가입 JSP 문서 매칭 dto
@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestMemberDto {

    @NotBlank(message = "아이디는 필수입력정보입니다.")
    @Size(min = 2, max = 15, message = "아이디는 5~20자 사이로 입력해야 합니다.")
    @Pattern(regexp ="^[a-z][a-z0-9_-]{4,14}$", message = "영문 소문자, 숫자, 특수문자(_),(-)만 사용가능합니다.")
    private String account; // 유저 계정

    @NotBlank(message = "이름은 필수입력정보입니다.")
    @Size(min = 2)
    private String name; // 유저 이름

    @NotBlank(message = "비밀번호는 필수입력정보입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이로 입력해주세요.")
    private String password; // 유저 비밀번호

    @NotBlank(message = "이메일은 필수입력정보입니다.")
    @Email(message = "올바른 이메일주소를 입력해주세요.")
    private String email; // 유저 이메일

    @NotNull(message = "생년월일은 필수입력정보입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth; // 유저 생년월일

//    private MultipartFile profileImage; // 유저 프로필사진

    // RequestMemberDto 를 MemberEntity 로 변환
    public Member toMemberEntity() {
        return Member.builder().userAccount(this.account).userName(this.name)
                .userPassword(this.password).userEmail(this.email).userBirth(this.birth).build();
    }
}