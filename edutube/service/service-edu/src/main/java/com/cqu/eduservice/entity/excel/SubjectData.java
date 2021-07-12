package com.cqu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author fubibo
 * @create 2021-07-06 下午3:20
 */

@Data
public class SubjectData {


    @ExcelProperty(index = 0)
    private String onesubjectName;

    @ExcelProperty(index = 1)
    private String twosubjectName;



}
