package com.cqu.eduservice.entity.vo;

import lombok.Data;

/**
 * @author fubibo
 * @create 2021-07-07 下午5:02
 */


/**
 * 用于课程最终确认
 */
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示


}
