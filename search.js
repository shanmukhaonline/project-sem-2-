export function linearSearch(arr,key){

for(let i=0;i<arr.length;i++){

if(arr[i].busNumber===key){

return arr[i]

}

}

return null

}



export function binarySearch(arr,key){

let left=0
let right=arr.length-1

while(left<=right){

let mid=Math.floor((left+right)/2)

if(arr[mid].busNumber===key){

return arr[mid]

}

else if(arr[mid].busNumber<key){

left=mid+1

}

else{

right=mid-1

}

}

return null

}