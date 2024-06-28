package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.requestDto.RequestMyPageMemberInfoDto;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {

    MemberMapper membermapper;

//    public RequestMyPageMemberInfoDto serviceFindOne(String account) {
//
//        Member member = membermapper.findOne(account);
//
//        String profile = member.getUserProfile();
//
////        System.out.println("멤버나오니?" + member);
////
////        RequestMyPageMemberInfoDto dto = new RequestMyPageMemberInfoDto(member);
//
////        if (member.getUserProfile())
////        System.out.println("디티오나오니?" + dto);
//
//        return  dto;
//    }
}
