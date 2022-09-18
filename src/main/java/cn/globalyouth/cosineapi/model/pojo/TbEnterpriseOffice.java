package cn.globalyouth.cosineapi.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.*;

@ApiModel(value="cn-globalyouth-cosineapi-model-pojo-TbEnterpriseOffice")
@Table(name = "tb_enterprise_office")
public class TbEnterpriseOffice {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(value="ID")
    private String id;

    /**
     * 企业ID 对应哪个企业发布的
     */
    @Column(name = "company_id")
    @ApiModelProperty(value="企业ID 对应哪个企业发布的")
    private String companyId;

    /**
     * 职位标题 职位名
     */
    @Column(name = "title")
    @ApiModelProperty(value="职位标题 职位名")
    private String title;

    /**
     * 工作性质
     */
    @Column(name = "nature")
    @ApiModelProperty(value="工作性质")
    private String nature;

    /**
     * 最低薪水
     */
    @Column(name = "min_salary")
    @ApiModelProperty(value="最低薪水")
    private Integer minSalary;

    /**
     * 最高薪水
     */
    @Column(name = "max_salary")
    @ApiModelProperty(value="最高薪水")
    private Integer maxSalary;

    /**
     * 薪水是否面议 1是 0否
     */
    @Column(name = "salary_negotiable")
    @ApiModelProperty(value="薪水是否面议 1是 0否")
    private Boolean salaryNegotiable;

    /**
     * 学历要求
     */
    @Column(name = "education")
    @ApiModelProperty(value="学历要求")
    private String education;

    /**
     * 职位描述
     */
    @Column(name = "description")
    @ApiModelProperty(value="职位描述")
    private String description;

    /**
     * 任职要求
     */
    @Column(name = "requirement")
    @ApiModelProperty(value="任职要求")
    private String requirement;

    /**
     * 投递要求
     */
    @Column(name = "demand")
    @ApiModelProperty(value="投递要求")
    private String demand;

    /**
     * 是否官网投递 1是 0否
     */
    @Column(name = "can_online")
    @ApiModelProperty(value="是否官网投递 1是 0否")
    private Boolean canOnline;

    /**
     * 官网网址
     */
    @Column(name = "online_url")
    @ApiModelProperty(value="官网网址")
    private String onlineUrl;

    /**
     * 工作城市
     */
    @Column(name = "`location`")
    @ApiModelProperty(value="工作城市")
    private String location;

    /**
     * 详细工作地址
     */
    @Column(name = "address")
    @ApiModelProperty(value="详细工作地址")
    private String address;

    /**
     * 截止日期
     */
    @Column(name = "deadline")
    @ApiModelProperty(value="截止日期")
    private Date deadline;

    /**
     * 职位空缺数量
     */
    @Column(name = "job_number")
    @ApiModelProperty(value="职位空缺数量")
    private Integer jobNumber;

    /**
     * 投递备注
     */
    @Column(name = "note")
    @ApiModelProperty(value="投递备注")
    private String note;

    /**
     * 发布职位状态 1 yes 0 no
     */
    @Column(name = "ongoing")
    @ApiModelProperty(value="发布职位状态 1 yes 0 no")
    private Boolean ongoing;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取企业ID 对应哪个企业发布的
     *
     * @return company_id - 企业ID 对应哪个企业发布的
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置企业ID 对应哪个企业发布的
     *
     * @param companyId 企业ID 对应哪个企业发布的
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取职位标题 职位名
     *
     * @return title - 职位标题 职位名
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置职位标题 职位名
     *
     * @param title 职位标题 职位名
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取工作性质
     *
     * @return nature - 工作性质
     */
    public String getNature() {
        return nature;
    }

    /**
     * 设置工作性质
     *
     * @param nature 工作性质
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * 获取最低薪水
     *
     * @return min_salary - 最低薪水
     */
    public Integer getMinSalary() {
        return minSalary;
    }

    /**
     * 设置最低薪水
     *
     * @param minSalary 最低薪水
     */
    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    /**
     * 获取最高薪水
     *
     * @return max_salary - 最高薪水
     */
    public Integer getMaxSalary() {
        return maxSalary;
    }

    /**
     * 设置最高薪水
     *
     * @param maxSalary 最高薪水
     */
    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    /**
     * 获取薪水是否面议 1是 0否
     *
     * @return salary_negotiable - 薪水是否面议 1是 0否
     */
    public Boolean getSalaryNegotiable() {
        return salaryNegotiable;
    }

    /**
     * 设置薪水是否面议 1是 0否
     *
     * @param salaryNegotiable 薪水是否面议 1是 0否
     */
    public void setSalaryNegotiable(Boolean salaryNegotiable) {
        this.salaryNegotiable = salaryNegotiable;
    }

    /**
     * 获取学历要求
     *
     * @return education - 学历要求
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历要求
     *
     * @param education 学历要求
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取职位描述
     *
     * @return description - 职位描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置职位描述
     *
     * @param description 职位描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取任职要求
     *
     * @return requirement - 任职要求
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * 设置任职要求
     *
     * @param requirement 任职要求
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    /**
     * 获取投递要求
     *
     * @return demand - 投递要求
     */
    public String getDemand() {
        return demand;
    }

    /**
     * 设置投递要求
     *
     * @param demand 投递要求
     */
    public void setDemand(String demand) {
        this.demand = demand;
    }

    /**
     * 获取是否官网投递 1是 0否
     *
     * @return can_online - 是否官网投递 1是 0否
     */
    public Boolean getCanOnline() {
        return canOnline;
    }

    /**
     * 设置是否官网投递 1是 0否
     *
     * @param canOnline 是否官网投递 1是 0否
     */
    public void setCanOnline(Boolean canOnline) {
        this.canOnline = canOnline;
    }

    /**
     * 获取官网网址
     *
     * @return online_url - 官网网址
     */
    public String getOnlineUrl() {
        return onlineUrl;
    }

    /**
     * 设置官网网址
     *
     * @param onlineUrl 官网网址
     */
    public void setOnlineUrl(String onlineUrl) {
        this.onlineUrl = onlineUrl;
    }

    /**
     * 获取工作城市
     *
     * @return location - 工作城市
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置工作城市
     *
     * @param location 工作城市
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取详细工作地址
     *
     * @return address - 详细工作地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细工作地址
     *
     * @param address 详细工作地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取截止日期
     *
     * @return deadline - 截止日期
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * 设置截止日期
     *
     * @param deadline 截止日期
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * 获取职位空缺数量
     *
     * @return job_number - 职位空缺数量
     */
    public Integer getJobNumber() {
        return jobNumber;
    }

    /**
     * 设置职位空缺数量
     *
     * @param jobNumber 职位空缺数量
     */
    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * 获取投递备注
     *
     * @return note - 投递备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置投递备注
     *
     * @param note 投递备注
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取发布职位状态 1 yes 0 no
     *
     * @return ongoing - 发布职位状态 1 yes 0 no
     */
    public Boolean getOngoing() {
        return ongoing;
    }

    /**
     * 设置发布职位状态 1 yes 0 no
     *
     * @param ongoing 发布职位状态 1 yes 0 no
     */
    public void setOngoing(Boolean ongoing) {
        this.ongoing = ongoing;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}