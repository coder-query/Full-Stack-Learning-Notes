/**
 * 全局 Axios 配置
 * 所有页面统一引入此文件即可使用 axios
 * 用法: <script src="/static/js/axios-config.js"></script>
 */
const axiosInstance = axios.create({
    baseURL: 'http://127.0.0.1:8066',  // 统一基础地址
    timeout: 10000,                     // 10秒超时
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
});

// 请求拦截器 - 显示加载层
let loadIndex;
axiosInstance.interceptors.request.use(
    function(config) {
        // 将 data 对象自动序列化为 form-urlencoded 格式
        if (config.data && typeof config.data === 'object' && !(config.data instanceof FormData)) {
            var params = new URLSearchParams();
            Object.keys(config.data).forEach(function(key) {
                var value = config.data[key];
                if (value !== undefined && value !== null) {
                    params.append(key, value);
                }
            });
            config.data = params;
        }
        // 排除不需要 loading 的请求（如导出、上传等）
        if (config.loading !== false) {
            loadIndex = layui.layer.load(1, {
                shade: [0.3, '#000'],
                content: '<div style="padding:10px 30px;color:#31bdec;font-weight:bold;">请求中...</div>'
            });
        }
        return config;
    },
    function(error) {
        return Promise.reject(error);
    }
);

// 响应拦截器 - 关闭加载层 + 统一处理
axiosInstance.interceptors.response.use(
    function(response) {
        if (loadIndex !== undefined && response.config.loading !== false) {
            layui.layer.close(loadIndex);
            loadIndex = undefined;
        }
        return response.data; // 只返回 data 部分
    },
    function(error) {
        if (loadIndex !== undefined) {
            layui.layer.close(loadIndex);
            loadIndex = undefined;
        }
        // 统一错误提示
        let msg = '网络异常，请稍后重试';
        if (error.response) {
            switch (error.response.status) {
                case 401:
                    msg = '未登录或登录已过期';
                    break;
                case 403:
                    msg = '没有操作权限';
                    break;
                case 500:
                    msg = error.response.data?.message || '服务器内部错误';
                    break;
                default:
                    msg = '请求失败(' + error.response.status + ')';
            }
        } else if (error.code === 'ECONNABORTED') {
            msg = '请求超时，请稍后重试';
        }
        layui.layer.msg(msg, { icon: 2, time: 2000 });
        return Promise.reject(error);
    }
);
