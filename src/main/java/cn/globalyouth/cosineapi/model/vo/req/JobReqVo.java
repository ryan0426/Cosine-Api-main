package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 职位查询Vo
 */
@Data
public class JobReqVo {

    private List<String> locations;

    private List<String> industries;

    private List<String> jobNatures;

    private List<String> educations;

    private Integer salaries;

    private List<String> companyNatures;

    private String name;

    private boolean haiTou;

    private int page;
}
