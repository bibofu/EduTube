package com.cqu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CGT
 * @create 2021-07-13 14:15
 */

@ApiModel(value = "Course查询对象",description = "课程查询对象封装")
@Data
public class CourseQuery {
    @ApiModelProperty(value = "课程名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "课程价格")
    private float price;
}
