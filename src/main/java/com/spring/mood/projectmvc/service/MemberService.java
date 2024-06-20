package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.requestDto.RequestMemberDto;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    // 의존 필드 생성
    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // JSP 문서에서 받은 유저정보 변환 & 저장
    public boolean memberServiceSave (RequestMemberDto dto) {

        // RequestMemberDto 를 MemberEntity 로 변환
        Member memberEntity = dto.toMemberEntity();

        // 비밀번호 암호화
        String encodedPassword = encoder.encode(dto.getPassword());
        memberEntity.setUserPassword(encodedPassword);

        // memberMapper 에서 dto 저장
        boolean flag = memberMapper.save(memberEntity);
        return flag;
    }

    // 아이디 중복체크하기
    public boolean serviceCheckId(String account) {
        return memberMapper.checkId(account);
    }

    // 이메일 중복체크하기
    public boolean serviceCheckEmail(String email) { return memberMapper.checkEmail(email);
    }



    // 회원가입 중간처리
    public void joinInServiceProcess (RequestMemberDto dto){
        // 1. 회원가입여부 확인
        Member checkMember = memberMapper.findOne(dto.getAccount());
        if (checkMember == null) {
            log.info("회원가입하지 않은 회원입니다.");
        }

        // 2. 입력 비밀번호 중복검사
        String inputPassword = dto.getPassword(); // 입력한 비밀번호
        String savedPassword = checkMember.getUserPassword(); // DB에 저장된 비밀번호

        if (!encoder.matches(inputPassword, savedPassword)) {
            log.info("일치하지 않은 비밀번호입니다.");
        }
        // 3. 입력 이메일 중복검사

    }
}
