function reserv_btn_click() {
    const bar = document.querySelector('.bottom-bar-container');
    bar.classList.toggle('down');
}

function stationModalOpen() {
	const modal = document.querySelector('.station-modal-zindex');
	modal.style.display = "block";
}

function personModalOpen() {
	const modal = document.querySelector('.person-modal-zindex');
	modal.style.display = "block";
}