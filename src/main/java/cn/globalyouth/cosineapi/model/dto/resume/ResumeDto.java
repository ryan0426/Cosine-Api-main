package cn.globalyouth.cosineapi.model.dto.resume;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 简历dto
 */
@Data
public class ResumeDto {

    private int id;

    private String title;

    private String addTime;

    private String key;

    private boolean isDefault;

    private String image;
}
