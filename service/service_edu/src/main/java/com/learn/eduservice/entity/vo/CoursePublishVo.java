package com.learn.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 * date: 2021/3/6 15:57
 * Package: com.learn.eduservice.entity.vo
 *
 * @author 李佳乐
 * @version 1.0
 */
@Data
@ApiModel(value = "课程发布信息")
public class CoursePublishVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示


}
