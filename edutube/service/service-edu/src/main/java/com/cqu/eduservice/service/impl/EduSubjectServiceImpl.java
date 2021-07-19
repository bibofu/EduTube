package com.cqu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqu.eduservice.entity.EduCourse;
import com.cqu.eduservice.entity.EduSubject;
import com.cqu.eduservice.entity.excel.SubjectData;
import com.cqu.eduservice.listener.SubjectListener;
import com.cqu.eduservice.mapper.EduSubjectMapper;
import com.cqu.eduservice.service.EduChapterService;
import com.cqu.eduservice.service.EduCourseService;
import com.cqu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqu.eduservice.service.EduVideoService;
import com.cqu.eduservice.subject.OneSubject;
import com.cqu.eduservice.subject.TwoSubject;
import com.cqu.servicebase.exceptionhandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    EduCourseService courseService;
    @Autowired
    EduChapterService chapterService;
    @Autowired
    EduVideoService videoService;
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {

        try {

            InputStream stream = file.getInputStream();
            EasyExcel.read(stream, SubjectData.class,new SubjectListener(subjectService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getSubjectInfo() {
        //1 查询所有一级分类  parentid = 0
        QueryWrapper<EduSubject> queryOne=new QueryWrapper<>();
        queryOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(queryOne);

        //2 查询所有二级分类  parentid != 0
        QueryWrapper<EduSubject> queryTwo = new QueryWrapper<>();
        queryTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(queryTwo);


        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3 封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值，
        //封装到要求的list集合里面 List<OneSubject> finalSubjectList
        for (int i = 0; i < oneSubjectList.size(); i++) { //遍历oneSubjectList集合

            EduSubject eduSubject = oneSubjectList.get(i);

            OneSubject oneSubject = new OneSubject();

            BeanUtils.copyProperties(eduSubject,oneSubject);
            //多个OneSubject放到finalSubjectList里面
            finalSubjectList.add(oneSubject);

            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();

            for (int m = 0; m < twoSubjectList.size(); m++) {
                //获取每个二级分类
                EduSubject tSubject = twoSubjectList.get(m);

                if(tSubject.getParentId().equals(eduSubject.getId())) {

                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            //把一级下面所有二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);
        }
        return finalSubjectList;
    }

    @Override
    public void removeSubject(String id) {

        courseService.removeBySubjectId(id);

        int result = baseMapper.deleteById(id);
        if (result == 0) {
            throw new MyException(20001, "删除失败");
        }
    }
}
