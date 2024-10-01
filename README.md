<div align="center">
  
  ### 🌟✨ 나의 애착 페이지 ✨🌟
<br></div>

<div align="right"><b>5조 진상훈 이예진 정재한 임제훈 배윤정 </b></div>

---

## 📑 목차
- 프로젝트 개요
- 팀원 소개
- 기술 스택
- 프로젝트 소개
- 트러블 슈팅
- 개발 일정
- 예시 화면
- 업데이트 예정

## 📋 프로젝트 개요
<div align="center"></div>
나의 애착 페이지는 현대인들의 감성적인 소통과 웰빙을 지원하기 위해 개발된 디지털 소통 플랫폼입니다. 이 프로젝트는 직관적이고 감각적인 디자인을 통해 사용자가 편리하게 소통할 수 있는 환경을 제공합니다.💬

주요 기능으로는 실시간 채팅💬, 다양한 주제 제공📚, 감성적인 배경화면🎨, 그리고 테라피 용품의 소개 및 판매🛍️가 포함됩니다. 이를 통해 사용자가 정서적 안정을 찾고 소통의 질을 높이는 것을 목표로 합니다.

## 👥 팀원 소개
|팀원|담당|담당|
|:---:|---|---|
|진상훈(팀장)|FRONT|BACK|https://github.com/hun2zz|
|임제훈|FRONT|BACK|https://github.com/jehoonje|
|이예진|FRONT|BACK|https://github.com/yaejin12|
|정재한|FRONT|BACK|https://github.com/jehoonje|
|배윤정|FRONT|BACK|https://github.com/yunjeongg|

## 🛠️ 기술 스택
<div align="center">

### FRONTEND
<img width="285" alt="htmlcss" src="https://github.com/jehoonje/jpastudy/assets/70048630/641ccc9a-35af-419e-9cd4-ab26e506dc03">
<img width="156" alt="script" src="https://github.com/jehoonje/jpastudy/assets/70048630/f70b0267-7557-4867-a1d6-43eb98aa43fc">
</div>
<div align="center">

### BACKEND
<img width="318" alt="java" src="https://github.com/jehoonje/jpastudy/assets/70048630/9fbcae05-a4f1-4646-b866-b26957ba59fe">
<img width="172" alt="mybatis" src="https://github.com/jehoonje/jpastudy/assets/70048630/42cff6b1-937e-4f98-96eb-58a7f4eca394">
<img width="333" alt="spring" src="https://github.com/jehoonje/jpastudy/assets/70048630/6c8f5a6f-0950-4284-b98d-b1aaf8f45986">
<img width="214" alt="sql" src="https://github.com/jehoonje/jpastudy/assets/70048630/1dc323fa-fd03-474f-ba09-a6bf54a569e6">
</div>

## 👨‍💻👩‍💻 팀 구성원 및 역할
  
### 🧑‍💼 상훈
  FRONT END<br><br>
  - 관리자 페이지, 실시간 채팅, Footer<br><br>
  
  BACK END<br>
  - 웹소켓을 사용해서 유저가 서버 접속 시 소켓을 연결해서 실시간 양방향 통신을 통해 채팅 시스템 구현<br>
  - 소켓 연결 후 원하는 채팅방에 진입하고자 할 때, 채팅방을 순회하며 50명이 아닌 채팅방에 자동으로 배정시켜서 입장해주는 기능 구현 <br>
  - 어드민 페이지에서 실시간으로 변경할 토픽을 입력하면 채팅에 진입해 있는 유저에게 주어지는 주제가 실시간으로 변경 후 채팅방이 바뀜.<br>
  - 실시간으로 채팅 주제를 생성한 후 데이터베이스에 들어가는 기능 구현 <br><br>

