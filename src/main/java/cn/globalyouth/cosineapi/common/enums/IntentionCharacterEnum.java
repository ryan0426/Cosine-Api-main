package cn.globalyouth.cosineapi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuyufeng, Rujun Yan
 * 求职性质
 */
@Getter
@AllArgsConstructor
public enum IntentionCharacterEnum {
    /**
     * 求职性质
     */
    ONLINE_INTERN(1, "线上实习"),
    SUMMER_INTERN(2, "暑期实习"),
    DAILY_INTERN(3, "日常实习"),
    RECRUITMENT(4, "春招秋招");

    private int code;

    private String value;

    public static String getValue(int code) {
        for(IntentionCharacterEnum type : IntentionCharacterEnum.values()) {
            if (type.getCode() == code) {
                return type.getValue();
            }
        }
        return "";
    }
}
