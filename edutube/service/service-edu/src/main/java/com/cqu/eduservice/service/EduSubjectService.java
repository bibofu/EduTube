package com.cqu.eduservice.service;

import com.cqu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqu.eduservice.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author fubibo
 * @since 2021-07-06
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> getSubjectInfo();

    void removeSubject(String id);
}
