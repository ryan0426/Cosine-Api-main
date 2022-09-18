package cn.globalyouth.cosineapi.interceptor;

import cn.globalyouth.cosineapi.common.annotations.UserLoginToken;
import cn.globalyouth.cosineapi.common.enums.ResponseEnumState;
import cn.globalyouth.cosineapi.common.exception.GraceException;
import cn.globalyouth.cosineapi.common.utils.JwtTokenUtils;
import cn.globalyouth.cosineapi.dao.mapper.TbEnterpriseUserMapper;
import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseUser;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private TbEnterpriseUserMapper userMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 从 http 请求头中取出 token
        String token = request.getHeader("token");

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken != null) {
                //如果token为空 则响应
                if (StringUtils.isBlank(token)) {
                    GraceException.display(ResponseEnumState.USER_AUTHENTICATION_FAILED);
                }
                //验证token是否过期

                boolean expired = JwtTokenUtils.isExpired(token);
                if(expired){
                    GraceException.display(ResponseEnumState.USER_AUTHENTICATION_FAILED);
                }
                //验证token
                boolean verify = JwtTokenUtils.verify(token);
                if(!verify){
                    GraceException.display(ResponseEnumState.USER_AUTHENTICATION_FAILED);
                }
                // 获取 token 中的 user id
                String userId = null;
                try {
                    userId = JwtTokenUtils.getUserId(token);
                } catch (JWTDecodeException e) {
                    GraceException.display(ResponseEnumState.USER_AUTHENTICATION_FAILED);
                }

                if (StringUtils.isBlank(userId)) {
                    GraceException.display(ResponseEnumState.USER_DOES_NOT_EXIST);
                }
                TbEnterpriseUser user = userMapper.selectByPrimaryKey(userId);
                if (user == null) {
                    GraceException.display(ResponseEnumState.USER_DOES_NOT_EXIST);
                }
                return true;
            }

        }

        return true;
    }

}
