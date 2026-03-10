export function bubbleSort(arr){

let a=[...arr]

for(let i=0;i<a.length;i++){

for(let j=0;j<a.length-i-1;j++){

if(a[j].busNumber>a[j+1].busNumber){

[a[j],a[j+1]]=[a[j+1],a[j]]

}

}

}

return a

}



export function selectionSort(arr){

let a=[...arr]

for(let i=0;i<a.length;i++){

let min=i

for(let j=i+1;j<a.length;j++){

if(a[j].busNumber<a[min].busNumber){

min=j

}

}

[a[i],a[min]]=[a[min],a[i]]

}

return a

}



export function insertionSort(arr){

let a=[...arr]

for(let i=1;i<a.length;i++){

let key=a[i]

let j=i-1

while(j>=0 && a[j].busNumber>key.busNumber){

a[j+1]=a[j]

j--

}

a[j+1]=key

}

return a

}