package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.requestDto.RequestMyPageMemberInfoDto;
import com.spring.mood.projectmvc.dto.responseDto.ResponseMyPageMemberInfoDto;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MemberMapper membermapper;
    private final PasswordEncoder encoder;

    public ResponseMyPageMemberInfoDto serviceFindOne (String account) {

        Member foundMember = membermapper.findOne(account);

//        System.out.println("디비에있는자료 = " + foundMember);

        ResponseMyPageMemberInfoDto foundOneDto = new ResponseMyPageMemberInfoDto(foundMember);

//        System.out.println("f프로필프뢸o = " + foundOneDto);

        return foundOneDto;
    }

    public int serviceUpdateMemberInfo(String account, RequestMyPageMemberInfoDto dto) {

        System.out.println("수정내용적은것 = " + dto);
        System.out.println("아이디 = " + account);

        Member modifyMember = dto.toMemberEntity(account);

        System.out.println("수정할내용이담긴멤버 = " + modifyMember);

        int isUpdated = membermapper.updateMyPageMemberInfo(modifyMember);

        System.out.println("수정완료완료 = " + isUpdated);

        return isUpdated;
    }

    public void serviceDelete(String account) {

        membermapper.delete(account);
    }

    public boolean serviceUpdatePassword(String account, String password, String newPassword) {

        // 로그인한 아이디를 통해 해당 회원의 정보를 찾기
        Member foundMember = membermapper.findOne(account);

        // 클라이언트에서 입력한 현재 비밀번호와 DB 의 비밀번호 비교하기
        if (encoder.matches(password, foundMember.getUserPassword())) {

            // 비밀번호 암호화
            String encodedPassword = encoder.encode(newPassword);

            boolean flag = membermapper.updatePassword(account, encodedPassword);

            return flag;
        }

        return false;
    }
}
