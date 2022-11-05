package com.lzxmusta.common.utils;

/**
 * @ClassName JwtHelper
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/5 18:04
 * @Version 1.0
 **/

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 生成JSON Web令牌的工具类
 */
public class JwtHelper {
//token过期时间
    private static long tokenExpiration = 24 * 60 * 60 * 1000;
    //加密密钥
    private static String tokenSignKey = "123456";
//通过用户id名称生成token字符串
    public static String createToken(String userId, String username) {
        String token = Jwts.builder()
                //分组
                .setSubject("AUTH-USER")
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
//                有效载荷
                .claim("userId", userId)
                .claim("username", username)
                //根据密钥编码加密
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                //字符串压缩变为一行显示
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }
//从token中获取用户id
    public static Long getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) return null;
//Jwts.parser()解析  setSigningKey(tokenSignKey)密钥
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userId = (Integer) claims.get("userId");
            return userId.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //从token中获取用户名称
    public static String getUsername(String token) {
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

    public static void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

//    public static void main(String[] args) {
//        String token = JwtHelper.createToken(1L, "admin");//"eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSCjAK0A0Ndg1S0lFKrShQsjI0MzY2sDQ3MTbQUSotTi3yTFGyMjKEsP0Sc1OBWp6unfB0f7NSLQDxzD8_QwAAAA.2eCJdsJXOYaWFmPTJc8gl1YHTRl9DAeEJprKZn4IgJP9Fzo5fLddOQn1Iv2C25qMpwHQkPIGukTQtskWsNrnhQ";//JwtHelper.createToken(7L, "admin");
//        System.out.println(token);
//        System.out.println(JwtHelper.getUserId(token));
//        System.out.println(JwtHelper.getUsername(token));
//    }
}
