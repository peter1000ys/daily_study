// push => 배열 맨 뒤에 추가
let arr1 = [1,2,3];
const newLength = arr1.push(4,5,6,7);

// pop => 배열 맨 뒤 원소 제거
let arr2 = [1,2,3];
const poppedItem = arr2.pop()

// shift => 배열의 맨 앞에 있는 원소 제거
let arr3 = [1,2,3];

let shiftedItem = arr3.shift();
console.log(arr3);

// unshift => 배열의 맨 앞에 새로운 원소를 추가
let arr4 = [1,2,3];
arr4.unshift(0);
console.log(arr4);
// shift 경우 pop이나 push 보다 시간이 더 많이 소요

// slice => 마치 가위처럼, 배열의 특정 범위를 잘라내서 새로운 배열로 반환
let arr5 = [1,2,3,4,5];
let sliced = arr5.slice(2);
console.log(sliced);
console.log(arr5)
console.log(arr5.slice(-2));

// concat => 두개의 서로 다른 배열을 이어 붙여서 새로운 배열을 반환

let arr6 = [1,2];
let arr7 = [3,4];
let concatedArr = arr6.concat(arr7);
console.log(concatedArr);

// 배열 순회와 탐색
// 1. forEach
// 모든 요소를 순회하면서 각각의 요소에 특정 동작을 수행시키는 메서드
let arrA = [1,2,3];

arr1.forEach(function (item, idx, arr) {
  console.log(idx, item*2);
})

let doubledArr = [];

arr1.forEach((item)=> {
  doubledArr.push(item*2);
});

console.log(doubledArr);

// includes
// 배열에 특정 요소가 있는지 확인하는 그런 메서드
let isIncluded = arr2.includes(10);
console.log(isIncluded);

// indexOf
// 특정 요소의 index를 찾아서 반환하는 메서드
let arrC = [1,2,3];
let index = arrC.indexOf(20);
console.log(index);
// 객체에서 찾을 때 못 찾는다. 왜냐면 얕은 비교로 검색하기 때문에 찾지를 못함.

// findIndex
// 모든 요소를 순히ㅗ하면서 콜백함수를 만족하는 그런
// 특정 요소의 인덱스를 반환하는 메서드
let objectArr = [
  {name : "ys"},
  {name : "tr"},
]

console.log(
  objectArr.findIndex(
    (item) => item.name ==="tr"
  )
);

// find
// 모든 요소를 순회하면서 콜백함수를 만족하는 요소를 찾음, 요소를 그대로 반환