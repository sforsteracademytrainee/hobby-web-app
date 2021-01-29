const regBox = document.querySelector("#reg");
const makeBox = document.querySelector("#make");
const modelBox = document.querySelector("#model");

const createVehicle = () => {
	let reg = regBox.value;
	let make = makeBox.value;
	let model = modelBox.value;
	
	if (reg != "" && make != "" && model != "") {
		let json = JSON.stringify({
	        "registrationNumber": reg,
	        "make": make,
			"model": model
	    })
		console.log(json);
	    fetch("/vehicle/create", {
		    method: 'POST',
		    body: json,
		    headers: {
		        'Content-type': 'application/json'
		    }
		})
		.then(response => response.json())
	    .then(json => console.log(json))
	    .catch(err => console.error(err))
	}
}