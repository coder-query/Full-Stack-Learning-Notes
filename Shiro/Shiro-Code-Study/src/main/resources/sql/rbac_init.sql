-- ============================================================
-- RBAC 权限模型建表脚本 (5张表)
-- 数据库: shiro (对应 application.properties 中配置)
-- 字符集: utf8mb4
-- 表名前缀: sys_
-- ============================================================

CREATE DATABASE IF NOT EXISTS `shiro` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `shiro`;

-- -----------------------------------------------------------
-- 1. sys_user 用户表
-- 对应实体: org.shuai.boot.shirocodestudy.sys.model.entity.SysUser
-- 密码加密: SHA256 + salt(123456@~realm~salt) 迭代10次
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    VARCHAR(64)  NOT NULL COMMENT '用户名',
    `password`    VARCHAR(128) NOT NULL COMMENT '密码(SHA256加密)',
    `salt`        VARCHAR(128) DEFAULT '123456@~realm~salt' COMMENT '密码盐值',
    `status`      VARCHAR(16)  DEFAULT '1' COMMENT '状态: 1-正常 0-禁用',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- -----------------------------------------------------------
-- 2. sys_role 角色表
-- 对应 JwtRealm 中硬编码的角色: 超级管理员、商家 等
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name`   VARCHAR(64)  NOT NULL COMMENT '角色名称',
    `role_code`   VARCHAR(64)  NOT NULL COMMENT '角色编码(如: admin, merchant)',
    `description` VARCHAR(256) DEFAULT NULL COMMENT '角色描述',
    `status`      VARCHAR(16)  DEFAULT '1' COMMENT '状态: 1-正常 0-禁用',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- -----------------------------------------------------------
-- 3. sys_menu 菜单/权限表
-- 对应 JwtRealm 中硬编码的权限: sys:user:add, sys:user:delete 等
-- permission 格式: 模块:资源:操作 (如 sys:user:add)
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '菜单/权限ID',
    `parent_id`   BIGINT       DEFAULT 0 COMMENT '父级ID(0为顶级)',
    `menu_name`   VARCHAR(64)  NOT NULL COMMENT '菜单/权限名称',
    `menu_type`   VARCHAR(16)  NOT NULL COMMENT '类型: 1-目录 2-菜单 3-按钮/权限',
    `permission`  VARCHAR(128) DEFAULT NULL COMMENT '权限标识(如: sys:user:add)',
    `path`        VARCHAR(256) DEFAULT NULL COMMENT '路由路径',
    `icon`        VARCHAR(128) DEFAULT NULL COMMENT '菜单图标',
    `sort_order`  INT          DEFAULT 0 COMMENT '排序号',
    `status`      VARCHAR(16)  DEFAULT '1' COMMENT '状态: 1-正常 0-禁用',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单/权限表';

-- -----------------------------------------------------------
-- 4. sys_user_role 用户-角色关联表
-- 多对多中间表: 一个用户可有多个角色, 一个角色可分配给多个用户
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
    `id`      BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `role_id` BIGINT NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_role` (`user_id`, `role_id`),
    KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户-角色关联表';

-- -----------------------------------------------------------
-- 5. sys_role_menu 角色-菜单关联表
-- 多对多中间表: 一个角色可拥有多个菜单权限, 一个菜单可属于多个角色
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
    `id`       BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_id`  BIGINT NOT NULL COMMENT '角色ID',
    `menu_id`  BIGINT NOT NULL COMMENT '菜单/权限ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_menu` (`role_id`, `menu_id`),
    KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色-菜单关联表';


-- ============================================================
-- 初始化数据 (与现有 JwtRealm 硬编码逻辑对齐)
-- ============================================================

-- ------------------- 初始化用户 -------------------
-- 密码均为 123456, 通过 ShiroPwdUtils.sha256("admin") 加密
-- 即 SHA256("123456", salt="123456@~realm~salt", iterations=10)
INSERT INTO `sys_user` (`id`, `username`, `password`, `salt`, `status`) VALUES
(1, 'admin',  'fb6d6a86368e7dc6973a976a055a91194180b2b7e000207cd88d87158dc831c4', '123456@~realm~salt', '1'),
(2, 'shuai',  'fb6d6a86368e7dc6973a976a055a91194180b2b7e000207cd88d87158dc831c4', '123456@~realm~salt', '1'),
(3, 'merchant','fb6d6a86368e7dc6973a976a055a91194180b2b7e000207cd88d87158dc831c4','123456@~realm~salt', '1');

-- ------------------- 初始化角色 -------------------
INSERT INTO `sys_role` (`id`, `role_name`, `role_code`, `description`) VALUES
(1, '超级管理员', 'admin',    '拥有系统所有权限'),
(2, '商家',       'merchant', '商家用户，拥有用户查看和编辑权限'),
(3, '运营',       'operator', '运营人员，拥有用户查看权限');

-- ------------------- 初始化菜单/权限 -------------------
-- 一级目录
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `permission`, `sort_order`) VALUES
(1,  0, '系统管理', '1', NULL, 1);

-- 二级菜单
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `permission`, `sort_order`) VALUES
(2,  1, '用户管理', '2', NULL, 1);

-- 三级按钮/权限 (对应 JwtRealm 中的 sys:user:* 权限标识)
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `permission`, `sort_order`) VALUES
(3,  2, '用户新增', '3', 'sys:user:add',    1),
(4,  2, '用户删除', '3', 'sys:user:delete',  2),
(5,  2, '用户修改', '3', 'sys:user:update',  3),
(6,  2, '用户查看', '3', 'sys:user:select',  4);

-- ------------------- 初始化用户-角色关联 -------------------
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
(1, 1),   -- admin -> 超级管理员
(2, 1),   -- shuai -> 超级管理员
(3, 2);   -- merchant -> 商家

-- ------------------- 初始化角色-菜单关联 -------------------
-- 超级管理员: 拥有所有权限
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6);

-- 商家: 用户查看 + 用户修改
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(2, 1), (2, 2), (2, 5), (2, 6);

-- 运营: 仅用户查看
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(3, 1), (3, 2), (3, 6);
