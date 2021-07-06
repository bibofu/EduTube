package com.cqu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.cqu.eduservice.entity.EduSubject;
import com.cqu.eduservice.entity.excel.SubjectData;
import com.cqu.eduservice.listener.SubjectListener;
import com.cqu.eduservice.mapper.EduSubjectMapper;
import com.cqu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author fubibo
 * @since 2021-07-06
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {

        try {

            InputStream stream = file.getInputStream();
            EasyExcel.read(stream, SubjectData.class,new SubjectListener(subjectService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
