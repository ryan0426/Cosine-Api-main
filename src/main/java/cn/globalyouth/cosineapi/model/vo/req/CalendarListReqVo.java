package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 日历查询
 */
@Data
public class CalendarListReqVo {

    private int userId;

    private String date;

    private List<Integer> types;

}
