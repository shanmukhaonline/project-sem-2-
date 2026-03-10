const table=document.getElementById("historyTable")

let applications=

JSON.parse(localStorage.getItem("busPassApplications")) || []

function renderHistory(){

table.innerHTML=""

applications.forEach((app,index)=>{

const row=document.createElement("tr")

row.innerHTML=`

<td>${app.name}</td>
<td>${app.email}</td>
<td>${app.phone}</td>
<td>${app.passType}</td>
<td>${app.busType}</td>
<td>${app.duration} Month</td>
<td>₹${app.fare}</td>

<td>
<button class="btn btn-danger btn-sm"
onclick="deleteApplication(${index})">
Delete
</button>
</td>

`

table.appendChild(row)

})

}

renderHistory()

function deleteApplication(index){

applications.splice(index,1)

localStorage.setItem(

"busPassApplications",
JSON.stringify(applications)

)

renderHistory()

}

class Queue{

constructor(){
this.items=[]
}

enqueue(element){
this.items.push(element)
}

dequeue(){
if(this.items.length===0) return null
return this.items.shift()
}

isEmpty(){
return this.items.length===0
}

}

const queue=new Queue()

applications.forEach(app=>queue.enqueue(app))

function processNext(){

const status=document.getElementById("queueStatus")

const next=queue.dequeue()

if(next){

status.innerText=
"Processing application for "+next.name

}else{

status.innerText="No applications left in queue"

}

}
