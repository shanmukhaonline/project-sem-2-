const routes = [

{busNumber:"21G",source:"Tambaram",destination:"Broadway",distance:32,trips:45},

{busNumber:"47A",source:"T Nagar",destination:"Avadi",distance:28,trips:38},

{busNumber:"29C",source:"Perambur",destination:"Sholinganallur",distance:35,trips:30},

{busNumber:"102",source:"Koyambedu",destination:"Adyar",distance:18,trips:50},

{busNumber:"5E",source:"Parrys",destination:"Besant Nagar",distance:14,trips:60},

{busNumber:"19B",source:"Tambaram",destination:"T Nagar",distance:25,trips:40},

{busNumber:"27H",source:"Guindy",destination:"Red Hills",distance:31,trips:28},

{busNumber:"12G",source:"Central",destination:"Velachery",distance:20,trips:42}

]

let routesData=[...routes]

const table=document.getElementById("routesTable")

function renderRoutes(data){

table.innerHTML=""

data.forEach(route=>{

const row=document.createElement("tr")

row.innerHTML=`

<td>${route.busNumber}</td>
<td>${route.source}</td>
<td>${route.destination}</td>
<td>${route.distance} km</td>
<td>${route.trips}</td>

`

table.appendChild(row)

})

}

renderRoutes(routesData)

function searchRoute(){

const key=document.getElementById("searchInput").value

const result=linearSearch(routesData,key)

if(result){

renderRoutes([result])

}else{

alert("Bus route not found")

}

}

function linearSearch(arr,key){

for(let i=0;i<arr.length;i++){

if(arr[i].busNumber===key){

return arr[i]

}

}

return null

}

function sortRoutes(type){

let sorted=[...routesData]

if(type==="bubble"){

sorted=bubbleSort(sorted)

}

if(type==="selection"){

sorted=selectionSort(sorted)

}

if(type==="insertion"){

sorted=insertionSort(sorted)

}

renderRoutes(sorted)

}

function bubbleSort(arr){

for(let i=0;i<arr.length;i++){

for(let j=0;j<arr.length-i-1;j++){

if(arr[j].busNumber>arr[j+1].busNumber){

[arr[j],arr[j+1]]=[arr[j+1],arr[j]]

}

}

}

return arr

}

function selectionSort(arr){

for(let i=0;i<arr.length;i++){

let min=i

for(let j=i+1;j<arr.length;j++){

if(arr[j].busNumber<arr[min].busNumber){

min=j

}

}

[arr[i],arr[min]]=[arr[min],arr[i]]

}

return arr

}

function insertionSort(arr){

for(let i=1;i<arr.length;i++){

let key=arr[i]
let j=i-1

while(j>=0 && arr[j].busNumber>key.busNumber){

arr[j+1]=arr[j]
j--

}

arr[j+1]=key

}

return arr

}
