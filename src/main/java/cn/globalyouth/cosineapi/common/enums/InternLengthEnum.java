package cn.globalyouth.cosineapi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuyufeng
 * 实习时间
 */
@Getter
@AllArgsConstructor
public enum InternLengthEnum {
    /**
     * 实习时间
     */
    BELOW_THREE_MONTHS(1, "3个月以内"),
    THREE_TO_SIX_MONTHS(2, "3到6个月"),
    ABOVE_HALF_YEAR(3, "半年以上"),
    ABOVE_ONE_YEAR(4, "一年以上");

    private int code;

    private String value;

    public static String getValue(int code) {
        for (InternLengthEnum type : InternLengthEnum.values()) {
            if (code == type.getCode()) {
                return type.getValue();
            }
        }
        return "";
    }
}
