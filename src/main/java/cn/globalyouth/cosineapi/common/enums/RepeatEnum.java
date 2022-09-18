package cn.globalyouth.cosineapi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuyufeng, Rujun Yan
 * 重复日程枚举
 */
@Getter
@AllArgsConstructor
public enum RepeatEnum {
    /**
     * 重复
     */
    NONE(0), DAILY(1), WEEKLY(2), MONTHLY(3);

    private int code;
}
