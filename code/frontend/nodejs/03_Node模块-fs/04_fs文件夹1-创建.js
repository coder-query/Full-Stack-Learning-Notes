const fs = require('fs')

// 创建文件夹 directory
fs.mkdir('./why', (err) => {
  console.log(err)
})

fs.promises.mkdir("./zsh-dir").then(res => {
  console.log("文件夹创建成功！！", res);

}).catch(err => {
  console.log("文件夹创建失败！！", err);
})
