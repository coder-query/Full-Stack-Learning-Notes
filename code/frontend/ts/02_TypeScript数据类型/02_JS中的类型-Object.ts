type Person = {
  name: string
  age: number
  height?: number // 可选属性, 如果没有这个属性, 那么在访问的时候会报错
}

let info: Person = {
  name: "张三",
  age: 18,
  height: 1.88
}

console.log(info.name)
console.log(info.age)

export { }
