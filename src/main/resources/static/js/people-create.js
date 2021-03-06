 const personFirstName = document.querySelector("#personFirstName");
const personSurname = document.querySelector("#personSurname");
const personAddress = document.querySelector("#personAddress");
const personPhone = document.querySelector("#personPhone");

const createPerson = () => {
	let firstName = personFirstName.value;
	let surname = personSurname.value;
	let address = personAddress.value;
	let phone = personPhone.value;
	
	if (firstName != "" && surname != "" && address != "" && phone != "") {
		let json = JSON.stringify({
	        "firstName": firstName,
	        "surname": surname,
			"address": address,
			"phone": phone,
	        "vehicleList": [{}]
	    });
	    fetch("/person/create", {
		    method: 'POST',
		    body: json,
		    headers: {
		        'Content-type': 'application/json'
		    }
		})
		.then(response => response.json())
	    .then(json => {
			if (json.id != null) {
				window.location.href = "people-read.html?id="+json.id;
			}
		})
	    .catch(err => console.error(err))
	}
}