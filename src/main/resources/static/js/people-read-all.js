const results = document.querySelector("#results");


const readAll = () => {
	
	// do empty / refresh
	
	fetch('person/readAll')
	.then((response) => response.json())
	.then((json) => {
		console.log(json);
		let table = document.createElement("table");
		table.setAttribute("class", "table");
		
		let tableHeader = document.createElement("thead");
		let tableHeaderRow = document.createElement("tr");
		let tableName = document.createElement("th");
		let tableAddress = document.createElement("th");
		let tableLink = document.createElement("th");
		tableName.setAttribute("scope", "col");
		tableAddress.setAttribute("scope", "col");
		tableLink.setAttribute("scope", "col");
		
		let headerName = document.createTextNode("Name");
		let headerAddress = document.createTextNode("Address");
		let headerLink = document.createTextNode("Details");
		
		tableName.appendChild(headerName);
		tableAddress.appendChild(headerAddress);
		tableLink.appendChild(headerLink);
		
		tableHeaderRow.appendChild(tableName);
		tableHeaderRow.appendChild(tableAddress);
		tableHeaderRow.appendChild(tableLink);
		
		tableHeader.appendChild(tableHeaderRow);
		
		table.appendChild(tableHeader);
		
		let tableBody = document.createElement("tbody");
		
		
		for(let i = 0; i < json.length; i++) {
			let row = document.createElement("tr");
			let rowName = document.createElement("td");
			let rowAddress = document.createElement("td");
			let rowLink = document.createElement("td");
			
			
			console.log(json[i].id)
			let name = document.createTextNode(json[i].firstName + " " + json[i].surname);
			let address = document.createTextNode(json[i].address);
			let link = document.createElement("a");
			let view = document.createTextNode("View");
			link.setAttribute("href", "people-read.html?id=" + json[i].id);
			link.appendChild(view);
			
			rowName.appendChild(name);
			rowAddress.appendChild(address);
			rowLink.appendChild(link);
			
			row.appendChild(rowName);
			row.appendChild(rowAddress);
			row.appendChild(rowLink);
			tableBody.appendChild(row);
		}
		table.appendChild(tableBody);
		results.appendChild(table);
	});
}

