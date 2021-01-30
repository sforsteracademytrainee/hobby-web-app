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
	.then((json) => parseDetails(json)); 
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
const parseDetails = (json) => {
	
	let list = document.createElement("ul");
	list.setAttribute("class", "list-group");
	
	let idElement = document.createElement("li");
	idElement.setAttribute("class", "list-group-item");
	let idText = document.createTextNode("Vehicle ID: " + json.id);
	idElement.appendChild(idText);
	list.appendChild(idElement);
	
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
	
	let keeperAdd = document.createElement("div");
	keeperAdd.setAttribute("class", "col form-floating input-group");
	
	let keeperButtonText;
	
	if (json.keeper != null) { // if has keeper
		let keeperElement = document.createElement("li");
		keeperElement.setAttribute("class", "list-group-item");
		let keeperText = document.createTextNode("Keeper: " + json.keeper.firstName + " " + json.keeper.surname + " ");
		keeperElement.appendChild(keeperText);
		let keeperLink = document.createElement("a");
		keeperLink.setAttribute("href", "people-read.html?id="+json.keeper.id);
		let keeperLinkText = document.createTextNode("View");
		keeperLink.appendChild(keeperLinkText);
		keeperElement.appendChild(keeperLink);
		list.appendChild(keeperElement);
		keeperButtonText = "Update keeper";
		

		
	} else { // if does not have keeper
		keeperButtonText = "Set keeper";
	}
	
	// input for keeper id
	let keeperBox = document.createElement("input");
	keeperBox.setAttribute("type", "number");
	keeperBox.setAttribute("id", "keeperId");
	keeperBox.setAttribute("name", "keeperId");
	keeperBox.setAttribute("class", "form-control");
	keeperBox.setAttribute("placeholder", "Keeper ID:")
	keeperAdd.appendChild(keeperBox);
	
	let keeperLabel = document.createElement("label");
	keeperLabel.setAttribute("for", "keeperId");
	keeperLabel.innerHTML = "Set a keeper ID:";
	keeperAdd.appendChild(keeperLabel);
			
	let keeperButton = document.createElement("button");
	keeperButton.setAttribute("onClick", "keeperUpdate();");
	keeperButton.setAttribute("class", "btn btn-primary");
	keeperButton.innerHTML = keeperButtonText;
	keeperAdd.appendChild(keeperButton);
	
	// String it all together
	
	results.appendChild(list);
	results.appendChild(document.createElement("br"))
	
	results.appendChild(keeperAdd);
    results.appendChild(document.createElement("br"));
	
	if (json.keeper != null) {
		let removeButton = document.createElement("button");
		removeButton.setAttribute("onClick", "keeperRemove();");
		removeButton.setAttribute("class", "btn btn-warning");
		removeButton.setAttribute("id", "keeperButton")
		removeButton.innerHTML = "Remove keeper";
		results.appendChild(removeButton);
	}
	let deleteButton = document.createElement("button");
	deleteButton.setAttribute("onClick", "idDelete("+json.id+");");
	deleteButton.setAttribute("class", "btn btn-danger");
	deleteButton.innerHTML = "Delete";
	results.appendChild(deleteButton);
	results.appendChild(document.createElement("br"));
}

// Button methods
const idSubmit = () => {
	window.location.href = "vehicles-read.html?id=" + Number.parseInt(idBox.value);
}

const keeperUpdate = () => {
	let keeperId = document.querySelector("#keeperId").value;
	let id  = getParams().id;
	let json = JSON.stringify({
		"keeper": {"id": keeperId}
	});
	fetch("/vehicle/update/"+id, {
		method: 'PUT',
		body: json,
		headers: {
			'Content-type': 'application/json'
		}
	})
	.then(response => {
		if (response.status == 500) {
			results.appendChild(document.createTextNode("Person id invalid"));
		} else {
			window.location.href = "vehicles-read.html?id="+id;
			return response.json;
		}
	})
	.then(json => console.log(json))
	.catch(err => console.error(err));
}

const keeperRemove = () => {
	let id  = getParams().id;
	fetch("/vehicle/update/"+id, {
		method: 'PUT',
		body: JSON.stringify({
					"keeper": null
				}),
		headers: {
			'Content-type': 'application/json'
		}
	})
	.then(response => {
		if (response.status == 500) {
			results.appendChild(document.createTextNode("Person id invalid"));
		} else {
			window.location.href = "vehicles-read.html?id="+id;
			return response.json;
		}
	})
	.then(json => console.log(json))
	.catch(err => console.error(err));
}