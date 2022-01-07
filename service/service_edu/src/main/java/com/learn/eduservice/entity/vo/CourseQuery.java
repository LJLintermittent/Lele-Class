package com.learn.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 * date: 2021/3/6 21:16
 * Package: com.learn.eduservice.entity.vo
 *
 * @author 李佳乐
 * @version 1.0
 */
@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程名称,模糊查询")
    private String title;

    @ApiModelProperty(value = "课程状态 'Draft' 未发布，'Normal' 已发布")
    private String status;

    @ApiModelProperty(value = "查询开始时间", example = "2021-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2021-01-31 10:10:10")
    private String end;
}
