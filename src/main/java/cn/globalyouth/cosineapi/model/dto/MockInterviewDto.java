package cn.globalyouth.cosineapi.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 模拟面试
 */
@Data
public class MockInterviewDto {

    private int id;

    private String title;

    private long timestamp;

    private String date;

    private String time;

    private Initiator initiator;

    private List<Participant> participants;

    private boolean hot;

    private int contain;

    private int remaining;

    private String link;

    private String content;

    @Data
    public static class Participant {

        private int id;

        private String profile;

    }

    @Data
    public static class Initiator {

        private int id;

        private String profile;

        private String name;

        private String introduction;

    }

}
