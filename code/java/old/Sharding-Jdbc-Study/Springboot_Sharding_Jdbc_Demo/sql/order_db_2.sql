
-- 创建订单库order_db_2
CREATE DATABASE  IF NOT EXISTS `order_db_2` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

-- 使用order_db_2库
USE `order_db_2`;

-- 在order_db中创建t_order_1、t_order_2表
CREATE TABLE IF NOT EXISTS `t_order_1` (
    `order_id` bigint(20) NOT NULL COMMENT '订单id',
    `price` decimal(10, 2) NOT NULL COMMENT '订单价格',
    `user_id` bigint(20) NOT NULL COMMENT '下单用户id',
    `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态',
    PRIMARY KEY (`order_id`) USING BTREE
    ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `t_order_2` (
    `order_id` bigint(20) NOT NULL COMMENT '订单id',
    `price` decimal(10, 2) NOT NULL COMMENT '订单价格',
    `user_id` bigint(20) NOT NULL COMMENT '下单用户id',
    `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态',
    PRIMARY KEY (`order_id`) USING BTREE
    ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE IF NOT EXISTS  `t_dict` (
    `dict_id` bigint(20) NOT NULL COMMENT '字典id',
    `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型',
    `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
    `value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典值',
    PRIMARY KEY (`dict_id`) USING BTREE
    ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
