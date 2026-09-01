const express = require('express')
const multer = require('multer')
const crypto = require('crypto')

function md5(str) {
  const hash = crypto.createHash('md5');
  hash.update(str);
  return hash.digest('hex');
}

// 创建app对象
const app = express()

// 应用一个express编写第三方的中间件
const upload = multer({
  storage: multer.diskStorage({
    destination: function (req, file, callback) {
      callback(null, './uploads')
    },
    filename: function (req, file, callback) {
      const uniquePrefix =
        console.log('file : ', file)
      callback(null, file.fieldname + '-' + uniqueSuffix)
    }
  })
})

// 编写中间件
// 上传单文件: singer方法
app.post('/avatar', upload.single('avatar'), (req, res, next) => {
  console.log('req.file : ', req.file)
  res.end('文件上传成功~')
})

// 启动服务器
app.listen(9000, () => {
  console.log('express服务器启动成功~')
})
