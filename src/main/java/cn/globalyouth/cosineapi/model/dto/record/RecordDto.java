package cn.globalyouth.cosineapi.model.dto.record;

import lombok.Data;

import java.util.List;

/**
 * @author liuyufeng
 * 个人履历库 简要展示
 */
@Data
public class RecordDto {

    private String profile;

    private Basic basic;

    private List<Work> workList;

    private List<Activity> activityList;

    private List<Education> educationList;

    private List<Award> awardList;

    private List<Other> otherList;

    @Data
    public static class Basic {

        private int id;

        private String name;

        private String gender;

        private String phone;

        private String email;

        private String location;

        private String other;
    }

    @Data
    public static class Work {

        private int id;

        private String company;

        private String position;

        private String date;
    }

    @Data
    public static class Activity {

        private int id;

        private String activity;

        private String role;

        private String date;
    }

    @Data
    public static class Education {

        private int id;

        private String school;

        private String education;

        private String date;
    }

    @Data
    public static class Award {

        private int id;

        private String award;

        private String date;
    }

    @Data
    public static class Other {

        private int id;

        private String skill;

        private String language;

        private String certificate;

        private String hobby;

        private String description;
    }
}
