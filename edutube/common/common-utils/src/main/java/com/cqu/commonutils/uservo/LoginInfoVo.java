package com.cqu.commonutils.uservo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fubibo
 * @create 2021-07-15 下午3:21
 */

@Data
public class LoginInfoVo {


    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

}

