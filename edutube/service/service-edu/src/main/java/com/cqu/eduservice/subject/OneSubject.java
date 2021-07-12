package com.cqu.eduservice.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fubibo
 * @create 2021-07-07 上午10:31
 */

@Data
public class OneSubject {

    private String id;
    private String title;

    // 1个一级分类有多个二级分类
    private List<TwoSubject> children=new ArrayList<>();
}
