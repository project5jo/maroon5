
// 프로필 사진 수정 변수 목록
const $profileInput = document.querySelector('.formInput'); // 프로필사진 인풋태그
const $profileInputStatus =  document.querySelector('.profileInputStatus'); // 프로필사진 인풋태그 상태

const $profileImg = document.querySelector('.form-profile img'); // 프로필사진 이미지태그
const $profileDB = document.getElementById("profileSRC"); // 서버로부터 받아온 기존 프로필사진

const $uploadBtn = document.querySelector('.upload'); // upload 버튼
const $defaultBtn = document.querySelector('.default'); // default 버튼
const $backBtn = document.querySelector('.back'); // back 버튼

const $check = document.querySelector('.check'); // 확인 버튼



// 1-1. 프로필사진 버튼 중 어떤것이든 클릭시 확인버튼 활성화하기
function activeCheck () {

  // 재확인 모달창 열기 이벤트 추가
  $check.addEventListener('click', openModal);
}



// 1-2. input 태그 클릭
function openProfileInput () {
  $profileInput.click();
}



// 1-3. 인풋태그로 이미지파일 첨부한 경우 첨부파일 데이터 저장 & 클라이언트 처리
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

        $profileInputStatus.value = "upload";
        console.log("인풋저장상태", $profileInputStatus.value);
    } 
})



// 1-4. 기본 이미지 적용 버튼을 클릭한 경우 클라이언트 처리
$defaultBtn.addEventListener('click', e => {
    // 기본사진 버튼 클릭시 프로필 기본이미지로 변경
    $profileImg.src = "/assets/img/profile3.jpg";

    $profileInputStatus.value = "default";
    // console.log("인풋저장상태", $profileInputStatus.value);
})



// 1-5. 취소 버튼을 클릭한 경우 기존에 저장된 프로필이미지 유지 클라이언트 처리
$backBtn.addEventListener('click', e => {

    // JSP 에서 서버에 저장된 이미지 파일 경로 가져오기
    console.log('서버사진', $profileDB.value);
    $profileImg.src = $profileDB.value;
    // $profileImg.src = "/assets/img/profile3.jpg";

    $profileInputStatus.value = "back";
    // console.log("인풋저장상태", $profileInputStatus.value);
})







