package cn.globalyouth.cosineapi.model.bean.send;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author liuyufeng, Rujun Yan
 * tb_job 职位
 */
@Data
public class Job {

    private Integer id;

    private String title;

    private Integer minSalary;

    private Integer maxSalary;

    private String salary;

    private String location;

    private String education;

    private String nature;

    private Integer isEager;

    private String canOnline;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date deadline;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp addTime;

    private String description;

    private String demand;

    private String address;

    private Integer show;

    private Integer companyId;

    private Company company;

    private Integer viewCnt;

    private Integer sendCnt;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Job job = (Job) o;
        return Objects.equals(id, job.id) && Objects.equals(title, job.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
