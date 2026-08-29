/**
 * 书籍相关接口
 * 用法: <script src="/static/api/book.js"></script>
 * 调用: BookApi.page(1, 10).then(...)
 */
var BookApi = {

    /**
     * 分页查询书籍列表
     * @param {Number} pageNo - 页码
     * @param {Number} pageSize - 每页条数
     * @returns {Promise}
     */
    page: function(pageNo, pageSize) {
        return axiosInstance({
            method: 'post',
            url: '/book/page',
            data: {
                pageNo: pageNo,
                pageSize: pageSize
            }
        });
    },

    /**
     * 删除书籍
     * @param {Number|String} id - 书籍ID
     * @returns {Promise}
     */
    del: function(id) {
        return axiosInstance({
            method: 'post',
            url: '/book/del',
            data: {
                id: id
            }
        });
    },

    /**
     * 新增书籍
     * @param {Object} data - 书籍数据
     * @param {String} data.bookName - 书籍名称
     * @param {String} data.authorName - 作者
     * @param {Number} data.price - 价格
     * @param {Number} data.categoryId - 分类ID
     * @param {String} data.bookUrl - 书籍链接
     * @param {String} data.bookAddress - 书籍地址
     * @returns {Promise}
     */
    add: function(data) {
        return axiosInstance({
            method: 'post',
            url: '/book/add',
            data: data
        });
    },

    /**
     * 编辑书籍
     * @param {Object} data - 书籍数据（含bookId）
     * @param {Number|String} data.bookId - 书籍ID
     * @param {String} data.bookName - 书籍名称
     * @param {String} data.authorName - 作者
     * @param {Number} data.price - 价格
     * @param {Number} data.categoryId - 分类ID
     * @param {String} data.bookUrl - 书籍链接
     * @param {String} data.bookAddress - 书籍地址
     * @returns {Promise}
     */
    update: function(data) {
        return axiosInstance({
            method: 'post',
            url: '/book/update',
            data: data
        });
    },

    /**
     * 根据ID查询书籍详情
     * @param {Number|String} id - 书籍ID
     * @returns {Promise}
     */
    detail: function(id) {
        return axiosInstance({
            method: 'get',
            url: '/book/detail',
            params: {
                id: id
            }
        });
    }
};
