package cn.globalyouth.cosineapi.model.bo.enterprise.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "企业用户名登录接受参数的对象", value = "企业用户名登录接受参数的对象")
public class UserLoginForUsernameBO {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名",name = "username",required = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码",name = "password",required = true)
    @Length(min = 8,max = 20,message = "密码长度最小为8位")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
