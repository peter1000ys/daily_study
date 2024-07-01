// filter
// 기존 배열에서 조건을 만족하는 요소들만 필터링하여 새로운 배열로 반환

let arr1 = [
  {name : "홍길동", hobby : "도둑질"},
  {name : "김효빈", hobby : "테니스"},
  {name : "이정한", hobby : "독서"},
];

const tenniusPeople = arr1.filter((item) => (item.hobby === "테니스"));
console.log(tenniusPeople);

// map
// 배열의 모든 요소를 순회하며 각각 콜백함수를 실행하고 그 결과값들을 모아서 새로운 배열로 반환
let arr2 = [1,2,3];

const mapResult1 = arr2.map((item, idx, arr) => {
  return item * 2;
});
console.log(mapResult1);

let names = arr1.map((item) => item.name);
console.log(names)


// sort
// 배열을 사전순으로 정렬하는 메서드
let arr3 = [10, 3, 5];
arr3.sort((a, b) => {
  if (a>b) {
    // b가 a 앞에 오게 해라
    return 1;
  } else if (a < b) {
    // a가 b 앞에 와라
    return -1;
  } else {
    // 그대로
    return 0;
  }
});
console.log(arr3);


// toSorted
// 정렬된 새로운 배열을 반환하는 메서드, 원래 배열은 그대로 있음
let arr5 = ["a", "c", "b"];
const sorted = arr5.toSorted();
console.log(arr5);
console.log(sorted);

// join
// 배열의 모든 요소를 하나의 문자열로 합쳐서 반환하는 그런 메서드
let arr6 = ["hi"," im "," American "];
console.log(arr6.join(""));
