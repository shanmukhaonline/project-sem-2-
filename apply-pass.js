const form=document.getElementById("passForm")

const busType=document.getElementById("busType")
const duration=document.getElementById("duration")
const fareDisplay=document.getElementById("fare")

function calculateFare(){

let base=0

if(busType.value==="ordinary"){
base=300
}

if(busType.value==="express"){
base=500
}

if(busType.value==="deluxe"){
base=800
}

const months=parseInt(duration.value)

const total=base*months

fareDisplay.innerText=total

}

busType.addEventListener("change",calculateFare)
duration.addEventListener("change",calculateFare)

calculateFare()

form.addEventListener("submit",function(e){

e.preventDefault()

const application={

name:document.getElementById("name").value,
email:document.getElementById("email").value,
phone:document.getElementById("phone").value,
studentId:document.getElementById("studentId").value,
passType:document.getElementById("passType").value,
busType:busType.value,
duration:duration.value,
fare:fareDisplay.innerText

}

let applications=

JSON.parse(localStorage.getItem("busPassApplications")) || []

applications.push(application)

localStorage.setItem(

"busPassApplications",
JSON.stringify(applications)

)

alert("Application Submitted Successfully!")

form.reset()

calculateFare()

})
