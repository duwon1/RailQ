document.addEventListener("DOMContentLoaded", function () {
    const infoDay = document.querySelector(".info_day");
    const dateDate = document.querySelector(".date-date");
    const tbody = document.getElementById("calendar-body");
    const timeButtons = document.querySelectorAll('.slick-item button');
    const slickListBoxes = document.querySelectorAll(".slick-list-box");
    const slickRacks = document.querySelectorAll(".slick-rack");
    const leftButton = document.querySelector(".slick-list-left-btn");
    const rightButton = document.querySelector(".slick-list-right-btn");
    const leftMonthBtn = document.getElementById("left-day");
    const rightMonthBtn = document.getElementById("right-day");
    const infoTimeEl = document.querySelector(".info_time");
    const btnDate = document.querySelector(".btn-date");

    let currentPosition = 0;
    let currentDate = new Date();

    const dayNames = ['일', '월', '화', '수', '목', '금', '토'];
    const dateFromServer = document.getElementById("getDate").value;
    const timeFromServer = document.getElementById("gettime").value;

    function holidayAPI(year, month) {
        const url = `http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?serviceKey=H5P66sxp6BnNWukmmYbToCrdFD4OB3GT0ynRUpfXlBmm5L9scMtyAtVAsShHgWk1qiAUEdE%2BQTd9D7i00bi%2Fug%3D%3D&solYear=${year}&solMonth=${String(month).padStart(2, '0')}`;
        fetch(url)
            .then(res => res.text())
            .then(str => {
                const xml = new window.DOMParser().parseFromString(str, "text/xml");
                const items = xml.getElementsByTagName("item");
                for (let item of items) {
                    const locdate = item.getElementsByTagName("locdate")[0].textContent;
                    const formatted = `${locdate.slice(0, 4)}-${locdate.slice(4, 6)}-${locdate.slice(6, 8)}`;
                    const td = document.querySelector(`td[id="${formatted}"]`);
                    if (td) td.classList.add("holiday");
                }
            });
    }

    function initializeCalendar(baseDate) {
        const year = baseDate.getFullYear();
        const month = baseDate.getMonth();
        const today = new Date();
        const twoWeeksLater = new Date(today);
        twoWeeksLater.setDate(today.getDate() + 14);

        tbody.innerHTML = "";
        dateDate.textContent = `${year}년 ${month + 1}월`;

        // ✅ 기존 선택 초기화
        document.querySelectorAll("#calendar-body td.selected").forEach(td => td.classList.remove("selected"));

        const firstDay = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();

        let date = 1;
        for (let i = 0; i < 6; i++) {
            const tr = document.createElement("tr");
            for (let j = 0; j < 7; j++) {
                const td = document.createElement("td");
                if ((i === 0 && j < firstDay) || date > lastDate) {
                    td.innerHTML = "";
                } else {
                    const d = new Date(year, month, date);
                    const formatted = `${year}-${String(month + 1).padStart(2, '0')}-${String(date).padStart(2, '0')}`;
                    const dayName = dayNames[d.getDay()];
                    td.textContent = date;
                    td.setAttribute("data-date", `${formatted}(${dayName})`);
                    td.setAttribute("id", formatted);

                    if (d < today.setHours(0, 0, 0, 0) || d > twoWeeksLater) {
                        td.classList.add("disabled");
                    } else {
                        td.addEventListener("click", () => handleDateClick(td));
                    }
                    date++;
                }
                tr.appendChild(td);
            }
            tbody.appendChild(tr);
        }

        holidayAPI(year, month + 1);
    }

    function handleDateClick(td) {
        document.querySelectorAll("#calendar-body td").forEach(cell => cell.classList.remove("selected"));
        td.classList.add("selected");

        const selectedDate = td.getAttribute("data-date");
        const [datePart, dayKor] = selectedDate.split('(');
        const [year, month, day] = datePart.split('-');
        infoDay.textContent = `${year}년 ${month}월 ${day}일(${dayKor.replace(')', '')})`;

        // 시간 초기화
        timeButtons.forEach(btn => btn.classList.remove("selected"));
        infoTimeEl.textContent = "시간 선택";
        updateTimeButtons(datePart, null);
    }

    function selectDateAndTime(dateStr, hourStr) {
        const td = document.querySelector(`td[id='${dateStr}']`);
        if (!td || td.classList.contains("disabled")) return;

        td.classList.add("selected");

        const dateObj = new Date(dateStr);
        const dayName = dayNames[dateObj.getDay()];
        infoDay.textContent = `${dateObj.getFullYear()}년 ${String(dateObj.getMonth() + 1).padStart(2, '0')}월 ${String(dateObj.getDate()).padStart(2, '0')}일(${dayName})`;

        btnDate.textContent = `${dateStr}(${dayName}) ${hourStr}:00`;
        btnDate.dataset.reservationDay = dateStr.replace(/-/g, "");
        btnDate.dataset.reservationTime = hourStr;

        updateTimeButtons(dateStr, parseInt(hourStr));
    }

    function updateTimeButtons(dateStr, selectedHour) {
        const todayStr = new Date().toISOString().split("T")[0];
        const isToday = dateStr === todayStr;
        const nowHour = new Date().getHours();

        timeButtons.forEach(btn => {
            const hour = parseInt(btn.textContent);
            btn.classList.remove("selected", "disabled");

            if (isToday && hour < nowHour) {
                btn.classList.add("disabled");
            } else if (selectedHour === hour) {
                btn.classList.add("selected");
                infoTimeEl.textContent = `${hour}:00 이후 출발`;
                scrollToButton(btn);
            }
        });
    }

    function scrollToButton(button) {
        const rack = button.closest(".slick-rack");
        const index = Array.from(slickRacks).indexOf(rack);
        currentPosition = -(index * 640);
        slickListBoxes.forEach(box => box.style.transform = `translateX(${currentPosition}px)`);
    }

    // 시간 선택
    timeButtons.forEach(button => {
        button.addEventListener("click", function () {
            if (this.classList.contains("disabled")) return;
            timeButtons.forEach(btn => btn.classList.remove("selected"));
            this.classList.add("selected");

            const hour = this.textContent.replace("시", "");
            infoTimeEl.textContent = `${hour}:00 이후 출발`;
            scrollToButton(this);
        });
    });

    // 시간 슬라이드
    leftButton.addEventListener("click", () => {
        currentPosition += 640;
        if (currentPosition > 0) currentPosition = 0;
        slickListBoxes.forEach(box => box.style.transform = `translateX(${currentPosition}px)`);
    });

    rightButton.addEventListener("click", () => {
        currentPosition -= 640;
        const limit = -((slickRacks.length - 1) * 640);
        if (currentPosition < limit) currentPosition = limit;
        slickListBoxes.forEach(box => box.style.transform = `translateX(${currentPosition}px)`);
    });

    // ✅ 달 이동 시 자동 재선택 적용
    leftMonthBtn.addEventListener("click", () => {
        currentDate.setMonth(currentDate.getMonth() - 1);
        initializeCalendar(currentDate);
        selectDateAndTime(dateFromServer, timeFromServer);
    });

    rightMonthBtn.addEventListener("click", () => {
        currentDate.setMonth(currentDate.getMonth() + 1);
        initializeCalendar(currentDate);
        selectDateAndTime(dateFromServer, timeFromServer);
    });

    // 초기 실행
    initializeCalendar(currentDate);
    selectDateAndTime(dateFromServer, timeFromServer);
});

// 모달 열기/닫기
function date_modal_open() {
    document.querySelector(".date-wrap-z-index").style.display = "block";
}
function date_modal_close() {
    document.querySelector(".date-wrap-z-index").style.display = "none";
}

// 날짜/시간 선택 완료
function date_submit() {
    const selectedTd = document.querySelector("#calendar-body td.selected");
    const selectedTime = document.querySelector(".slick-item .selected");

    if (!selectedTd) return alert("날짜를 선택해주세요");
    if (!selectedTime) return alert("시간을 선택해주세요");

    const dateText = selectedTd.getAttribute("data-date");
    const [datePart, dayKor] = dateText.split("(");
    const hour = selectedTime.textContent.replace("시", "");
    const btnDate = document.querySelector(".btn-date");

    btnDate.textContent = `${datePart}(${dayKor.replace(")", "")}) ${hour}:00`;
    btnDate.dataset.reservationDay = datePart.replace(/-/g, "");
    btnDate.dataset.reservationTime = hour;

    date_modal_close();
	updateReservationForm();
}
