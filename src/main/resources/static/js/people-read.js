const results = document.querySelector("#results");

const idBox = document.querySelector("#personId");

// Maybe give a page when the person does not exist

// ^^^ need to handle when the lookup doesnt exist

const getParams = () => {
	let queryStr = window.location.search;
	let paramPairs = queryStr.substr(1).split('&');
	
	var params = {};
	for (var i = 0; i < paramPairs.length; i++) {
	    var parts = paramPairs[i].split('=');
	    params[parts[0]] = parts[1];
	}
	
	return params;
}

const searchPerson = () => {
	let params = getParams();
	
	if (params.id !== undefined && params.id !== null && params.id !== "NaN") {
		idSearch(params.id);
	}
}

const idSearch = (id) => {
	fetch("person/readVehicles/"+id)
	.then((response) => {
		if (response.status === 200) {
			return response.json();
		} else {
			results.innerHTML = "Person does not exist.";
		}
	})
	.then((json) => parseDetails(id, json)); 
}

const deletePerson = () => {
	let params = getParams();
	
	if (params.id !== undefined && params.id !== null && params.id !== "NaN") {
		idDelete(params.id);
	} else {
		console.log("Not enough data to search");
	}
}

const idDelete = (id) => {
	fetch("person/delete/"+id, {
		method: "DELETE"
	})
	.then((response) => {
		if (response.status === 204) {
			results.innerHTML = "User " + id + " has succesfully been deleted.";
		}
	});
	// need to make it cleaner when we do delte to make it more intuitive.
}

// Person details layout
const parseDetails = (id, json) => {
	let list = document.createElement("ul");
	list.setAttribute("class", "list-group");
	
	let idElement = document.createElement("li");
	idElement.setAttribute("class", "list-group-item");
	let idText = document.createTextNode("Person ID: " + id);
	idElement.appendChild(idText);
	list.appendChild(idElement);
	
	let nameElement = document.createElement("li");
	nameElement.setAttribute("class", "list-group-item");
	let nameText = document.createTextNode(json.firstName + " " + json.surname);
	nameElement.appendChild(nameText);
	list.appendChild(nameElement);
	
	let addressElement = document.createElement("li")
	addressElement.setAttribute("class", "list-group-item");
	let addressText = document.createTextNode(json.address);
	addressElement.appendChild(addressText);
	list.appendChild(addressElement);
	
	let phoneElement = document.createElement("li");
	phoneElement.setAttribute("class", "list-group-item");
	let phoneText = document.createTextNode(json.phone);
	phoneElement.appendChild(phoneText);
	list.appendChild(phoneElement);
	
	
	// do vehicles loop here
	
	for (let i = 0; i < json.vehicleList.length; i++) {
		let vehicleElement = document.createElement("li");
		vehicleElement.setAttribute("class", "list-group-item");
		let vehicleText = document.createTextNode(json.vehicleList[i].registrationNumber + ": " + json.vehicleList[i].make + " " + json.vehicleList[i].model + " ");
		vehicleElement.appendChild(vehicleText);
		let vehicleLink = document.createElement("a");
		vehicleLink.setAttribute("href", "/vehicles-read.html?id="+json.vehicleList[i].id)
		let vehicleLinkText = document.createTextNode("View");
		vehicleLink.appendChild(vehicleLinkText);
		vehicleElement.appendChild(vehicleLink);
		list.appendChild(vehicleElement);
	}
	
	results.appendChild(list);	
	results.appendChild(document.createElement("br"))
	
	// Delete button
	let deleteButton = document.createElement("button");
	deleteButton.setAttribute("onClick", "idDelete("+id+");");
	deleteButton.setAttribute("class", "btn btn-danger");
	deleteButton.innerHTML = "Delete";
	results.appendChild(deleteButton);
}

// Button methods
const idSubmit = () => {
	window.location.href = "people-read.html?id=" + Number.parseInt(idBox.value);
}