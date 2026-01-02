/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80043 (8.0.43)
 Source Host           : localhost:3306
 Source Schema         : rbac_demo

 Target Server Type    : MySQL
 Target Server Version : 80043 (8.0.43)
 File Encoding         : 65001

 Date: 02/01/2026 19:23:33
*/

create database if not exists rbac_demo;
use rbac_demo;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `permission_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限编码',
  `permission_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
  `permission_type` tinyint NOT NULL DEFAULT 1 COMMENT '权限类型：1-菜单，2-按钮，3-接口',
  `parent_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '父权限ID',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '前端组件',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限描述',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序号',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
  `is_external` tinyint NOT NULL DEFAULT 0 COMMENT '是否外部链接：0-否，1-是',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_permission_code`(`permission_code` ASC) USING BTREE,
  INDEX `idx_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `idx_permission_type`(`permission_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_sort_order`(`sort_order` ASC) USING BTREE,
  INDEX `idx_is_deleted`(`is_deleted` ASC) USING BTREE,
  INDEX `idx_is_deleted_deleted_at`(`is_deleted` ASC, `deleted_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 'system', '系统管理', 1, 0, '/system', 'Layout', 'settings', '系统管理菜单', 100, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (2, 'system:user', '用户管理', 1, 1, 'user', 'system/user/index', 'user', '用户管理', 101, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (3, 'system:user:query', '查询用户', 2, 2, NULL, NULL, NULL, '查询用户列表', 1, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (4, 'system:user:add', '新增用户', 2, 2, NULL, NULL, NULL, '新增用户', 2, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (5, 'system:user:edit', '编辑用户', 2, 2, NULL, NULL, NULL, '编辑用户信息', 3, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (6, 'system:user:delete', '删除用户', 2, 2, NULL, NULL, NULL, '删除用户', 4, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (7, 'system:role', '角色管理', 1, 1, 'role', 'system/role/index', 'team', '角色管理', 102, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (8, 'system:role:query', '查询角色', 2, 7, NULL, NULL, NULL, '查询角色列表', 1, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (9, 'system:role:add', '新增角色', 2, 7, NULL, NULL, NULL, '新增角色', 2, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (10, 'system:role:edit', '编辑角色', 2, 7, NULL, NULL, NULL, '编辑角色信息', 3, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (11, 'system:role:delete', '删除角色', 2, 7, NULL, NULL, NULL, '删除角色', 4, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (12, 'system:role:assign', '分配权限', 2, 7, NULL, NULL, NULL, '为角色分配权限', 5, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (13, 'system:permission', '权限管理', 1, 1, 'permission', 'system/permission/index', 'security-scan', '权限管理', 103, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (14, 'system:permission:query', '查询权限', 2, 14, NULL, NULL, NULL, '查询权限列表', 1, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (15, 'system:permission:add', '新增权限', 2, 14, NULL, NULL, NULL, '新增权限', 2, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (16, 'system:permission:edit', '编辑权限', 2, 14, NULL, NULL, NULL, '编辑权限信息', 3, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (17, 'system:permission:delete', '删除权限', 2, 14, NULL, NULL, NULL, '删除权限', 4, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (18, 'dashboard', '仪表板', 1, 0, '/dashboard', 'dashboard/index', 'dashboard', '系统仪表板', 1, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (19, 'dashboard:view', '查看仪表板', 2, 20, NULL, NULL, NULL, '查看仪表板数据', 1, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (20, 'profile', '个人中心', 1, 0, '/profile', 'profile/index', 'user', '个人中心', 90, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (21, 'profile:view', '查看资料', 2, 22, NULL, NULL, NULL, '查看个人资料', 1, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_permission` VALUES (22, 'profile:edit', '编辑资料', 2, 22, NULL, NULL, NULL, '编辑个人资料', 2, 1, 0, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色描述',
  `is_system` tinyint NOT NULL DEFAULT 0 COMMENT '是否为系统内置角色：0-否，1-是',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序号',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_code`(`role_code` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_sort_order`(`sort_order` ASC) USING BTREE,
  INDEX `idx_is_deleted`(`is_deleted` ASC) USING BTREE,
  INDEX `idx_is_deleted_deleted_at`(`is_deleted` ASC, `deleted_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '管理员', '系统管理员，拥有系统管理权限', 1, 1, 1, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_role` VALUES (2, 'user', '普通用户', '普通用户角色，拥有基础权限', 0, 2, 1, 0, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);

-- ----------------------------
-- Table structure for sys_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_relation`;
CREATE TABLE `sys_role_permission_relation`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  `role_id` bigint UNSIGNED NOT NULL COMMENT '角色ID',
  `permission_id` bigint UNSIGNED NOT NULL COMMENT '权限ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_permission`(`role_id` ASC, `permission_id` ASC) USING BTREE,
  INDEX `idx_role_id`(`role_id` ASC) USING BTREE,
  INDEX `idx_permission_id`(`permission_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission_relation
-- ----------------------------
INSERT INTO `sys_role_permission_relation` VALUES (1, 1, 1, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (2, 1, 2, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (3, 1, 7, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (4, 1, 13, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (5, 1, 18, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (6, 1, 20, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (7, 1, 3, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (8, 1, 4, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (9, 1, 5, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (10, 1, 6, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (11, 1, 8, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (12, 1, 9, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (13, 1, 10, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (14, 1, 11, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (15, 1, 12, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (16, 1, 14, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (17, 1, 15, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (18, 1, 16, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (19, 1, 17, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (20, 1, 19, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (21, 1, 21, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (22, 1, 22, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (32, 2, 20, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (33, 2, 21, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (34, 2, 22, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (35, 2, 23, '2026-01-02 19:17:14');
INSERT INTO `sys_role_permission_relation` VALUES (36, 2, 24, '2026-01-02 19:17:14');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码哈希',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像URL',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
  `last_login_at` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `uk_email`(`email` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_is_deleted`(`is_deleted` ASC) USING BTREE,
  INDEX `idx_is_deleted_deleted_at`(`is_deleted` ASC, `deleted_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iK6RYz4WvOQ7WlNpZLlJ3s6YzQaK', '系统管理员', '13800138001', NULL, 1, 0, NULL, NULL, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_user` VALUES (2, 'user1', 'user1@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iK6RYz4WvOQ7WlNpZLlJ3s6YzQaK', '测试用户1', '13800138002', NULL, 1, 0, NULL, NULL, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);
INSERT INTO `sys_user` VALUES (3, 'user2', 'user2@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iK6RYz4WvOQ7WlNpZLlJ3s6YzQaK', '测试用户2', '13800138003', NULL, 1, 0, NULL, NULL, '2026-01-02 19:17:14', '2026-01-02 19:17:14', NULL);

-- ----------------------------
-- Table structure for sys_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_relation`;
CREATE TABLE `sys_user_role_relation`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `role_id` bigint UNSIGNED NOT NULL COMMENT '角色ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_role`(`user_id` ASC, `role_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_role_id`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role_relation
-- ----------------------------
INSERT INTO `sys_user_role_relation` VALUES (1, 1, 1, '2026-01-02 19:17:14');
INSERT INTO `sys_user_role_relation` VALUES (2, 2, 2, '2026-01-02 19:17:14');
INSERT INTO `sys_user_role_relation` VALUES (3, 3, 2, '2026-01-02 19:17:14');

SET FOREIGN_KEY_CHECKS = 1;
