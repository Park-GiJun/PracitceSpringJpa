function freeBoardWrite() {
    console.log("freeBoardWrite clicked");
    fetch('/FreeBoard/WritePage')
        .then(function (response) {
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

function Write() {
    console.log("Write clicked");
    let formData = new FormData(document.getElementById('freeBoardForm'));
    fetch('/FreeBoard/WritePage/Write', {
        method: 'POST',
        body: formData
    })
        .then(function (response) {
            return response.text();
        })
        .then(function (html) {
            document.getElementById('content-page').innerHTML = html;
            loadBoardList();
        })
        .catch(function (error) {
            console.error('Error:', error);
        });
}

function loadBoardList() {
    fetch('/FreeBoard')
        .then(function (response) {
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


function loadBoardDetails(idx) {
    console.log("Loading details for IDX: " + idx);
    const url = '/FreeBoard/WritePage/Detail/' + idx;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(html => {
            document.getElementById('content-page').innerHTML = html;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

