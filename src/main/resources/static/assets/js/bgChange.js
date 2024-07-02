// 사용자의 현재 위치 정보를 가져오는 함수
function getCurrentLocation() {
  return new Promise((resolve, reject) => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(resolve, reject);
    } else {
      reject(new Error('Geolocation is not supported by this browser.'));
    }
  });
}

// OpenWeatherMap API를 호출하여 날씨 데이터를 가져오는 함수
async function fetchWeatherData(lat, lon) {
  const apiKey = 'f17fb76a32d667c27e32c2c2ce62d6d7'; // OpenWeatherMap API 키
  const url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apiKey}&units=metric`;

  try {
    const response = await fetch(url);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error fetching weather data:', error);
    throw error;
  }
}

// 날씨와 시간에 따라 배경 이미지를 설정하는 함수
function setBackground(weatherData) {
  const currentTime = new Date();
  console.log(currentTime); // 현재 시간
  const hours = currentTime.getHours();
  const weatherCondition = weatherData.weather[0].main; // 날씨 상태
  console.log(weatherCondition); // 날씨 상태
  let backgroundUrl = '';

  // 날씨 상태와 시간에 따라 배경 지정
  if (weatherCondition === 'Clear') {
    if (hours >= 6 && hours < 14) {
      // 맑은 아침
      backgroundUrl = 'url(/assets/img/sunny.jpeg)';
    } else if (hours >= 14 && hours < 22) {
      // 맑은 오후
      backgroundUrl = 'url(/assets/img/NorwaySunset.gif)';
    } else {
      // 맑은 밤
      backgroundUrl = 'url(/assets/img/firepit.gif)';
    }
  } else if (weatherCondition === 'Rain') {
    if (hours >= 6 && hours < 14) {
      // 비오는 아침
      backgroundUrl = 'url(/assets/img/rain.gif)';
    } else if (hours >= 14 && hours < 22) {
      // 비오는 오후
      backgroundUrl = 'url(/assets/img/Cinemagraph.gif)';
    } else {
      // 비오는 밤
      backgroundUrl = 'url(/assets/img/rain.gif)';
    }
  } else if (weatherCondition === 'Clouds') {
    if (hours >= 6 && hours < 14) {
      // 흐린 아침
      backgroundUrl = 'url(/assets/img/Cinemagraph.gif)';
    } else if (hours >= 14 && hours < 22) {
      // 흐린 오후
      backgroundUrl = 'url(/assets/img/Cinemagraph.gif)';
    } else {
      // 흐린 밤
      backgroundUrl = 'url(/assets/img/Cinemagraph.gif)';
    }
  } else {
    // 기본 배경
    backgroundUrl = 'url(/assets/img/whiskey.gif)';
  }

  document.querySelector('.main_banner').style.backgroundImage = backgroundUrl;
}

// 초기화 함수, 사용자 위치를 가져와 날씨 데이터를 설정
async function init() {
  try {
    const position = await getCurrentLocation();
    console.log(position); // 현재 유저 위치
    
    const lat = position.coords.latitude;
    const lon = position.coords.longitude;

    const weatherData = await fetchWeatherData(lat, lon);

    setBackground(weatherData);
    setInterval(async () => {
      const newWeatherData = await fetchWeatherData(lat, lon);
      setBackground(newWeatherData);
    }, 60000); // 1분마다 업데이트
  } catch (error) {
    console.error('Error getting location or weather data:', error);
  }
}

// DOMContentLoaded 이벤트가 발생하면 init 함수 실행
document.addEventListener('DOMContentLoaded', init);




//////////////////////////////////////////////////////////// TEST 시연용 ///////////
// // 현시간에 맞춰 배경화면 변경 (임시로 3초설정 해놈)
// let counter = 0;

// function setBackgroundBasedOnCounter() {
// console.log("함수도 작동함");
// let backgroundUrl = '';

// if (counter % 3 === 0) {
//     backgroundUrl = 'url(/assets/img/Cinemagraph.gif)';
// } else if (counter % 3 === 1) {
//     backgroundUrl = 'url(/assets/img/rain.gif)';
// } else {
//     backgroundUrl = 'url(/assets/img/firepit.gif)';
// }

// document.querySelector('.main_banner').style.backgroundImage = backgroundUrl;
// counter++;
// console.log("배경바꼇을걸?");
// }

// function init() {
//     setBackgroundBasedOnCounter();
//     console.log("이벤트작동함");
//     // 10초마다 배경을 업데이트
//     setInterval(setBackgroundBasedOnCounter, 3000); // 10초마다 실행
// }

// document.addEventListener('DOMContentLoaded', init);

