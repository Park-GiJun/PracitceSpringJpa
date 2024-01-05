function generateAttendCalender(year, month){
    console.log("generateAttendCalendar");

    const infoDate = new Date();
    const infoYear = year !== undefined? year : infoDate.getFullYear();
    const infoMonth = month !== undefined? month : infoDate.getMonth;

    const firstDayOfMonth = new Date(infoYear, infoMonth, 1).getDay();

    let nextDayCounter = 1;

    const displayDate = new Date(infoYear, infoMonth + 1, 1);
    const displayInfoDate = document.getElementById('displayDate');
    displayInfoDate.textContent = String(displayDate);
}