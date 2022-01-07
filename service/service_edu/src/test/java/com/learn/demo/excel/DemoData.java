package com.learn.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Description:
 * date: 2021/3/3 19:26
 * Package: com.learn.demo.excel
 *
 * @author 李佳乐
 * @version 1.0
 */
@Data
public class DemoData {

    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
