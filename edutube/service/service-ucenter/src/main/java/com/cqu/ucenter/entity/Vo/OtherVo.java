package com.cqu.ucenter.entity.Vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CGT
 * @create 2021-07-21 15:07
 */
@Data
@ApiModel()
public class OtherVo {
    private Integer sex;
    private String nickname;
    private Integer age;
}
