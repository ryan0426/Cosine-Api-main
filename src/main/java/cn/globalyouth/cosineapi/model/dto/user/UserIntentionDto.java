package cn.globalyouth.cosineapi.model.dto.user;

import lombok.Data;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 求职意向dto
 */
@Data
public class UserIntentionDto {

    private int id;

    private List<String> positions;

    private List<String> industries;

    private List<String> cities;

    private List<String> intentionCharacter;

    private int internLength;

    private String arrivalTime;

}
