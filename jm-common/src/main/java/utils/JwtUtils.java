package utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;


public class JwtUtils {
//    设置密钥
    private static final String JWT_SECRET = "djh-qingke-crm-jwt-secret-key-2026";
//    设置令牌有效期
    private static final long JWT_EXPIRE = 1000 * 60 * 60 * 24 * 7;
//    生成令牌的方法
    public static String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRE))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();
    }
//    解析令牌的方法
    public static Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(JWT_SECRET.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
