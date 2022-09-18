package cn.globalyouth.cosineapi.model.bean.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_user_intention 求职性质
 */
@Data
public class UserIntention {

    private Integer id;

    private Integer user;

    private String positions;

    private String industries;

    private String cities;

    private String intentionCharacter;

    private Integer internLength;

    @JsonFormat(pattern="yyyy-MM",timezone = "GMT+8")
    private Date arrivalTime;

    private Timestamp addTime;
}
