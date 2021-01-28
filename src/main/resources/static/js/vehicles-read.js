const results = document.querySelector("#results");

const idBox = document.querySelector("#vehicleId");

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

const searchVehicle = () => {
	let params = getParams();
	
	if (params.id !== undefined && params.id !== null && params.id !== "NaN") {
		idSearch(params.id);
	}
}

const idSearch = (id) => {
	fetch("vehicle/readKeeper/"+id)
	.then((response) => {
		if (response.status === 200) {
			return response.json();
		} else {
			results.innerHTML = "Vehicle does not exist.";
		}
	})
	.then((json) => parseDetails(id, json)); 
}

const deleteVehicle = () => {
	let params = getParams();
	
	if (params.id !== undefined && params.id !== null && params.id !== "NaN") {
		idDelete(params.id);
	} else {
		console.log("Not enough data to search");
	}
}

const idDelete = (id) => {
	fetch("vehicle/delete/"+id, {
		method: "DELETE"
	})
	.then((response) => {
		if (response.status === 204) {
			results.innerHTML = "Vehicle " + id + " has succesfully been deleted.";
		}
	});
	// need to make it cleaner when we do delte to make it more intuitive.
}

// Person details layout
const parseDetails = (id, json) => {
	console.log(json);
	let list = document.createElement("ul");
	list.setAttribute("class", "list-group");
	
	let nameElement = document.createElement("li");
	nameElement.setAttribute("class", "list-group-item");
	let nameText = document.createTextNode(json.registrationNumber);
	nameElement.appendChild(nameText);
	list.appendChild(nameElement);
	
	let addressElement = document.createElement("li");
	addressElement.setAttribute("class", "list-group-item");
	let addressText = document.createTextNode(json.make + " " + json.model);
	addressElement.appendChild(addressText);
	list.appendChild(addressElement);
	
	let keeperElement = document.createElement("li");
	keeperElement.setAttribute("class", "list-group-item");
	let keeperText = document.createTextNode(json.keeper.firstName + " " + json.keeper.surname + " ");
	keeperElement.appendChild(keeperText);
	let keeperLink = document.createElement("a");
	keeperLink.setAttribute("href", "people-read.html?id="+json.keeper.id);
	let keeperLinkText = document.createTextNode("View");
	keeperLink.appendChild(keeperLinkText);
	keeperElement.appendChild(keeperLink);
	list.appendChild(keeperElement);
	
	// do keeper loop here
	
	results.appendChild(list);	
	
	
	// Delete button
	let deleteButton = document.createElement("button");
	deleteButton.setAttribute("onClick", "idDelete("+id+");");
	deleteButton.setAttribute("class", "btn btn-danger");
	deleteButton.innerHTML = "Delete";
	results.appendChild(deleteButton);
}

// Button methods
const idSubmit = () => {
	window.location.href = "vehicles-read.html?id=" + Number.parseInt(idBox.value);
}