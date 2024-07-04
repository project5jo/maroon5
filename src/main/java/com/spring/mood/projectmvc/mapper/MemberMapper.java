package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.dto.requestDto.AutoSignInDto;
import com.spring.mood.projectmvc.dto.requestDto.RequestMyPageMemberInfoDto;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberMapper {

    boolean save(Member member);

    Member findOne(String account);

    void updateRoomId(@Param("account") String account, @Param("roomId")int roomId);


    User findChatUser(String account);

    boolean deleteMyPageAccount(@Param("account") String account, @Param("deleteFlag") boolean deleteFlag);

    boolean checkId(String account);

    boolean checkEmail(String email);

    Member findId(@Param("name")String name,@Param("email") String email);

    boolean updatePassword(@Param("account") String account,@Param("NewPw") String NewPw);

    boolean updatePoint(@Param("point") Integer point,@Param("account") String account);

    //자동로그인 쿠키값, 만료시간 업데이트
    void updateAutoLogin(AutoSignInDto dto);

    //세션아이디로 회원정보 조회
    Member findUserBySessionId (String sessionId);

    // 마이페이지 회원정보 수정
    int updateMyPageMemberInfo (Member member);

    int updateMyPageProfile (@Param("account") String account, @Param("profile") String profile);

    boolean chargePoint (@Param("account") String account, @Param("point") int point);

}
