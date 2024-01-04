document.getElementById('calendar-link').addEventListener('click', function (e) {
    console.log("Calendar clicked");
    e.preventDefault();
    loadCalender();
});

document.getElementById('shareBoard-link').addEventListener('click', function (e) {
    console.log("ShareBoard clicked");
    e.preventDefault();
    loadShareBoard();
});

document.getElementById('freeBoard-link').addEventListener('click', function (e) {
    console.log("FreeBoard clicked");
    e.preventDefault();
    loadFreeBoard();
});

document.getElementById('myInfo-link').addEventListener('click', function (e) {
    console.log("MyInfo clicked");
    e.preventDefault();
    loadMyInfo();
});

function runAtHour(func) {
    const now = new Date();
    const delay = 60 * 60 * 1000 - (now.getMinutes() * 60 + now.getSeconds()) * 1000 + now.getMilliseconds();

    setTimeout(function () {
        func();
        setInterval(func, 60 * 60 * 1000);
    }, delay);
}

runAtHour(temperature);

window.onload = function () {
    temperature();
    loadCalender();
};

function temperature() {
    const date = new Date();
    const year = date.getFullYear();
    const month = date.getMonth() + 1 >= 10 ? date.getMonth() + 1 : `0${date.getMonth() + 1}`;
    const day = date.getDate() >= 10 ? date.getDate() : `0${date.getDate()}`;
    const getDate = year + month + day;

    let hours = date.getHours();
    const showDate = "/ " + year + "-" + month + "-" + day;

    const getHours = () => {
        if (hours >= 0 && hours <= 2) {
            hours = '2300';
        } else if (hours >= 3 && hours <= 5) {
            hours = '0200';
        } else if (hours >= 6 && hours <= 8) {
            hours = '0500';
        } else if (hours >= 9 && hours <= 11) {
            hours = '0800';
        } else if (hours >= 12 && hours <= 14) {
            hours = '1100';
        } else if (hours >= 15 && hours <= 17) {
            hours = '1400';
        } else if (hours >= 18 && hours <= 20) {
            hours = '1700';
        } else if (hours >= 21 && hours <= 23) {
            hours = '2000';
        }
        return hours;
    }

    const API_KEY = '0d6ebfF%2BFKN%2Fz7kLCYvb01T9lIaf0%2BhvnIR%2BHkzrXe%2FsagnlvpURJd%2FgJPC%2Bl1zQ6xtxyHepZqT21QZMvUBJnw%3D%3D';

    console.log("check params: " + getDate + ", " + getHours() + ", " + API_KEY);

    const url = 'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=' + API_KEY + '&pageNo=' + 1 + '&numOfRows=' + 1 + '&dataType=JSON' + '&base_date=' + getDate + '&base_time=' + getHours() + '&nx=55&ny=127';

    console.log("check url: " + url);

    fetch(url, {
        method: 'GET'
    }).then(response => response.json()).then(data => {
        console.log(data);
        const items = data.response.body.items.item[0].fcstValue;
        console.log(items)

        const temperature = document.getElementById('temperatureValue');
        temperature.textContent = `${items}°C` + "   " + showDate;
    })

}

function loadCalender() {
    fetch('/Calender').then(function (response) {
        return response.text();
    }).then(function (html) {
        document.getElementById('content-page').innerHTML = html;
        let script = document.createElement('script');
        script.src = '/js/Calender.js';
        script.onload = function () {
            console.log('Calender.js loaded');

            if (typeof init() === 'function') {
                init()
            }
        }
        document.head.appendChild(script);
    }).catch(function (err) {
        console.warn('Something went wrong.', err);
    });
}

function loadShareBoard() {
    fetch('/ShareBoard')
        .then(function (response) {
            return response.text();
        }).then(function (html) {
        document.getElementById('content-page').innerHTML = html;
    }).catch(function (err) {
        console.warn('Something went wrong.', err);
    });
}

function loadFreeBoard() {
    fetch('/FreeBoard').then(function (response) {
        return response.text();
    }).then(function (html) {
        document.getElementById('content-page').innerHTML = html;
        let script = document.createElement('script');
        script.src = '/js/FreeBoard.js';
        document.head.appendChild(script);
    }).catch(function (err) {
        console.warn('Something went wrong.', err);
    });
}

function loadMyInfo() {
    fetch('/MyInfo').then(function (response) {
        return response.text();
    }).then(function (html) {
        document.getElementById('content-page').innerHTML = html;
    }).catch(function (err) {
        console.warn('Something went wrong.', err);
    });
}