package cn.globalyouth.cosineapi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuyufeng, Rujun Yan
 * 事件背景色枚举
 */
@Getter
@AllArgsConstructor
public enum StyleEnum {
    /**
     * 样式
     */
    MOCK(1, "#A97BCD"),
    NODE(2, "#EF4F4F"),
    TEACHIN(3, "#16C79A"),
    CUSTOM_RED(4, "#EE9595"),
    CUSTOM_YELLOW(5, "#FFD66B"),
    CUSTOM_BLUE(6, "#0EB0C9");

    private int code;

    private String color;

    public static String getColorByCode(int code) {
        for (StyleEnum type : StyleEnum.values()) {
            if (code == type.getCode()) {
                return type.getColor();
            }
        }
        return null;
    }
}
