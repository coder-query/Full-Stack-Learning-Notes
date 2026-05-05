package org.shuai.boot.shirocodestudy.shiro.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtils {
    // 1. 密钥 (Secret Key)：用于签名的字符串，生产环境建议使用更复杂的字符串并放在配置文件中
    // 注意：HMAC256 算法要求密钥具有一定的强度，建议长度至少为 32 字节
    private static final String SECRET_KEY = "my_secret_key_for_jwt_hmac256_algorithm";

    // 2. Token 过期时间 (单位：毫秒)，这里设置为 12 小时
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000L;

    /**
     * 生成 Token
     *
     * @param claims 自定义数据（例如 userId, username 等）
     * @return 加密后的 Token 字符串
     */
    public static String createToken(Map<String, Object> claims) {
        // 设置过期时间
        Instant expiresAt = Instant.now().plusMillis(EXPIRE_TIME);
        // 使用 Builder 模式构建 Token
        return JWT.create()
                .withClaim("data", claims) // 将自定义数据放入名为 "data" 的 Claim 中
                .withExpiresAt(expiresAt)  // 设置过期时间
                .sign(Algorithm.HMAC256(SECRET_KEY)); // 使用 HMAC256 算法和密钥进行签名
    }

    /**
     * 验证 Token 并解析数据
     *
     * @param token 前端传来的 Token 字符串
     * @return 解析后的数据 Map，如果验证失败则返回 null 或抛出异常
     */
    public static Map<String, Object> verifyToken(String token) {
        try {
            // 构建验证器
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();

            // 验证 Token，如果失败会抛出异常
            DecodedJWT jwt = verifier.verify(token);

            // 获取我们在生成时放入的 "data" 数据
            Claim claim = jwt.getClaim("data");
            return claim.asMap();

        } catch (Exception e) {
            // 这里可以根据具体异常类型（如 TokenExpiredException）做不同处理
            log.error("verifyToken error, e = ", e);
            return null; // 验证失败
        }
    }

    /**
     * 测试主方法
     */
    public static void main(String[] args) {
        // 1. 准备数据
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", 1001);
        payload.put("username", "zhangsan");
        payload.put("role", "admin");

        // 2. 生成 Token
        String token = JwtUtils.createToken(payload);
        System.out.println("生成的 Token: " + token);

        // 3. 验证 Token
        Map<String, Object> userInfo = JwtUtils.verifyToken(token);
        if (userInfo != null) {
            System.out.println("验证成功，解析数据: " + userInfo);
        } else {
            System.out.println("验证失败");
        }
    }
}
