

// 传入字符串和数组，但是数组中可以包含其他类型的数据
// function getLength(args: string | any[]) {
//   return args.length
// }

// 传入对象，要求对象中必须包含 length 属性，这个属性是数字number类型
function getLength(args: { length: number }) {
  return args.length
}
getLength("aaaaa")
getLength(["abc", "cba", "nba", 123])

const info = {
  length: 100
}

getLength(info)

// getLength(123)

export { }
