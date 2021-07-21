package com.cqu.ucenter.entity.Vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CGT
 * @create 2021-07-21 10:35
 */
@Data
@ApiModel(value="地址对象", description="地址对象")
public class UrlVo {
    @ApiModelProperty(value = "头像地址")
    private String url;

}
