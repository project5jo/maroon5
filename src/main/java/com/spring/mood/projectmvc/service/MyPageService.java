package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.requestDto.RequestMyPageMemberInfoDto;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MemberMapper membermapper;

    public RequestMyPageMemberInfoDto serviceFindOne(String account) {

        Member member =  membermapper.findOne(account);

        RequestMyPageMemberInfoDto dto = new RequestMyPageMemberInfoDto(member);

        return dto;
    }
}
