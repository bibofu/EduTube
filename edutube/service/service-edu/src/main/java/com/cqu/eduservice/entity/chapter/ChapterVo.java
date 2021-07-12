package com.cqu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fubibo
 * @create 2021-07-07 下午2:54
 */

@Data
public class ChapterVo {

    private String id;
    private String title;

    private List<VideoVo> children=new ArrayList<>();
}
