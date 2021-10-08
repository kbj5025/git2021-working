// 함수의 매개변수
// 변수명 : type
// function 함수명(매개변수1 : 타입, 매개변수2 : 타입):함수의반환타입{}

function sum(a: number, b: number): number {
  return a + b;
}

console.log(sum(1, 2));

// 첫번째 글자를 대문자로 변환
function capitalize(str: string): string {
  // IDE(Integrated development enviroment)에서 매개변수가 문자열인것을 인지함
  // 해당 형식에 맞는 함수나 속성을 자동완성하여 사용할수있게됨.
  return str[0].toUpperCase() + str.substr(1);
}
console.log(capitalize("javscript"));
