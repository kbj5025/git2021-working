// interface : 객체 구조의 형식
// interface 타입명 {
//   속성명 : 타입;
//   속성명 : 타입 ;
//  }
interface User {
  firstname: string;
  lastname?: string; // 속성명? , optional(필수값이아닌) 속성
}

// 타입명[]
// number[], string[], User[]
function printName(arr: User[]) {}

function printNames(obj: User) {
  console.log(obj.firstname + " " + obj.lastname);
}

const user: User = {
  firstname: "John",
  //lastname:"Smith",
};

const users: User[] = [
  { firstname: "John", lastname: "Smith" },
  { firstname: "Park", lastname: "Sam" },
];

printName(user);
printName(users);
