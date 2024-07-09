function confirmDeletion(itemId) {
  if (confirm("정말로 이 상품을 삭제하시겠습니까?")) {
    window.location.href = `/shop/delete/${itemId}`; // 여기는 실제 삭제 로직을 처리하는 URL로 변경하세요.
  }
}
function handleFormSubmit(event, itemId) {
  event.preventDefault(); // 폼 제출 막기

  const userAccount = document.querySelector(
    `#addToCartForm-${itemId} input[name="userAccount"]`
  ).value;

  if (!userAccount) {
    showFailModal();
    return false;
  }

  const form = document.getElementById(`addToCartForm-${itemId}`); // 이벤트 대상 폼 참조
  const formData = new FormData(form);

  // Quantity를 1로 설정
  formData.set("quantity", 1);

  fetch("/cart", {
    method: "POST",
    body: formData,
  })
    .then((response) => {
      if (response.ok) {
        showModal();
      } else {
        alert("장바구니에 추가하는 데 실패했습니다.");
      }
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("장바구니에 추가하는 도중 오류가 발생했습니다.");
    });

  return false; // 폼 제출 막기
}

function showFailModal() {
  document.getElementById("modalMessage").textContent =
    "로그인 후 이용해주세요.";
  modalButton.textContent = "Login";
  modalButton.setAttribute("onclick", "goToSignIn()");
  document.getElementById("cartModal").style.display = "block";
}

function goToSignIn() {
  window.location.href = "/sign-in";
}

function showModal() {
  document.getElementById("cartModal").style.display = "block";
}

function closeModal() {
  document.getElementById("cartModal").style.display = "none";
}

function goToCart() {
  window.location.href = "/cart";
}

function goBack() {
  window.location.href = "/shop";
}

const $ul = document.querySelector(".main-container > ul");
const $allCartBtn = $ul.querySelectorAll("button.item-cart-btn");
// 가격 보이게 하기
$ul.querySelectorAll(".main-container li").forEach(($li) => {
  $li.addEventListener("mousemove", (e) => {
    const $targetLi = e.target.closest(".item-box");

    // hidden add하기
    $allCartBtn.forEach((CartBtn) => {
      CartBtn.classList.add("hidden");
    });

    const $targetCartBtn = $targetLi.querySelector("button.item-cart-btn");
    $targetCartBtn.classList.remove("hidden");
  });
});
