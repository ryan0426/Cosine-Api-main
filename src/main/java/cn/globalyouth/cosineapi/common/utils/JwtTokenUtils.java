package cn.globalyouth.cosineapi.common.utils;

import cn.globalyouth.cosineapi.common.utils.extend.JWTConfig;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * jwt工具类
 */
public class JwtTokenUtils {



    /**
     * 生成token
     * @param userId  用户id
     * @param current  时间毫秒数，为空的话默认为当前时间毫秒数
     * @return
     */
    public static String generateToken(String userId, Long current) {
        if (current == null) {
            current = System.currentTimeMillis();
        }
        Algorithm algorithm = Algorithm.HMAC256(JWTConfig.secret);
        return JWT.create()
                .withClaim(JWTConfig.authoritiesKey, userId)
                .withClaim("current", current)
                .withExpiresAt(new Date(current + JWTConfig.expiration * 60 * 1000))
                .sign(algorithm);
    }

    /**
     * 从token中获取用户id
     * @param token
     * @return
     */
    public static String getUserId(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(JWTConfig.authoritiesKey).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 获取token
     * @return
     */
    public static String getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader(JWTConfig.tokenHeader);
        if (StringUtils.isBlank(token)) {
            return "";
        }
        return token;
    }


    /**
     * 验证token是否过期
     * @param token
     * @return
     */
    public static boolean isExpired(String token) {
        DecodedJWT jwt = null;
        try {
            jwt = JWT.decode(token);
        } catch (JWTDecodeException e) {
            return true;
        }
        Date expiration = jwt.getExpiresAt();
        return expiration.before(new Date());
    }


    /**
     * 验证token是否正确
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            //解密
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWTConfig.secret)).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从token中 获取存入的current时间
     * @param token
     * @return
     */
    public static Long getCurrent(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("current").asLong();
        }catch (Exception e){
            return null;
        }
    }
}