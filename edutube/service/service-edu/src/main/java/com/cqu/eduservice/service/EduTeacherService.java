package com.cqu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author fubibo
 * @since 2021-07-05
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> teacherPage);

    List<EduTeacher> getHotTeacher();
}
