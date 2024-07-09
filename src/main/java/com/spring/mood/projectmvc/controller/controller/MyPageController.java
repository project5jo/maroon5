package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.RequestMyPageMemberInfoDto;
import com.spring.mood.projectmvc.dto.responseDto.ResponseMyPageMemberInfoDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.service.MyPageService;
import com.spring.mood.projectmvc.service.SignInService;
import com.spring.mood.projectmvc.util.SignInUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private static final Logger log = LoggerFactory.getLogger(MyPageController.class);
    private final MyPageService service;
    private final SignInService signInService;

    // MyPage 클릭했을 때 조건에 맞는경우 웹페이지 띄우기
    @GetMapping("/mypage")
    public String openMyPage (HttpSession session, Model model) {

        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        // 로그인한 회원정보의 아이디를 통해 해당 회원 한 명의 정보 찾기
        ResponseMyPageMemberInfoDto dto = service.serviceFindOne(loginUser.getAccount());

        // JSP 로 dto 보내기
        model.addAttribute("nowMember", dto);

        return "html/mypage-main";
    }

    // 마이페이지 들어갔을 때 등록된 프로필사진이 있다면 띄우기
    @GetMapping("/mypage-memberinfo")
    public String openMyPageMemberInfo (HttpSession session, Model model) {

        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

//        System.out.println("로그인한 회원의 정보 = " + loginUser);

        // 로그인한 회원정보의 아이디를 통해 해당 회원 한 명의 정보 찾기
        ResponseMyPageMemberInfoDto dto = service.serviceFindOne(loginUser.getAccount());

//        System.out.println("로그인한회원한명의 전체정보 = " + dto);
       
       // JSP 로 dto 보내기
        model.addAttribute("isUpdated", false);
        model.addAttribute("nowMember", dto);

        return "html/mypage-memberInfo";
    }

    // 회원정보 수정하기
    @PostMapping("/mypage-memberinfo")
    public String updateMyPageMemberInfo (HttpSession session, RequestMyPageMemberInfoDto dto, Model model) {

        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        // 로그인한 회원의 정보에서 아이디 가져오기
        String account = loginUser.getAccount();
        System.out.println("로그인한 회원의 정보수정수정 = " + loginUser);

        // 로그인 아이디 & 회원정보 수정창에서 받은 데이터를 서비스로 보내 회원정보 수정처리 위임하기
        int isUpdated = service.serviceUpdateMemberInfo(account, dto);

        model.addAttribute("isUpdated", isUpdated > 0);
        model.addAttribute("updatedMember", dto);


        return "redirect:/mypage-memberinfo";
    }

    @GetMapping("/mypage-profile")
    public String openMyPageProfile (HttpSession session, Model model) {

        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        // 로그인한 회원정보의 아이디를 통해 해당 회원 한 명의 정보 찾기
        ResponseMyPageMemberInfoDto dto = service.serviceFindOne(loginUser.getAccount());

        // JSP 로 dto 보내기
        model.addAttribute("nowMember", dto);

        return "html/mypage-profile";
    }

    @PostMapping("/mypage-profile")
    public String updateMyPageProfile (HttpSession session, MultipartFile profile, String profileStatus, Model model) {
        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        String account = loginUser.getAccount();

        // 로그인 아이디 & 서버에서 받은 프로필데이터를 서비스로 보내 수정처리 위임하기
        int isUpdated = service.serviceUpdateProfile(account, profile, profileStatus, session);
        if (isUpdated > 0) {
            // 프로필 이미지 업데이트가 성공하면 loginUser 객체의 프로필 이미지 필드 갱신
            session.setAttribute("loginUser", loginUser);
        }
        model.addAttribute("isUpdated", isUpdated > 0);
        model.addAttribute("updatedMember", profile);

        return "redirect:/mypage-profile";

    }

    @GetMapping("/mypage-password")
    public String openMyPagePassword (HttpSession session, Model model) {

        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        // 로그인한 회원정보의 아이디를 통해 해당 회원 한 명의 정보 찾기
        ResponseMyPageMemberInfoDto dto = service.serviceFindOne(loginUser.getAccount());

        // JSP 로 dto 보내기
        model.addAttribute("nowMember", dto);

        return "html/mypage-password";
    }

    // 아이디, 이메일 중복검사 비동기 요청 처리
    @PostMapping("/checkpassword")
    @ResponseBody
    public ResponseEntity<Boolean> checkPassword (HttpSession session, @RequestParam("password")String password) {
        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        boolean flag = false;

        if (service.serviceCheckPassword (loginUser.getAccount(), password)) {
            flag = true;
        } else {
            flag = false;
        }
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    @PostMapping("/mypage-password")
    public String updatePassword (HttpSession session, String password, String newPassword) {
        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        boolean isUpdated = service.serviceUpdatePassword(loginUser.getAccount(), password, newPassword);

        if (isUpdated) {
            return "redirect:/mypage-password";
        }
        return "html/mypage-password";
    }

    @GetMapping("/mypage-cancel")
    public String openMyPageCancel (HttpSession session, Model model) {
        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        // 로그인한 회원정보의 아이디를 통해 해당 회원 한 명의 정보 찾기
        ResponseMyPageMemberInfoDto dto = service.serviceFindOne(loginUser.getAccount());

        // JSP 로 dto 보내기
        model.addAttribute("nowMember", dto);

        return "html/mypage-cancel";
    }

    @PostMapping ("/mypage-cancel")
    public String deleteMemberInfo (HttpSession session, boolean deleteFlag, HttpServletRequest request, HttpServletResponse response) {
        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        boolean isDeleted = service.serviceDelete(loginUser.getAccount(), deleteFlag);

        if (isDeleted) {
            if (SignInUtil.isAutoLoggedIn(request)) {
                signInService.autoLoginClear(request, response);
            }
            HttpSession newSession = request.getSession();
            newSession.removeAttribute("loginUser");
            newSession.invalidate();

            return "redirect:/";
        } else {
            return "/mypage-cancel";
        }

    }

    @GetMapping("/mypage-point")
    public String openMyPagePoint (HttpSession session, Model model) {
        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

//        System.out.println("로그인한 회원의 정보 = " + loginUser);

        // 로그인한 회원정보의 아이디를 통해 해당 회원 한 명의 정보 찾기
        ResponseMyPageMemberInfoDto dto = service.serviceFindOne(loginUser.getAccount());

//        System.out.println("로그인한회원한명의 전체정보 = " + dto);

        // JSP 로 dto 보내기
        model.addAttribute("isUpdated", false);
        model.addAttribute("nowMember", dto);

        return "html/mypage-point";
    }

    @PostMapping ("/mypage-point")
    public String chargePoint (HttpSession session, int point, Model model) {
        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        String account = loginUser.getAccount();

        boolean isUpdated = service.serviceChargePoint(account, point);

        if (isUpdated) {
            ResponseMyPageMemberInfoDto dto =  service.serviceFindOne(account);

            // JSP 로 dto 보내기
            model.addAttribute("updatedMember", dto);
            return "redirect:/mypage-point";

        } else {
            throw new RuntimeException ("업데이트에 실패하였습니다.");
        }
    }
}
