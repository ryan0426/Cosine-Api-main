package cn.globalyouth.cosineapi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuyufeng
 * 语言枚举
 */
@Getter
@AllArgsConstructor
public enum LanguageEnum {

    /**
     * 语言
     */
    CHINESE(1, "中文"), ENGLISH(2, "英文");

    private int code;

    private String value;

    public static String getValue(int code) {
        for (LanguageEnum type : LanguageEnum.values()) {
            if (code == type.code) {
                return type.value;
            }
        }
        return "";
    }

    public static int getCode(String value) {
        for (LanguageEnum type : LanguageEnum.values()) {
            if (value.equals(type.value)) {
                return type.code;
            }
        }
        return -1;
    }
}
