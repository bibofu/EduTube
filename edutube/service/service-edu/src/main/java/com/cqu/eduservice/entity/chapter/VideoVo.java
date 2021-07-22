package com.cqu.eduservice.entity.chapter;

import lombok.Data;

/**
 * @author fubibo
 * @create 2021-07-07 下午2:54
 */

@Data
public class VideoVo {

    private String id;
    private String title;

    private String videoSourceId;
    private Boolean isFree;
}
