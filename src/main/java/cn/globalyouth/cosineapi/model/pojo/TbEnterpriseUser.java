package cn.globalyouth.cosineapi.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.*;

@ApiModel(value="cn-globalyouth-cosineapi-model-pojo-TbEnterpriseUser")
@Table(name = "tb_enterprise_user")
public class TbEnterpriseUser {
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
    @Column(name = "enterprise_id")
    @ApiModelProperty(value="企业ID")
    private String enterpriseId;

    /**
     * 用户名
     */
    @Column(name = "username")
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 电子邮箱
     */
    @Column(name = "email")
    @ApiModelProperty(value="电子邮箱")
    private String email;

    /**
     * 密码
     */
    @Column(name = "`password`")
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 联系人姓名
     */
    @Column(name = "contact")
    @ApiModelProperty(value="联系人姓名")
    private String contact;

    /**
     * 办公电话
     */
    @Column(name = "`call`")
    @ApiModelProperty(value="办公电话")
    private String call;

    /**
     * 联系人手机
     */
    @Column(name = "phone")
    @ApiModelProperty(value="联系人手机")
    private String phone;

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
     * 获取企业ID
     *
     * @return enterprise_id - 企业ID
     */
    public String getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 设置企业ID
     *
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取电子邮箱
     *
     * @return email - 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮箱
     *
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取联系人姓名
     *
     * @return contact - 联系人姓名
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人姓名
     *
     * @param contact 联系人姓名
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取办公电话
     *
     * @return call - 办公电话
     */
    public String getCall() {
        return call;
    }

    /**
     * 设置办公电话
     *
     * @param call 办公电话
     */
    public void setCall(String call) {
        this.call = call;
    }

    /**
     * 获取联系人手机
     *
     * @return phone - 联系人手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系人手机
     *
     * @param phone 联系人手机
     */
    public void setPhone(String phone) {
        this.phone = phone;
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