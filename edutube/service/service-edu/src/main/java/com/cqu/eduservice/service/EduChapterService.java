package com.cqu.eduservice.service;

import com.cqu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqu.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author fubibo
 * @since 2021-07-07
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoById(String courseId);

    boolean deleteChapter(String chapterId);

    boolean removeChapterByCourseId(String courseId);
}
