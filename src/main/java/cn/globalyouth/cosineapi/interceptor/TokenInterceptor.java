package cn.globalyouth.cosineapi.interceptor;

import cn.globalyouth.cosineapi.common.annotations.NeedToken;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.common.utils.TokenHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author liuyufeng
 * token拦截器
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private TokenHelper tokenHelper;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 如果没被@NeedToken注解代表不需要登录验证，直接通过
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(NeedToken.class) == null) {
            return true;
        }
        // token验证
        String token = request.getHeader("token");
        // 验证通过
        if (tokenHelper.check(token)) {
            return true;
        }
        // 验证不通过
        log.info("token无效");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(MAPPER.writeValueAsString(ApiResponse.error(401, "token invalid")));
        return false;
    }
}
