
      // 현시간에 맞춰 배경화면 변경 (임시로 3초설정 해놈)(테스트)
      let counter = 0;

      function setBackgroundBasedOnCounter() {
      console.log("함수도 작동함");
      let backgroundUrl = '';

      if (counter % 3 === 0) {
          backgroundUrl = 'url(/assets/img/Cinemagraph.gif)';
      } else if (counter % 3 === 1) {
          backgroundUrl = 'url(/assets/img/rain.gif)';
      } else {
          backgroundUrl = 'url(/assets/img/firepit.gif)';
      }

      document.querySelector('.main_banner').style.backgroundImage = backgroundUrl;
      counter++;
      console.log("배경바꼇을걸?");
      }

      function init() {
          setBackgroundBasedOnCounter();
          console.log("이벤트작동함");
          // 10초마다 배경을 업데이트
          setInterval(setBackgroundBasedOnCounter, 3000); // 10초마다 실행
      }

      document.addEventListener('DOMContentLoaded', init);

      
      // 현재 유저의 시간에 따라 배경지정하는 함수
      // if (hours >= 6 && hours < 14) {
      //     // 오전 6시부터 오후 2시까지
      //     backgroundUrl = 'url(/assets/img/Cinemagraph.gif)';
      // } else if (hours >= 14 && hours < 22) {
      //     // 오후 2시부터 밤 10시까지
      //     backgroundUrl = 'url(/assets/img/rain.gif)';
      // } else {
      //     // 밤 10시부터 오전 6시까지
      //     backgroundUrl = 'url(/assets/img/firepit.gif)';
      // }

      // document.body.style.backgroundImage = backgroundUrl;
      // }
