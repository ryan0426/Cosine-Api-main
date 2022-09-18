package cn.globalyouth.cosineapi.configuration;

import cn.globalyouth.cosineapi.interceptor.AuthenticationInterceptor;
import cn.globalyouth.cosineapi.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author liuyufeng
 * 拦截器配置
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private TokenInterceptor tokenInterceptor;

    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");

        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");
    }
}
