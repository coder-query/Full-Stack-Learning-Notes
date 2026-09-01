
const http = require('http')

// 1.创建server服务器
const server = http.createServer((req, res) => {
  // ✅ 新标准 WHATWG URL API
  const baseUrl = `http://${req.headers.host}`
  const urlString = req.url
  if (!urlString) return res.end('请求路径不正确')
  const urlObj = new URL(urlString, baseUrl)
  // 获取 query 参数
  const offset = urlObj.searchParams.get('offset')
  const size = urlObj.searchParams.get('size')
  console.log(offset, size)
  res.end('hello world aaaa bbb')
})


// 2.开启server服务器
server.listen(8000, () => {
  console.log('服务器开启成功~')
})
