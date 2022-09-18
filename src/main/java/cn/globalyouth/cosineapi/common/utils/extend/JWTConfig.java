package cn.globalyouth.cosineapi.common.utils.extend;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Getter
@PropertySource("classpath:static/cosine/configuration/jwtconfig.properties")
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {

    public static String authoritiesKey;

    public static String tokenHeader;

    public static Integer expiration;

    public static String secret;

    public void setAuthoritiesKey(String authoritiesKey) {
        this.authoritiesKey = authoritiesKey;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
