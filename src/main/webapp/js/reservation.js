function reserv_btn_click() {
	const bar = document.querySelector('.bottom-bar-container');
	bar.classList.toggle('down');
}

function stationModalOpen(type) {
	const modal = document.querySelector('.station-modal-zindex');
	modal.style.display = "block";
	modal.removeAttribute("id");
	modal.id = type;
	resetCity();
}

function personModalOpen() {
	const modal = document.querySelector('.person-modal-zindex');
	modal.style.display = "block";
}

document.addEventListener("DOMContentLoaded", function() {
	const selects = document.querySelectorAll(".select");
	selects.forEach(select => {
		const header = select.querySelector("h3 a");
		const option = select.querySelector(".option");

		header.addEventListener("click", function(e) {
			e.preventDefault();
			document.querySelectorAll(".option").forEach(opt => {
				if (opt !== option) opt.style.display = "none";
			});
			document.querySelectorAll(".select").forEach(s => {
				if (s !== select) s.classList.remove("active");
			});

			const isOpen = option.style.display === "block";
			option.style.display = isOpen ? "none" : "block";
			select.classList.toggle("active", !isOpen);
		});
	});
});

function resetCity() {
	const defaultCity = document.querySelector('a.city-link[data-citycode="11"]');
	if (defaultCity) defaultCity.click();
}

function get_reservation() {
	const data = {
		start_station: document.getElementById("start-station").dataset.station,
		last_station: document.getElementById("last-station").dataset.station,
		day: document.querySelector(".btn-date").dataset.reservationDay,
		time: document.querySelector(".btn-date").dataset.reservationTime,
		adult: document.getElementById("adult").value,
		child: document.getElementById("child").value,
		elderly: document.getElementById("elderly").value,
		nice: document.getElementById("nice").value,
		total: document.getElementById("person_total").value,
		pageNum: String(document.getElementById("pageNum").value)
	};

	fetch("/reservation/getrail", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify(data)
	})
		.then(response => {
			if (!response.ok) throw new Error("네트워크 오류");
			return response.json();
		})
		.then(result => {
			console.log("예약 성공:", result);
		})
		.catch(error => {
			console.error("예약 실패:", error);
		});
}

function nextPage(pageNum) {
	pageNum += 1;
	console.log(pageNum);
	get_reservation(pageNum);
}

function submitReservation() {
	const trainId = document.getElementById("form-trainId").value;
	const startTime = document.getElementById("form-startTime").value;
	const dateStr = document.getElementById("form-date").value;

	if (!trainId || !startTime || !dateStr) {
		alert("예매할 열차를 먼저 선택하세요.");
		return;
	}

	// 출발 시간과 날짜 합쳐서 생성
	const [hourStr, minStr] = startTime.split(":");
	const [year, month, day] = dateStr.split("-").map(Number);
	const depDate = new Date(year, month - 1, day, parseInt(hourStr), parseInt(minStr));

	const now = new Date();
	if (depDate <= now) {
		alert("이미 출발한 열차는 예매할 수 없습니다.");
		return;
	}

	document.getElementById("reservationForm").submit();
}


// 클릭 시 티켓 정보만 세팅되도록 수정됨 (즉시 submit X)
document.querySelectorAll('.price-box').forEach(box => {
	box.addEventListener('click', function() {
		const infoBox = this.closest('.rail-inner');
		const h2 = infoBox.querySelector('h2');
		if (!h2) return;
		const text = h2.textContent; // e.g. 서울 → 부산 (05:27 ~ 08:15)
		const match = text.match(/(\d{2}):(\d{2})/);
		if (!match) return;
		const trainId = infoBox.querySelector('.num')?.textContent || '';
		const route = h2.textContent.match(/([가-힣]+) → ([가-힣]+)/);
		if (!route) return;

		const start = route[1];
		const last = route[2];
		const times = text.match(/\((\d{2}:\d{2}) ~ (\d{2}:\d{2})\)/);
		if (!times) return;

		selectTicket(trainId, start, last, times[1], times[2]);
	});

});

function selectTicket(trainId, start, last, startTime, lastTime, date, trainName, price) {
	document.getElementById("select-tiket").innerText = `${start}→${last} (${startTime} ~ ${lastTime})`;

	document.getElementById("form-trainId").value = trainId;
	document.getElementById("form-startStation").value = start;
	document.getElementById("form-lastStation").value = last;
	document.getElementById("form-startTime").value = startTime;
	document.getElementById("form-lastTime").value = lastTime;
	document.getElementById("form-trainName").value = trainName;
	document.getElementById("form-price").value = price;

	// 이게 있어야 submitReservation에서 비교 가능
	const dateInput = document.getElementById("form-date");
	if (dateInput) {
		dateInput.value = date;
	} else {
		const hidden = document.createElement("input");
		hidden.type = "hidden";
		hidden.name = "date";
		hidden.id = "form-date";
		hidden.value = date;
		document.getElementById("reservationForm").appendChild(hidden);
	}
}
