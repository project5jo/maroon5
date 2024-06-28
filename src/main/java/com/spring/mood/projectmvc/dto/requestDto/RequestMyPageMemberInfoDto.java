package com.spring.mood.projectmvc.dto.requestDto;

import com.spring.mood.projectmvc.entity.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestMyPageMemberInfoDto {

    private String account; // 유저 계정

    @NotBlank(message = "이름은 필수입력정보입니다.")
    @Size(min = 2)
    private String name; // 유저 이름

    @NotBlank(message = "비밀번호는 필수입력정보입니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 8~16자 사이로 입력해주세요.")
    private String password; // 유저 비밀번호

    @NotBlank(message = "이메일은 필수입력정보입니다.")
    @Email(message = "올바른 이메일주소를 입력해주세요.")
    private String email; // 유저 이메일

    @NotNull(message = "생년월일은 필수입력정보입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth; // 유저 생년월일

    private String profileImage;  // 유저 프로필사진

//    // Dto 를 MemberEntity 로 변환
//    public Member toMemberEntity() {
//        return Member.builder().userAccount(this.account).userName(this.name)
//                .userEmail(this.email).userBirth(this.birth).build();
//    }

    public RequestMyPageMemberInfoDto(Member member) {
        this.account = member.getUserAccount();
        this.name = member.getUserName();
        this.password = member.getUserPassword();
        this.email = member.getUserEmail();
        this.birth = member.getUserBirth();
        this.profileImage = member.getUserProfile();
    }
}
