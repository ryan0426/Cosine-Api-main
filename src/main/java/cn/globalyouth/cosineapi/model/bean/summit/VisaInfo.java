package cn.globalyouth.cosineapi.model.bean.summit;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_visa_info 峰会Visa卡预留信息
 */
@Data
public class VisaInfo {

    private Integer id;

    private String name;

    private String phone;

    private String location;

    private Timestamp addTime;
}
