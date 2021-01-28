const results = document.querySelector("#results");

const idBox = document.querySelector("#personId");
const firstNameBox = document.querySelector("#personFirstName");
const surnameBox = document.querySelector("#personSurname");

const searchPerson = () => {
	let queryStr = window.location.search;
	let paramPairs = queryStr.substr(1).split('&');
	
	var params = {};
	for (var i = 0; i < paramPairs.length; i++) {
	    var parts = paramPairs[i].split('=');
	    params[parts[0]] = parts[1];
	}
	
	if (params.id !== undefined && params.id !== null && params.id !== "NaN") {
		idBranch(params.id);
	} else if (params.firstName != null && params.surname != null) {
		console.log("do name search");
	} else {
		console.log("not enough data to search");
	}
}

const idBranch = (id) => {
	
	fetch("person/read/"+id)
	.then((response) => response.json())
	.then((json) => {
		results.innerHTML = json.firstName + " " +json.surname + " " + json.address + " " + json.phone;
	}) 
}



// Button methods
const idSearch = () => {
	window.location.href = "people-read.html?id=" + Number.parseInt(idBox.value);
}

const nameSearch = () => {
	window.location.href = "people-read.html?firstName=" + firstNameBox.value + "&surname=" + surnameBox.value;
}