package cn.globalyouth.cosineapi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuyufeng
 * 日历事件类型
 */
@Getter
@AllArgsConstructor
public enum EventTypeEnum {
    /**
     * 事件
     */
    MOCK(1, "模拟面试"),
    NODE(2, "时间节点"),
    TEACH_IN(3, "宣讲会"),
    EXAM(4, "笔试"),
    INTERVIEW(5, "面试"),
    CUSTOM(0, "自定义");

    private int code;

    private String name;

    public static String getEventName(int code) {
        for (EventTypeEnum type : EventTypeEnum.values()) {
            if (code == type.getCode()) {
                return type.getName();
            }
        }
        return null;
    }
}
