const results = document.querySelector("#results");


const readAll = () => {
	
	// do empty / refresh
	
	fetch('vehicle/readAll')
	.then((response) => response.json())
	.then((json) => {
		console.log(json);
		let table = document.createElement("table");
		table.setAttribute("class", "table");
		
		let tableHeader = document.createElement("thead");
		let tableHeaderRow = document.createElement("tr");
		let tableReg = document.createElement("th");
		let tableMM = document.createElement("th");
		let tableLink = document.createElement("th");
		tableReg.setAttribute("scope", "col");
		tableMM.setAttribute("scope", "col");
		tableLink.setAttribute("scope", "col");
		
		let headerReg = document.createTextNode("Registration Number");
		let headerMM = document.createTextNode("Make & Model");
		let headerLink = document.createTextNode("Link");
		
		tableReg.appendChild(headerReg);
		tableMM.appendChild(headerMM);
		tableLink.appendChild(headerLink);
		
		tableHeaderRow.appendChild(tableReg);
		tableHeaderRow.appendChild(tableMM);
		tableHeaderRow.appendChild(tableLink);
		
		tableHeader.appendChild(tableHeaderRow);
		
		table.appendChild(tableHeader);
		
		let tableBody = document.createElement("tbody");
		
		
		for(let i = 0; i < json.length; i++) {
			let row = document.createElement("tr");
			let rowReg = document.createElement("td");
			let rowMM = document.createElement("td");
			let rowLink = document.createElement("td");
			
			
			console.log(json[i].id)
			let reg = document.createTextNode(json[i].registrationNumber);
			let mm = document.createTextNode(json[i].make + " " + json[i].model);
			let link = document.createElement("a");
			let view = document.createTextNode("View");
			link.setAttribute("href", "vehicles-read.html?id=" + json[i].id);
			link.appendChild(view);
			
			rowReg.appendChild(reg);
			rowMM.appendChild(mm);
			rowLink.appendChild(link);
			
			row.appendChild(rowReg);
			row.appendChild(rowMM);
			row.appendChild(rowLink);
			tableBody.appendChild(row);
		}
		table.appendChild(tableBody);
		results.appendChild(table);
	});
}

