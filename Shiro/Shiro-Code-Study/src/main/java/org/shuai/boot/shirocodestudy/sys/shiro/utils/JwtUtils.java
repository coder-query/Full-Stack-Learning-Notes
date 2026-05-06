package org.shuai.boot.shirocodestudy.sys.shiro.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtils {

    // 密钥（至少32字节）
    private static final String SECRET_KEY = "my_secret_key_for_jwt_hmac256_algorithm";

    // Token 过期时间：12小时
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000L;

    /**
     * 1. 生成 Token
     */
    public static String generateToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("data", claims)
                .withExpiresAt(Instant.now().plusMillis(EXPIRE_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    /**
     * 2. 提取 Claims（不验证签名和过期时间）
     * 用于从 Token 中读取数据，不管是否过期或签名是否正确
     */
    public static Map<String, Object> extractClaims(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            Claim claim = decodedJWT.getClaim("data");
            return claim.asMap();
        } catch (JWTDecodeException e) {
            log.error("Token 格式错误: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 3. 验证 Token 是否合法且未过期
     * @return true: 合法有效, false: 非法或已过期
     */
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (TokenExpiredException e) {
            log.error("Token 已过期: {}", e.getMessage());
            return false;
        } catch (SignatureVerificationException e) {
            log.error("Token 签名验证失败: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Token 验证失败: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 4. 验证并返回 Claims（组合功能）
     * @return 验证通过返回数据，失败返回 null
     */
    public static Map<String, Object> verifyAndGetClaims(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("data").asMap();
        } catch (TokenExpiredException e) {
            log.error("Token 已过期");
            return null;
        } catch (SignatureVerificationException e) {
            log.error("Token 签名无效");
            return null;
        } catch (Exception e) {
            log.error("Token 验证失败: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 5. 检查 Token 是否过期
     */
    public static boolean isExpired(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            Date expiresAt = decodedJWT.getExpiresAt();
            return expiresAt != null && expiresAt.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws InterruptedException {
        // 生成 Token
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", 1001);
        payload.put("username", "zhangsan");
        String token = generateToken(payload);
        System.out.println("Token: " + token);

        // 提取 Claims（不验证）
        System.out.println("提取数据: " + extractClaims(token));

        // 验证 Token
        System.out.println("验证结果: " + verifyToken(token));

        // 验证并获取数据
        System.out.println("验证+获取: " + verifyAndGetClaims(token));

        // 检查是否过期
        System.out.println("是否过期: " + isExpired(token));
    }
}