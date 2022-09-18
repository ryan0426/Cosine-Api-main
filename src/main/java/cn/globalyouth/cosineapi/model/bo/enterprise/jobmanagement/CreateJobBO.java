package cn.globalyouth.cosineapi.model.bo.enterprise.jobmanagement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

@ApiModel(description = "创建职位接受参数的对象", value = "创建职位接受参数的对象")
public class CreateJobBO {

    @NotBlank(message = "职位名称不能为空")
    @ApiModelProperty(value = "职位名称",name = "name",required = true)
    private String name;

    @NotBlank(message = "职位描述不能为空")
    @ApiModelProperty(value = "职位描述",name = "description",required = true)
    private String description;

    @NotBlank(message = "任职要求不能为空")
    @ApiModelProperty(value = "任职要求",name = "requirement",required = true)
    private String requirement;

    @NotBlank(message = "工作城市不能为空")
    @ApiModelProperty(value = "工作城市",name = "location",required = true)
    private String location;

    @NotBlank(message = "详细工作地址不能为空")
    @ApiModelProperty(value = "详细工作地址",name = "address",required = true)
    private String address;

    @NotBlank(message = "学历要求不能为空")
    @ApiModelProperty(value = "学历要求",name = "degree",required = true)
    private String degree;


    @ApiModelProperty(value = "薪资待遇(最低)",name = "salary_to",required = false)
    private Integer salary_to;

    @ApiModelProperty(value = "薪资待遇(最高)",name = "salary_from",required = false)
    private Integer salary_from;

    @ApiModelProperty(value = "职位空缺数量",name = "job_number",required = true)
    @NotNull(message = "职位空缺数量不能为空")
    private Integer job_number;



    @ApiModelProperty(value = "投递备注",name = "job_number",required = false)
    private String website;

    @ApiModelProperty(value = "投递备注",name = "job_number",required = false)
    private String note;

    @NotNull(message = "发布职位状态不能为空")
    @ApiModelProperty(value = "发布职位状态",name = "job_number",required = true)
    @Min(value = 0,message = "职位状态不正确")
    @Max(value = 1,message = "职位状态不正确")
    private Integer ongoing;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getSalary_to() {
        return salary_to;
    }

    public void setSalary_to(Integer salary_to) {
        this.salary_to = salary_to;
    }

    public Integer getSalary_from() {
        return salary_from;
    }

    public void setSalary_from(Integer salary_from) {
        this.salary_from = salary_from;
    }

    public Integer getJob_number() {
        return job_number;
    }

    public void setJob_number(Integer job_number) {
        this.job_number = job_number;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getOngoing() {
        return ongoing;
    }

    public void setOngoing(Integer ongoing) {
        this.ongoing = ongoing;
    }
}
