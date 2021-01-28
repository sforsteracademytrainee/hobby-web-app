const results = document.querySelector("#results");

const idBox = document.querySelector("#personId");
const firstNameBox = document.querySelector("#personFirstName");
const surnameBox = document.querySelector("#personSurname");

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
	fetch("person/read/"+id)
	.then((response) => {
		if (response.status === 200) {
			return response.json();
		} else {
			results.innerHTML = "Person does not exist.";
		}
	})
	.then((json) => {
		results.innerHTML = json.firstName + " " +json.surname + " " + json.address + " " + json.phone;
		let button = document.createElement("button");
		button.setAttribute("onClick", "idDelete("+id+");");
		button.setAttribute("class", "btn btn-danger");
		button.innerHTML = "Delete";
		results.appendChild(button);
	}) 
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



// Button methods
const idSubmit = () => {
	window.location.href = "people-read.html?id=" + Number.parseInt(idBox.value);
}