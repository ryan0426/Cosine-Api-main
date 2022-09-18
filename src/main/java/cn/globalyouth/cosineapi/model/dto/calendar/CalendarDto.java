package cn.globalyouth.cosineapi.model.dto.calendar;

import cn.globalyouth.cosineapi.model.dto.MockInterviewDto;
import lombok.Data;

import java.util.List;

/**
 * @author liuyufeng
 * 日程dto
 */
@Data
public class CalendarDto {

    private int id;

    private String date;

    private String fromTime;

    private String toTime;

    private String fullFromDate;

    private int type;

    private String style;

    private int remind;

    private int zone;

    private String title;

    private String content;

    private String remark;

    private boolean node;

    private int repeat;

    private Event event;

    @Data
    public static class Event {

        private String type;

        private MockInterviewDto.Initiator initiator;

        private List<MockInterviewDto.Participant> participants;

        private int people;

        private String link;

        private String content;
    }

}
