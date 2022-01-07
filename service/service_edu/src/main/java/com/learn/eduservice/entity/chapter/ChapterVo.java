package com.learn.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/3/4 21:45
 * Package: com.learn.eduservice.entity.chapter
 *
 * @author 李佳乐
 * @version 1.0
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoVo> children = new ArrayList<>();
}
