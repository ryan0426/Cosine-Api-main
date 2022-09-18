package cn.globalyouth.cosineapi.model.bo.enterprise.jobmanagement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;


@ApiModel(description = "关闭职位接受参数的对象", value = "关闭职位接受参数的对象")
public class CloseJobBO {


    @NotBlank(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID",name = "id",required = true)
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
