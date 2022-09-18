package cn.globalyouth.cosineapi.model.dto;

import lombok.Data;

/**
 * @author liuyufeng
 * 宣讲会dto
 */
@Data
public class TeachInDto {

    private int id;

    private String title;

    private String image;

    private long timestamp;

    private String fullDate;

    private String date;

    private String time;

    private Initiator initiator;

    private boolean hot;

    private boolean vip;

    private String guest;

    private int people;

    private String link;

    private String content;

    @Data
    public static class Initiator {

        private int id;

        private String profile;

        private String name;

        private String introduction;
    }
}
