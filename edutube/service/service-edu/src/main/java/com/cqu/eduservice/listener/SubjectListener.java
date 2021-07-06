package com.cqu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqu.eduservice.entity.EduSubject;
import com.cqu.eduservice.entity.excel.SubjectData;
import com.cqu.eduservice.service.EduSubjectService;
import com.cqu.servicebase.exceptionhandler.MyException;


/**
 * @author fubibo
 * @create 2021-07-06 下午3:27
 */
public class SubjectListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService subjectService;


    public SubjectListener() {

    }
    public SubjectListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        if (subjectData==null){
            throw new MyException(20001,"输入文件为空");
        }

        EduSubject oneSubject = this.existOneSubject(subjectService, subjectData.getOnesubjectName());
        if (oneSubject==null){//表里没有这个一级分类
            oneSubject=new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOnesubjectName());

            subjectService.save(oneSubject);

        }

        String pid=oneSubject.getId();

        EduSubject twoSubject = this.existTwoSubject(subjectService, subjectData.getTwosubjectName(), pid);
        if (twoSubject==null){
            twoSubject = new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwosubjectName());//二级分类名称
            subjectService.save(twoSubject);

        }


    }

    //判断一级分类添加，不能重复加
    public EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();

        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");

        EduSubject one = subjectService.getOne(wrapper);

        return one;
    }

    //判断二级分类添加，不能重复
    public EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){

        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();

        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);

        EduSubject two = subjectService.getOne(wrapper);

        return two;





    }




    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
