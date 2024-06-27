package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
        Member member = memberMapper.findOne(userAccount);

        if (member == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getUserAccount())
                .password(member.getUserPassword())
                .authorities("ROLE_" + member.getUserRole()) // 여기에서 ROLE_를 추가해봅니다.
                .build();

    }
}
