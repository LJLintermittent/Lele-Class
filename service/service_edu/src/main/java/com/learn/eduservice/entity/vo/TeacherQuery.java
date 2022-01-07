package com.learn.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 * date: 2021/2/26 18:19
 * Package: com.learn.eduservice.entity.vo
 *
 * @author 李佳乐
 * @version 1.0
 */
@Data
public class TeacherQuery {
    @ApiModelProperty(value = "教室名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1 高级讲师 2 首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2021-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2021-01-31 10:10:10")
    private String end;


}
