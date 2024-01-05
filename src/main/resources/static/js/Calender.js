let clickedDate;

let section = 'company';

let selectedYear;

let selectedMonth;

let schedules = {};

function generateCalendar(year, month) {
    const today = new Date();
    const currentYear = year || today.getFullYear();
    const currentMonth = month !== undefined ? month : today.getMonth();
    const nextMonthFirstDay = new Date(currentYear, currentMonth + 1, 1).getDate();

    const firstDayOfMonth = new Date(currentYear, currentMonth, 1).getDay();
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();

    let nextMonthDayCounter = 1;

    const displayMonth = document.getElementById('displayMonth');
    const monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    displayMonth.textContent = monthNames[parseInt(currentMonth)];

    const calendarBody = document.getElementById('calendar').getElementsByTagName('tbody')[0];
    calendarBody.innerHTML = '';

    let date = 1; // 현재 달의 날짜를 추적하는 변수입니다.
    let printingDate = false; // 날짜 인쇄를 시작할지 여부를 결정하는 플래그입니다.

    for (let i = 0; i < 6; i++) {
        let row = document.createElement('tr');

        for (let j = 0; j < 7; j++) {
            let cell = document.createElement('td');
            cell.classList.add('top-right');

            if (i === 0 && j === firstDayOfMonth) {
                printingDate = true; // 첫 번째 날짜를 인쇄하기 시작합니다.
            }

            if (!printingDate) {
                cell.appendChild(document.createTextNode(''));
            } else {
                if (date > daysInMonth) {
                    printingDate = false; // 현재 달의 날짜 인쇄를 중단합니다.
                    cell.classList.add('next-month');
                    let cellText = document.createTextNode(nextMonthDayCounter);
                    cell.appendChild(cellText);
                    nextMonthDayCounter++;
                } else {
                    let cellText = document.createTextNode(date);
                    cell.appendChild(cellText);

                    if (date === today.getDate() && currentMonth === today.getMonth()) {
                        cell.classList.add('today');
                    }

                    let formattedDate = String(date).padStart(2, '0');
                    let scheduleList = schedules[formattedDate];
                    if (scheduleList) {
                        if (scheduleList.length <= 3) {
                            scheduleList.forEach(schedule => {
                                let scheduleEl = document.createElement('div');
                                scheduleEl.textContent = schedule;
                                scheduleEl.classList.add('schedule-item');
                                cell.appendChild(scheduleEl);
                            });
                        } else {
                            for (let i = 0; i < 2; i++) {
                                let scheduleEl = document.createElement('div');
                                scheduleEl.textContent = scheduleList[i];
                                scheduleEl.classList.add('schedule-item');
                                cell.appendChild(scheduleEl);
                            }
                            let remainingCount = scheduleList.length - 2;
                            let moreScheduleEl = document.createElement('div');
                            moreScheduleEl.textContent = `+${remainingCount}`;
                            moreScheduleEl.classList.add('schedule-item');
                            cell.appendChild(moreScheduleEl);
                        }
                    }

                    (function (currentDate) {
                        cell.addEventListener('click', function () {
                            clickedDate = `${currentYear}-${String(currentMonth + 1).padStart(2, '0')}-${String(currentDate).padStart(2, '0')}`;
                            console.log(clickedDate);
                            const headerDate = document.getElementById('headerDate');
                            headerDate.textContent = clickedDate;
                            const sidebar = document.getElementById('sidebar');
                            sidebar.style.width = '250px';

                            updateSidebarWithSchedules(clickedDate);
                        });
                    })(date);
                    date++;
                }
            }

            row.appendChild(cell);

        }
        calendarBody.appendChild(row);
    }
}


function generateSelect() {
    const today = new Date();
    const currentYear = today.getFullYear();
    const currentMonth = today.getMonth() + 1;
    const selectMonth = document.getElementById('selectMonth');
    const selectYear = document.getElementById('selectYear');

    selectYear.innerHTML = '';
    selectMonth.innerHTML = '';

    for (let i = 0; i < 10; i++) {
        let yearOption = document.createElement('option');
        yearOption.value = currentYear - 5 + i;
        yearOption.textContent = currentYear - 5 + i;

        if (currentYear === currentYear - 5 + i) {
            yearOption.selected = true;
        }

        selectYear.appendChild(yearOption);
    }

    for (let i = 1; i <= 12; i++) {
        let monthOption = document.createElement('option');
        monthOption.value = i;
        monthOption.textContent = i < 10 ? '0' + i : i;

        if (currentMonth === i) {
            monthOption.selected = true;
        }

        selectMonth.appendChild(monthOption);
    }
}

