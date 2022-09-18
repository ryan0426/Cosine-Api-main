package cn.globalyouth.cosineapi.common.utils;

import cn.globalyouth.cosineapi.dao.user.UserDao;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author liuyufeng
 * token验证
 */
@Component
public class TokenHelper {

    @Resource
    private UserDao userDao;

    private static final List<String> ACTIVITIES = Collections.singletonList("summit");


    public boolean check(String token) {
        if (!StringUtils.notEmpty(token)) {
            return false;
        }
        if (!token.contains("_")) {
            return false;
        }
        String[] parts = token.split("_");
        // 不足三部分
        if (parts.length < 3) {
            return false;
        }
        // 用户id
        int id = Integer.parseInt(parts[0]);
        if (id == -1) {
            // 活动名
            String activity = parts[1];
            // 活动不存在
            if (!ACTIVITIES.contains(activity)) {
                return false;
            }
            long time = Long.parseLong(parts[2]);
            // 超过1分钟
            if (System.currentTimeMillis() - time > 60 * 1000) {
                return false;
            }
            return true;
        }
        return true;
    }
}
