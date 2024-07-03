// 마이페이지 - 프로필사진 수정



// 프로필 사진 수정에 필요한 변수모음
const $leftProfileBtn = document.querySelector('.profile-icon'); // 왼쪽메뉴 프로필 수정 버튼

const $profileInput = document.querySelector('.profileInput'); // 프로필사진 인풋태그
const $profileImg = document.querySelector('.right-input img'); // 프로필사진 이미지태그
const $profileInputStatus =  document.querySelector('.profileInputStatus'); // 프로필사진 인풋태그 상태

const $profileDB = document.getElementById("profileSRC"); // 서버로부터 받아온 기존 프로필사진

const $profileUploadBtn = document.querySelector('.upload'); // 프로필사진 선택 버튼
const $profileBasicBtn = document.querySelector('.basic'); // 기본 이미지 적용 버튼

const $profileDeleteBtn = document.querySelector('.delete'); // 취소 버튼
const $profileCheckBtn = document.querySelector('.check'); // 적용 버튼

const $modal = document.querySelector('.modal-box'); // 모달창
const $modalBack = document.querySelector('.modalBack'); // 모달창 배경

const $modalCancelBtn = document.querySelector('.cancel');// 모달 취소 버튼
const $modalXButton = document.querySelector('.modal-header span'); // 모달창의 X버튼

const $modalRecheckBtn = document.querySelector('.recheck');// 모달 재확인 버튼
const $profileForm = document.querySelector('.profileForm'); // 프로필 폼 태그



// 1. 프로필사진 선택버튼을 클릭하면 이미지파일 첨부 인풋태그 클릭
$profileUploadBtn.addEventListener('click', e => {
    // 프로필사진 업로드버튼을 누르면 인풋태그 클릭하기
    $profileInput.click();
})

// 2-1. 인풋태그로 이미지파일 첨부한 경우 첨부파일 데이터 저장 & 클라이언트 처리
$profileInput.addEventListener("change", (e) => {
    // 인풋태그 에서 이미지파일 선택했을 때 
    // 유저가 올린 파일
    const profileDate = $profileInput.files[0];

    if (profileDate) {
        // 이미지파일의 로우데이터(바이트) 를 읽는 객체 생성
        const reader = new FileReader();

        // 파일을 img 태그의 src 속성에 넣기 위해 URL 형태로 변경
        reader.readAsDataURL(profileDate);

        // 파일 읽기 완료 후 img 태그의 src 속성에 Data URL 설정
        reader.onloadend = (e) => {
            $profileImg.src = reader.result;
        };

        $profileInputStatus.value = "true";
        console.log("인풋저장상태", $profileInputStatus.value);

    } 
})

// 2-2. 기본 이미지 적용 버튼을 클릭한 경우 클라이언트 처리
$profileBasicBtn.addEventListener('click', e => {
    // 기본사진 버튼 클릭시 프로필 기본이미지로 변경
    $profileImg.src = "/assets/img/profile3.jpg";

    $profileInputStatus.value = "deleteProfile";
    // console.log("인풋저장상태", $profileInputStatus.value);
})

// 2-3. 취소 버튼을 클릭한 경우 기존에 저장된 프로필이미지 유지 클라이언트 처리
$profileDeleteBtn.addEventListener('click', e => {

    // JSP 에서 서버에 저장된 이미지 파일 경로 가져오기
    $profileImg.src = $profileDB.value;

    $profileInputStatus.value = "nowProfile";
    // console.log("인풋저장상태", $profileInputStatus.value);
})





