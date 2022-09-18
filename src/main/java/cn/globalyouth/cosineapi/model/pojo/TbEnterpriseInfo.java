package cn.globalyouth.cosineapi.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.*;

@ApiModel(value="cn-globalyouth-cosineapi-model-pojo-TbEnterpriseInfo")
@Table(name = "tb_enterprise_info")
public class TbEnterpriseInfo {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(value="ID")
    private String id;

    /**
     * 企业名称
     */
    @Column(name = "`name`")
    @ApiModelProperty(value="企业名称")
    private String name;

    /**
     * 企业地址
     */
    @Column(name = "address")
    @ApiModelProperty(value="企业地址")
    private String address;

    /**
     * 所属行业
     */
    @Column(name = "industry")
    @ApiModelProperty(value="所属行业")
    private String industry;

    /**
     * 企业标语
     */
    @Column(name = "company_slogan")
    @ApiModelProperty(value="企业标语")
    private String companySlogan;

    /**
     * 企业介绍
     */
    @Column(name = "company_description")
    @ApiModelProperty(value="企业介绍")
    private String companyDescription;

    /**
     * 企业显示名称
     */
    @Column(name = "display_name")
    @ApiModelProperty(value="企业显示名称")
    private String displayName;

    /**
     * 企业性质
     */
    @Column(name = "company_nature")
    @ApiModelProperty(value="企业性质")
    private String companyNature;

    /**
     * 企业福利
     */
    @Column(name = "company_benefits")
    @ApiModelProperty(value="企业福利")
    private String companyBenefits;

    /**
     * 联系人邮箱
     */
    @Column(name = "contact_email")
    @ApiModelProperty(value="联系人邮箱")
    private String contactEmail;

    /**
     * 企业官网
     */
    @Column(name = "company_website")
    @ApiModelProperty(value="企业官网")
    private String companyWebsite;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value="修改时间")
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
     * 获取企业名称
     *
     * @return name - 企业名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置企业名称
     *
     * @param name 企业名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取企业地址
     *
     * @return address - 企业地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置企业地址
     *
     * @param address 企业地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取所属行业
     *
     * @return industry - 所属行业
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置所属行业
     *
     * @param industry 所属行业
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 获取企业标语
     *
     * @return company_slogan - 企业标语
     */
    public String getCompanySlogan() {
        return companySlogan;
    }

    /**
     * 设置企业标语
     *
     * @param companySlogan 企业标语
     */
    public void setCompanySlogan(String companySlogan) {
        this.companySlogan = companySlogan;
    }

    /**
     * 获取企业介绍
     *
     * @return company_description - 企业介绍
     */
    public String getCompanyDescription() {
        return companyDescription;
    }

    /**
     * 设置企业介绍
     *
     * @param companyDescription 企业介绍
     */
    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    /**
     * 获取企业显示名称
     *
     * @return display_name - 企业显示名称
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 设置企业显示名称
     *
     * @param displayName 企业显示名称
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 获取企业性质
     *
     * @return company_nature - 企业性质
     */
    public String getCompanyNature() {
        return companyNature;
    }

    /**
     * 设置企业性质
     *
     * @param companyNature 企业性质
     */
    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }

    /**
     * 获取企业福利
     *
     * @return company_benefits - 企业福利
     */
    public String getCompanyBenefits() {
        return companyBenefits;
    }

    /**
     * 设置企业福利
     *
     * @param companyBenefits 企业福利
     */
    public void setCompanyBenefits(String companyBenefits) {
        this.companyBenefits = companyBenefits;
    }

    /**
     * 获取联系人邮箱
     *
     * @return contact_email - 联系人邮箱
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * 设置联系人邮箱
     *
     * @param contactEmail 联系人邮箱
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * 获取企业官网
     *
     * @return company_website - 企业官网
     */
    public String getCompanyWebsite() {
        return companyWebsite;
    }

    /**
     * 设置企业官网
     *
     * @param companyWebsite 企业官网
     */
    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
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
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}