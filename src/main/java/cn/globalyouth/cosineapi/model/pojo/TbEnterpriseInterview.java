package cn.globalyouth.cosineapi.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.*;

@ApiModel(value="cn-globalyouth-cosineapi-model-pojo-TbEnterpriseInterview")
@Table(name = "tb_enterprise_interview")
public class TbEnterpriseInterview {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(value="ID")
    private String id;

    /**
     * 企业ID
     */
    @Column(name = "company_id")
    @ApiModelProperty(value="企业ID")
    private String companyId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    @ApiModelProperty(value="用户ID")
    private Integer userId;

    /**
     * 投递人姓名
     */
    @Column(name = "user_name")
    @ApiModelProperty(value="投递人姓名")
    private String userName;

    /**
     * 面试日期
     */
    @Column(name = "interview_date")
    @ApiModelProperty(value="面试日期")
    private Date interviewDate;

    /**
     * 面试时间
     */
    @Column(name = "interview_time")
    @ApiModelProperty(value="面试时间")
    private Date interviewTime;

    /**
     * 职位ID
     */
    @Column(name = "office_id")
    @ApiModelProperty(value="职位ID")
    private String officeId;

    /**
     * 职位名称
     */
    @Column(name = "office_title")
    @ApiModelProperty(value="职位名称")
    private String officeTitle;

    /**
     * 面试网址
     */
    @Column(name = "interview_url")
    @ApiModelProperty(value="面试网址")
    private String interviewUrl;

    /**
     * 1 进行中 0 已终止
     */
    @Column(name = "ongoing")
    @ApiModelProperty(value="1 进行中 0 已终止")
    private Integer ongoing;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新日期
     */
    @Column(name = "update_time")
    @ApiModelProperty(value="更新日期")
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
     * 获取企业ID
     *
     * @return company_id - 企业ID
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置企业ID
     *
     * @param companyId 企业ID
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取投递人姓名
     *
     * @return user_name - 投递人姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置投递人姓名
     *
     * @param userName 投递人姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取面试日期
     *
     * @return interview_date - 面试日期
     */
    public Date getInterviewDate() {
        return interviewDate;
    }

    /**
     * 设置面试日期
     *
     * @param interviewDate 面试日期
     */
    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    /**
     * 获取面试时间
     *
     * @return interview_time - 面试时间
     */
    public Date getInterviewTime() {
        return interviewTime;
    }

    /**
     * 设置面试时间
     *
     * @param interviewTime 面试时间
     */
    public void setInterviewTime(Date interviewTime) {
        this.interviewTime = interviewTime;
    }

    /**
     * 获取职位ID
     *
     * @return office_id - 职位ID
     */
    public String getOfficeId() {
        return officeId;
    }

    /**
     * 设置职位ID
     *
     * @param officeId 职位ID
     */
    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    /**
     * 获取职位名称
     *
     * @return office_title - 职位名称
     */
    public String getOfficeTitle() {
        return officeTitle;
    }

    /**
     * 设置职位名称
     *
     * @param officeTitle 职位名称
     */
    public void setOfficeTitle(String officeTitle) {
        this.officeTitle = officeTitle;
    }

    /**
     * 获取面试网址
     *
     * @return interview_url - 面试网址
     */
    public String getInterviewUrl() {
        return interviewUrl;
    }

    /**
     * 设置面试网址
     *
     * @param interviewUrl 面试网址
     */
    public void setInterviewUrl(String interviewUrl) {
        this.interviewUrl = interviewUrl;
    }

    public Integer getOngoing() {
        return ongoing;
    }

    public void setOngoing(Integer ongoing) {
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
     * 获取更新日期
     *
     * @return update_time - 更新日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新日期
     *
     * @param updateTime 更新日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}