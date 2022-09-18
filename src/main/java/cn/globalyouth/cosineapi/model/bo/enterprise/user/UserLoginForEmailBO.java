package cn.globalyouth.cosineapi.model.bo.enterprise.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ApiModel(description = "企业邮箱登录接受参数的对象", value = "企业邮箱登录接受参数的对象")
public class UserLoginForEmailBO {

    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱",name = "email",required = true)
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码",name = "password",required = true)
    @Length(min = 8,max = 20,message = "密码长度最小为8位")
    private String password;

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
}
