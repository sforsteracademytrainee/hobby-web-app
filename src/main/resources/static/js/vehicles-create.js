const regBox = document.querySelector("#reg");
const makeBox = document.querySelector("#make");
const modelBox = document.querySelector("#model");

const createVehicle = () => {
	let reg = regBox.value;
	let make = makeBox.value;
	let model = modelBox.value;
	
	if (reg != "" && make != "" && model != "") {
	    fetch("/vehicle/create", {
		    method: 'POST',
		    body: JSON.stringify({
		        "registrationNumber": reg,
		        "make": make,
				"model": model
		    }),
		    headers: {
		        'Content-type': 'application/json'
		    }
		})
		.then(response => response.json())
	    .then(json => {
			if (json.id != null) {
				window.location.href = "vehicles-read.html?id="+json.id;
			}
		})
	    .catch(err => console.error(err))
	}
}