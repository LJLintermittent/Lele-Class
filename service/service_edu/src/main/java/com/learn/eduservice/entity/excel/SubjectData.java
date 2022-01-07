package com.learn.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Description:
 * date: 2021/3/3 20:14
 * Package: com.learn.eduservice.entity.excel
 *
 * @author 李佳乐
 * @version 1.0
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
