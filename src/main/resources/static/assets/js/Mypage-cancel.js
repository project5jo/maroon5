const $cancelInput = document.querySelector(".cancelInput"); // input 태그

const $checkBtn = document.querySelector(".check"); // check 버튼 태그

// 1-1. input 태그를 클릭여부에 따른 value 값 변경하기
function checkToggle (checkbox) {

    if (checkbox.checked) {
        checkbox.value = "true";
        $checkBtn.type = "button";
        $checkBtn.classList.add("yellow");

    } else {
        checkbox.value = "false";
        $checkBtn.type="reset";
        $checkBtn.classList.remove("yellow");
    }
}

// 1-2. input 태그 클릭여부에 따른 value 값 확인하기

$cancelInput.addEventListener('click', e => {

    
    console.log("input 태그 value", $cancelInput.value);
})