### 🧑‍💼 제훈
  FRONT END<br>
  - 마크업 & CSS <br>
  - 카테고리 서랍, 쇼핑몰 목록, 관리자 아이템 추가 페이지, 장바구니, 주문 내역, 주문 상세 정보, 장바구니 추가 모달, Footer<br>
  - 스크립트, 날씨 API를 활용하고 설정된 시간에 따라 배경화면이 변화하도록 구현.<br>
  디자인 및 배경 레퍼런스 서칭<br><br>
  
  BACK END<br>
  - SecurityConfig 클래스로 유저와 admin 권한 설정<br>
  - 쇼핑몰 DB에 활용되는 DTO 클래스 생성<br>
  - 쇼핑몰 목록 아이템 저장 시, 장바구니 아이템 저장 시, 결제 완료 시, DB로 저장이 되고 세션에 등록된 유저의 저장된 정보를 읽어올 수 있고 삭제 가능,<br>
    주문 내역에서 조회가 가능하도록 controller, mapper, service, 클래스들을 통해 기능 구현.
    util을 활용한 이미지 업로딩 기능 구현<br><br>
    
### 🧑‍💼 예진
  FRONT END<br>
  - 마크업 & CSS : 로그인, 비밀번호 변경, 아이디 찾기, 헤더, 채팅창, 주문 결제 창 <br>
  - CSS 추가 작업: shop 메인, shop 상세페이지, 장바구니, 회원가입<br><br>
  
  BACK END<br>
  - 로그인 : 아이디 비번을 클라이언트에게 받아 DB 검색 후 회원 유무 확인.<br>
  - 자동 로그인: 자동 로그인 체크하면 자동 로그인 가능. <br>
  - 아이디 찾기 : 이름과 이메일을 클라이언트에게 받아 아이디 찾기<br>
  - 비번 변경 : 아이디를 클라이언트에게 받아 회원 정보 검색 후 비번 수정 가능 <br>
  - 주문 결제 창 : 세션에 저장된 회원 아이디를 받아 회원 정보 검색 및 controller, service, mapper 구현, 비동기를 이용하여 포인트 결제 확인.<br><br>


### 🧑‍💼 윤정
  FRONT END<br>
  - 마이페이지 JSP & CSS<br>
  
  BACK END<br>
  - 회원가입에서 프로필, 아이디, 비밀번호, 생일, 이메일을 입력받아 회원가입 가능<br>
  - 마이페이지에서 충전하고 싶은 금액을 클릭하고 충전 버튼을 클릭하면 포인트 충전.<br>
  - 원하는 사진을 선택해 프로필 사진으로 변경 또는 삭제.<br>
  - 회원정보에서 이름, 생일, 이메일을 수정, 현재 비밀번호와 새 비밀번호를 받아 조건 만족 시 비밀번호 수정, 특정 회원의 회원 탈퇴 기능<br><br>

### 🧑‍💼 재한
  FRONT END<br>
  - 주문 상세 페이지 마크업<br>
  - Footer 마크업<br><br>
  
  BACK END<br>
  - 클라이언트가 샵 페이지의 아이템 저장 시 DB로 저장 후에 장바구니 페이지에서 다시 조회하고, 결제 페이지에서 다시 조회하는 shop, shopping cart, order의 controller, service, mapper 구현<br>
  - 결제 페이지에서 주소 입력할 때 다음 주소 API 호출 기능, 날씨 API 호출 기능<br>
  - DB와 1:1 매칭되는 엔터티, 기본 기능 매퍼 생성<br>
  - 더미 데이터 생성<br>
  - 배경화면으로 사용할 레퍼런스 서칭<br>
  - 제품 상세 페이지에서 다른 아이템 랜덤 추천 기능<br><br>

## 🗓️ 개발 일정
- 2024.06.21 ~ 2024.07.09

## 🚀 업데이트 예정

### 💬 채팅 :
- 채팅 토픽을 가벼운 진중한 카테고리를 나눠서 원하는 정도의 주제로 얘기할 수 있게 하는 기능<br>
- 미디어 파일 첨부를 통해 다양한 소통의 기회를 만드는 기능<br>
- 친구 추가 기능을 통해 유저들 간의 소통을 원활히 도와주는 기능<br><br>

### 🔄 반품, 교환 기능 :
- 반품, 교환 처리를 통해 주문 내역에서 확인 가능하고 운영자가 조회할 수 있도록 하는 기능<br><br>

### 🎨 배경화면 :
- 배경화면이 변경될 때 알맞는 배경음악(빗소리, 타는 소리)이 자동적으로 재생되는 기능<br><br>

### 💳 결제 :
- 실제 결제 기능을 통해 사용자와 운영자 간의 거래가 이루어질 수 있도록 하는 기능<br><br>
