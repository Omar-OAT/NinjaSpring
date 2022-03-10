'use strict'


//incomplete due to lost time by ISP outages 


//attempted to iterate through my array so that I can insert into table rows
let getData = async () => {
const ninjas = []
fetch("http://localhost:8080/getAll/" )
  .then(response => response.json())
  .then(function (result) {
    console.log('Result', result)
    for (var i = 0; i < result.length; i++) {
      ninjas.push(result[i])
        
    }
    console.log('Ninjas', ninjas)
  })
  .catch(error => console.log('error', error));
}


const paragraphToSelect = document.querySelector("#example");


let showData = async () => {
let returnedData = await getData();
var table = document.getElementById("example")
//Adding additional clear to remove "no data shown about output"
paragraphToSelect.innerHTML = "";
//Ita through the json data
for (let d of returnedData) {
        let div = document.createElement("tr");
        //adding styling to each div 
        div.style = "margin:10px;"
        //matching the object syntax given in console.log(data)
        div.innerHTML = `age: ${d.age}, id: ${d.id}, Jutsu: ${d.jutsu}, name: ${d.name}, village: ${d.village}`;
        //appending our para -> div
        paragraphToSelect.append(div);
    }

}
//reset 
let clearData = () => paragraphToSelect.innerHTML = "No data shown";











