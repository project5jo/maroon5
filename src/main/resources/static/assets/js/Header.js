function showFailModal() {
  document.getElementById("modalMessage").textContent =
    "로그인 후 이용해주세요.";
  modalButton.textContent = "Login";
  modalButton.setAttribute("onclick", "goToSignIn()");
  document.getElementById("cartModal").style.display = "block";
}
