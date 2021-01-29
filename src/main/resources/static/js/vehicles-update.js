const vehicleReg = document.querySelector("#reg");
const vehicleMake = document.querySelector("#make");
const vehicleModel = document.querySelector("#model");
const vehicleId = document.querySelector("#vehicleId");
const form = document.querySelector("#form");
const updateButton = document.querySelector("#updateButton");
const currentEdit = document.querySelector("#currentEdit");

const idSubmit = () => {
	let id = vehicleId.value;
	fetch("/vehicle/read/"+id)
	.then((response) => {
		if (response.status === 200) {
			results.innerHTML = "";
			return response.json();
		} else {
			form.setAttribute("class", "d-none");
			results.innerHTML = "Vehicle does not exist.";
		}
	})
	.then((json) => {
		if (json != null) populateForm(id, json);
	})
	.catch(err => console.error(err));
}

const populateForm = (id, json) => {
	currentEdit.innerHTML = "Editing ID: " + id;
	vehicleReg.setAttribute("value", json.registrationNumber);
	vehicleMake.setAttribute("value", json.make);
	vehicleModel.setAttribute("value", json.model);
	updateButton.setAttribute("onClick", "updateVehicle("+id+");")
	form.setAttribute("class", "");
}

const updateVehicle = (id) => {
	let reg = vehicleReg.value;
	let make = vehicleMake.value;
	let model = vehicleModel.value;
	
	if (reg != "" && make != "" && model != "") {
		let json = JSON.stringify({
	        "reg": reg,
	        "make": make,
			"model": model
	    });
		console.log(json);
	    fetch("/vehicle/update/"+id, {
		    method: 'PUT',
		    body: json,
		    headers: {
		        'Content-type': 'application/json'
		    }
		})
		.then(response => {
			if (response.status == 202) {
				window.location.href = "vehicles-read.html?id="+id;
			}
			return response.json();
		})
	    .then(json => console.log(json))
	    .catch(err => console.error(err))
	}
}