function closeSidebar() {
    let sidebar = document.getElementById('sidebar');
    if (sidebar.style.width === '250px') {
        sidebar.style.width = '0';
    } else {
        sidebar.style.width = '250px';
    }
}

function superCloseSidebar() {
    let sidebar = document.getElementById('sidebar');
    sidebar.style.width = '0';
}


function updateCalendar() {
    selectedYear = parseInt(document.getElementById('selectYear').value);
    selectedMonth = parseInt(document.getElementById('selectMonth').value) - 1;
    schedules = {};
    if (section === 'company') {
        getCompanySchedule();
    } else {
        generateCalendar(selectedYear, selectedMonth);
    }
}

function updateSidebarWithSchedules(date) {

    let sidebar = document.getElementById('sidebarContent');
    sidebar.innerHTML = '';
    let formattedDate = date.slice(-2);
    let dailySchedules = schedules[formattedDate];

    if (dailySchedules) {
        dailySchedules.forEach(schedule => {

            const entryContainer = document.createElement('div');
            entryContainer.classList.add('diary-entry-container');
            if (section === 'personal') {
                const newCheckBox = document.createElement('input');
                newCheckBox.type = 'checkbox';
                newCheckBox.classList.add('form-check-input');
                newCheckBox.setAttribute('data-id', schedule);
                entryContainer.appendChild(newCheckBox);
            }
            const newEntry = document.createElement('h5');
            newEntry.textContent = schedule;
            newEntry.classList.add('text-primary-emphasis', 'diary-text');

            entryContainer.style.display = 'flex';
            entryContainer.style.alignItems = 'center';
            entryContainer.style.gap = '10px';

            entryContainer.appendChild(newEntry);
            sidebar.appendChild(entryContainer);
        });
    }
}

function addDiary() {
    const inputText = prompt(clickedDate + "의 일정을 입력해주세요.");
    const emp_id = String(document.getElementById('emp_id').value)
    console.log("inputText: " + inputText + " emp_id: " + emp_id);
    if (inputText) {

        const sidebar = document.getElementById('sidebarContent');

        const entryContainer = document.createElement('div');
        entryContainer.classList.add('diary-entry-container');

        if (section === 'personal') {
            const newCheckBox = document.createElement('input');
            newCheckBox.type = 'checkbox';
            newCheckBox.classList.add('form-check-input');
            newCheckBox.setAttribute('data-id', inputText);
            entryContainer.appendChild(newCheckBox);
        }

        const newEntry = document.createElement('h5');
        newEntry.textContent = inputText;
        newEntry.classList.add('text-primary-emphasis', 'diary-text');

        entryContainer.style.display = 'flex';
        entryContainer.style.alignItems = 'center';
        entryContainer.style.gap = '10px';

        entryContainer.appendChild(newEntry);
        sidebar.appendChild(entryContainer);
        console.log("check inputText: " + inputText + " emp_id: " + emp_id);

        fetch('/EmployeeSchedule/Save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({emp_Id: emp_id, date: clickedDate, text: inputText})
        })
            .then(response => response.text())
            .then(data => {
                console.log(data);
                personal();
            })
            .catch(error => console.error('Error:', error));
    }
}


