package cn.globalyouth.cosineapi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuyufeng
 * 性别
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {
    /**
     * 性别
     */
    MALE(1, "男"), FEMALE(2, "女");

    private int code;

    private String name;

    public static String getGenderName(int code) {
        for (GenderEnum item : GenderEnum.values()) {
            if (code == item.getCode()) {
                return item.getName();
            }
        }
        return "未知";
    }

}
