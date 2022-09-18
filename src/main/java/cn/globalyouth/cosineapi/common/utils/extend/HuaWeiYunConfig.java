package cn.globalyouth.cosineapi.common.utils.extend;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:static/cosine/configuration/huaweiyun.properties")
@ConfigurationProperties(prefix = "huawei")
public class HuaWeiYunConfig {
    public static String ak;
    public static String sk;
    public static String endPoint;
    public static String url;
    public static String defaultBucketName;

    public void setDefaultBucketName(String defaultBucketName) {
        this.defaultBucketName = defaultBucketName;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
