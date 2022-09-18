package cn.globalyouth.cosineapi.model.bo.enterprise.jobmanagement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;

@ApiModel(description = "查询所有职位接受参数的对象", value = "查询所有职位接受参数的对象")
public class QueryAllBO {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名",name = "username",required = true)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
