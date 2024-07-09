const menuIcon = document.querySelector(".menu-icon");
const categoryDrawer = document.querySelector(".category-drawer");
const categoryButton = document.querySelector(".category-drawer > .cancel_btn");

let timeout;

menuIcon.addEventListener("click", () => {
  categoryDrawer.classList.add("show");
  menuIcon.style.opacity = "0";
});

// x 버튼 이벤트
categoryButton.addEventListener("click", () => {
  clearTimeout(timeout);
  categoryDrawer.classList.remove("show");
  menuIcon.style.opacity = "1";
});
