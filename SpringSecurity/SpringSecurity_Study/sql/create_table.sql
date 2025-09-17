-- 先手动建一个 字符集为utf-8mb4的数据库 security-demo
-- 然后再执行下面的sql脚本

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                         `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户账号',
                         `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
                         `enabled` tinyint(1) NOT NULL COMMENT '账号是否启用 -->  启用 1  废弃 0',
                         `role` tinyint(1) NOT NULL COMMENT '角色 -->  管理员 1  普通用户  0',
                         PRIMARY KEY (`id`) USING BTREE,
                         UNIQUE INDEX `user_username_uindex`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 1, 1);
INSERT INTO `user` VALUES (2, 'Helen', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 1, 0);
INSERT INTO `user` VALUES (3, 'Tom', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 1, 0);
INSERT INTO `user` VALUES (4, 'password', '{bcrypt}$2a$10$wgG5NRq2qsjNIOCUT9ZqSuv.qePiqnHVHg0qKoSpy5OKKpuslloeu', 1, 0);
INSERT INTO `user` VALUES (6, 'shuaihong', '{bcrypt}$2a$10$e6FAj/m2cAFmkDbRYzNmy.icpzXMzCpIJ.Vg2eFrlVmnYfqFZq.r.', 1, 0);
INSERT INTO `user` VALUES (7, 'zsh', '{bcrypt}$2a$10$cB2hH0qBFPN/pK.vKq4PXOdLG.MRe.OWtAp/18SiIHCRefjfCZWNG', 1, 0);
INSERT INTO `user` VALUES (8, 'zshynn', '{bcrypt}$2a$10$xwDlUV9HXemtdCh/H3BwKOZeeHRrSZFotUCo2gry9h8bs0xCY14oK', 1, 0);
INSERT INTO `user` VALUES (9, 'hhhddd', '{bcrypt}$2a$10$TAdiok3FIUDF3h36ckSJ7eZsuEyBDeubo/fDexQ5JryZDiXLq40dC', 1, 0);
INSERT INTO `user` VALUES (11, 'hhd', '{bcrypt}$2a$10$yESyQrQ.sL75XD8H4nN4c.NbjVdMEgITQvpFRQvEVjF3e3FL1DSYy', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
