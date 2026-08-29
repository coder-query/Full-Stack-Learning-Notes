package com.shaui.spring_security.utils;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 生成JSON Web令牌的工具类
 */

@Component
//@Data
public class JwtUtils {

    @Value("${jwt.tokenExpiration}")
    private long tokenExpiration;   //token过期时间

    @Value("${jwt.tokenSignKey}")
    private String tokenSignKey;    //加密密钥

//根据用户ID 和 用户名 生成token字符串

    public String createToken(Long userId, String username) {
        String token = Jwts.builder()
                .setSubject("AUTH-USER")//主题
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)) //过期时间
                .claim("userId", userId)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //从token字符串获取userid
    public Long getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) return null;
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);//用之前服务器写好的tokenSignKey进行验证解密
            Claims claims = claimsJws.getBody();// 得到有效载荷
            Integer userId = (Integer) claims.get("userId");
            return userId.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //从token字符串获取username
    public String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) return "";

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可，token一般由客户端放在请求头。
    }

//测试方法
    /**
     *  String token = JwtUtils.createToken(1L, "admin");
     * //"eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSCjAK0A0Ndg1S0lFKrShQsjI0MzY2sDQ3MTbQUSotTi3yTFGyMjKEsP0Sc1OBWp6unfB0f7NSLQDxzD8_QwAAAA.2eCJdsJXOYaWFmPTJc8gl1YHTRl9DAeEJprKZn4IgJP9Fzo5fLddOQn1Iv2C25qMpwHQkPIGukTQtskWsNrnhQ";
     *         System.out.println(token);
     *         System.out.println(JwtUtils.getUserId(token));
     *         System.out.println(JwtUtils.getUsername(token));
     */
}