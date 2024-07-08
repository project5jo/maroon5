
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



// 1-3. input태그에 첨부한 파일 처리
function addInputProfile () {
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
}


// 1-3. 인풋태그로 이미지파일 첨부한 경우 첨부파일 데이터 저장 & 클라이언트 처리
$profileInput.addEventListener("change", (e) => {

    addInputProfile ();
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



// 2-1. 드랍변수목록

// dropArea 요소 가져오기
const $dropArea = document.getElementById('drop-area'); // 프로필 박스 창

// dragenter 이벤트 리스너 등록
$dropArea.addEventListener('dragenter', function(e) { // 드래그된 항목이 드랍영역에 진입할 때
    e.preventDefault(); // 이벤트의 기본동작 취소시키고, 파일드롭한 경우 드롭영역에 파일 추가
    e.stopPropagation(); // 이벤트의 전파를 중지시켜, 이벤트가 상위 요소로 전파되는 것을 막아 이벤트 핸들러가 중복 호출되는 것을 방지
    $dropArea.classList.add('highlight'); // 드랍완료시 시각효과 제거하기
}, false);

// dragover 이벤트 리스너 등록
$dropArea.addEventListener('dragover', function(e) { // 드랍영역 위에서 드래그된 항목이 움직일 때
    e.preventDefault();
    e.stopPropagation();
    $dropArea.classList.add('highlight');
}, false);

// dragleave 이벤트 리스너 등록
$dropArea.addEventListener('dragleave', function(e) { // 드랍영역을 드래그한 항목이 드랍영역를 벗어날 때
    e.preventDefault();
    e.stopPropagation();
    $dropArea.classList.remove('highlight');
}, false);

// drop 이벤트 리스너 등록
$dropArea.addEventListener('drop', function(e) { // 드래그한 항목을 드랍영역 위에 놓을 때
    e.preventDefault();
    e.stopPropagation();
    $dropArea.classList.remove('highlight');

    // 드롭된 파일 처리 로직 추가
    const files = e.dataTransfer.files;
    
    if (files.length > 0) {
        $profileInput.files = files;
        addInputProfile ();
    }
}, false);