function deleteDiary() {
    const entries = document.getElementsByClassName('diary-entry-container');
    for (let i = entries.length - 1; i >= 0; i--) {
        let entry = entries[i];
        let checkBox = entry.querySelector('input[type="checkbox"]');
        if (checkBox && checkBox.checked) {
            const emp_id = String(document.getElementById('emp_id').value)
            let text = checkBox.getAttribute('data-id');

            console.log("check inputText: " + text + " emp_id: " + emp_id);

            fetch('/EmployeeSchedule/Delete', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({emp_Id: emp_id, date: clickedDate, text: text})
            })
                .then(response => response.text())
                .then(data => {
                    console.log(data);
                    superCloseSidebar();
                    getEmployeeSchedule();
                    if (data.status === 'success') {
                        sidebar.removeChild(entry);
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    }
}

function updateButtonsVisibility() {
    const addDiaryButton = document.getElementById('addDiaryButton');
    const deleteDiaryButton = document.getElementById('deleteDiaryButton');

    if (section === 'personal') {
        addDiaryButton.style.display = 'inline-block';
        deleteDiaryButton.style.display = 'inline-block';
    } else {
        addDiaryButton.style.display = 'none';
        deleteDiaryButton.style.display = 'none';
    }
}

function company() {
    section = 'company';
    console.log(section);
    superCloseSidebar();
    getCompanySchedule();
    updateButtonsVisibility();
}

function team() {
    section = 'team';
    console.log(section);
    superCloseSidebar();
    getTeamSchedule();
    updateButtonsVisibility();

}

function personal() {
    section = 'personal';
    console.log(section);
    superCloseSidebar();
    getEmployeeSchedule();
    updateButtonsVisibility();
}

function init() {
    console.log("init");
    generateSelect();
    updateButtonsVisibility();

    const today = new Date();
    const currentMonth = today.getMonth();
    const currentYear = today.getFullYear();
    const currentDate = today.getDate();

    let clickedDate = `${currentYear}-${String(currentMonth + 1).padStart(2, '0')}-${String(currentDate).padStart(2, '0')}`;

    console.log("기본 설정된 오늘 날짜: ", clickedDate);
    const selectedYear = parseInt(document.getElementById('selectYear').value);
    const selectedMonth = parseInt(document.getElementById('selectMonth').value) - 1; // 월은 0-11로 표현됩니다.
    getCompanySchedule();
};

window.onload = function () {
    console.log("window.onload");
    init();
    setTimeout(company, 500);  // Delay of 500 milliseconds
};

function getCompanySchedule() {
    const selectedYear = String(document.getElementById('selectYear').value);
    const selectedMonth = String(((document.getElementById('selectMonth').value) - 1) < 10 ? '0' + (document.getElementById('selectMonth').value - 1) : document.getElementById('selectMonth').value - 1);

    console.log("Selected Year: ", selectedYear + " Selected Month: " + selectedMonth);

    const queryString = `?selectedYear=${selectedYear}&selectedMonth=${selectedMonth}`;

    fetch('/CompanySchedule' + queryString, {
        method: 'GET'
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Server response wasn\'t OK');
            }
        })
        .then(data => {
            console.log(data);
            schedules = data;
            generateCalendar(selectedYear, parseInt(selectedMonth));
        })
        .catch(error => {
            console.error('Fetch Error:', error);
        });
}

function getTeamSchedule() {
    const selectedYear = String(document.getElementById('selectYear').value);
    const selectedMonth = String(((document.getElementById('selectMonth').value) - 1) < 10 ? '0' + (document.getElementById('selectMonth').value - 1) : document.getElementById('selectMonth').value - 1);
    const department = String(document.getElementById('emp_department').value)

    console.log("Selected Year: ", selectedYear + " Selected Month: " + selectedMonth + " Department: " + department);

    const queryString = `?selectedYear=${selectedYear}&selectedMonth=${selectedMonth}&department=${department}`;

    fetch('/TeamSchedule' + queryString, {
        method: 'GET'
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Server response wasn\'t OK');
            }
        })
        .then(data => {
            console.log(data);
            schedules = data;
            generateCalendar(selectedYear, parseInt(selectedMonth));
        })
        .catch(error => {
            console.error('Fetch Error:', error);
        });
}

function getEmployeeSchedule() {
    const selectedYear = String(document.getElementById('selectYear').value);
    const selectedMonth = String(((document.getElementById('selectMonth').value) - 1) < 10 ? '0' + (document.getElementById('selectMonth').value - 1) : document.getElementById('selectMonth').value - 1);
    const emp_id = String(document.getElementById('emp_id').value)

    console.log("Selected Year: ", selectedYear + " Selected Month: " + selectedMonth + " emp_id: " + emp_id);

    const queryString = `?selectedYear=${selectedYear}&selectedMonth=${selectedMonth}&emp_id=${emp_id}`;

    fetch('/EmployeeSchedule' + queryString, {
        method: 'GET'
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Server response wasn\'t OK');
            }
        })
        .then(data => {
            console.log(data);
            schedules = data;
            generateCalendar(selectedYear, parseInt(selectedMonth));
        })
        .catch(error => {
            console.error('Fetch Error:', error);
        });
}
