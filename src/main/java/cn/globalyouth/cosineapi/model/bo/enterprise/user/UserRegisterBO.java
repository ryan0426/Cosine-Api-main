package cn.globalyouth.cosineapi.model.bo.enterprise.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.*;

@ApiModel(description = "企业用户注册接受参数的对象", value = "企业用户注册接受参数的对象")
public class UserRegisterBO {
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名",name = "username",required = true)
    @Length(min = 1,max = 20,message = "用户名长度不正确")
    private String username;

    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱",name = "email",required = true)
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码",name = "password",required = true)
    @Length(min = 8,max = 20,message = "密码长度最小为8位")
    private String password;



    @NotBlank(message = "企业名称不能为空")
    @ApiModelProperty(value = "企业名称",name = "name",required = true)
    private String name;

    @ApiModelProperty(value = "企业地址",name = "address",required = false)
    private String address;

    @ApiModelProperty(value = "所属行业",name = "industry",required = true)
    @NotBlank(message = "所属行业不能为空")
    private String industry;


    @ApiModelProperty(value = "联系人姓名",name = "contact",required = true)
    @NotBlank(message = "联系人姓名不能为空")
    private String contact;


    @ApiModelProperty(value = "办公电话",name = "call",required = false)
    private String call;


    @ApiModelProperty(value = "联系人手机",name = "phone",required = true)
    @NotBlank(message = "联系人手机不能为空")
    @Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$", message = "手机号格式不正确")
    private String phone;


    @ApiModelProperty(value = "企业标语",name = "companySlogan",required = false)
    private String companySlogan;

    @ApiModelProperty(value = "企业介绍",name = "companyDescription",required = false)
    private String companyDescription;


    @ApiModelProperty(value = "企业显示名称",name = "displayName",required = false)
    private String displayName;

    @ApiModelProperty(value = "企业性质",name = "companyNature",required = false)
    @NotBlank(message = "企业性质不能为空")
    private String companyNature;

    @ApiModelProperty(value = "企业福利",name = "companyBenefits",required = false)
    private String companyBenefits;

    @ApiModelProperty(value = "联系人邮箱",name = "contactEmail",required = false)
    private String contactEmail;

    @ApiModelProperty(value = "企业官网",name = "companyWebsite",required = false)
    private String companyWebsite;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getCompanySlogan() {
        return companySlogan;
    }

    public void setCompanySlogan(String companySlogan) {
        this.companySlogan = companySlogan;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }

    public String getCompanyBenefits() {
        return companyBenefits;
    }

    public void setCompanyBenefits(String companyBenefits) {
        this.companyBenefits = companyBenefits;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }
}
