const personFirstName = document.querySelector("#personFirstName");
const personSurname = document.querySelector("#personSurname");
const personAddress = document.querySelector("#personAddress");
const personPhone = document.querySelector("#personPhone");
const personId = document.querySelector("#personId");
const form = document.querySelector("#form");
const updateButton = document.querySelector("#updateButton");
const currentEdit = document.querySelector("#currentEdit");

const idSubmit = () => {
	let id = personId.value;
	fetch("person/read/"+id)
	.then((response) => {
		if (response.status === 200) {
			results.innerHTML = "";
			return response.json();
		} else {
			form.setAttribute("class", "d-none");
			results.innerHTML = "Person does not exist.";
		}
	})
	.then((json) => {
		if (json != null) populateForm(id, json);
	})
	.catch(err => console.error(err));
}

const populateForm = (id, json) => {
	currentEdit.innerHTML = "Editing ID: " + id;
	personFirstName.setAttribute("value", json.firstName);
	personSurname.setAttribute("value", json.surname);
	personAddress.setAttribute("value", json.address);
	personPhone.setAttribute("value", json.phone);
	updateButton.setAttribute("onClick", "updatePerson("+id+");")
	form.setAttribute("class", "");
}

const updatePerson = (id) => {
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
	    });
		console.log(json);
	    fetch("/person/update/"+id, {
		    method: 'PUT',
		    body: json,
		    headers: {
		        'Content-type': 'application/json'
		    }
		})
		.then(response => {
			if (response.status == 202) {
				window.location.href = "people-read.html?id="+id;
			}
			return response.json();
		})
	    .then(json => console.log(json))
	    .catch(err => console.error(err))
	}
}