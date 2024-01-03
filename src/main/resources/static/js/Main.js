document.getElementById('calendar-link').addEventListener('click', function (e) {
    console.log("Calendar clicked");
    e.preventDefault();
    fetch('/Calender').then(function (response) {
        return response.text();
    }).then(function (html) {
        document.getElementById('content-page').innerHTML = html;
        let script = document.createElement('script');
        script.src = '/js/Calender.js';
        script.onload = function () {
            console.log('Calender.js loaded');

            if(typeof init() === 'function'){
                init()
            }
        }
        document.head.appendChild(script);
    }).catch(function (err) {
        console.warn('Something went wrong.', err);
    });
});

document.getElementById('shareBoard-link').addEventListener('click', function (e) {
    console.log("ShareBoard clicked");
    e.preventDefault();
    fetch('/ShareBoard')
        .then(function (response) {
        return response.text();
    }).then(function (html) {
        document.getElementById('content-page').innerHTML = html;
    }).catch(function (err) {
        console.warn('Something went wrong.', err);
    });
});

document.getElementById('freeBoard-link').addEventListener('click', function (e) {
    console.log("FreeBoard clicked");
    e.preventDefault();
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
});

document.getElementById('myInfo-link').addEventListener('click', function (e) {
    console.log("MyInfo clicked");
    e.preventDefault();
    fetch('/MyInfo').then(function (response) {
        return response.text();
    }).then(function (html) {
        document.getElementById('content-page').innerHTML = html;
    }).catch(function (err) {
        console.warn('Something went wrong.', err);
    });
});