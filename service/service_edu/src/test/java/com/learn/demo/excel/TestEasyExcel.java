package com.learn.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/3/3 19:31
 * Package: com.learn.demo.excel
 *
 * @author 李佳乐
 * @version 1.0
 */
public class TestEasyExcel {

    @Test
    public void testWrite() {
        String fileName = "D:\\guli_excel\\write1.xlsx";
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getList());
    }

    @Test
    public void testRead() {
        String fileName = "D:\\guli_excel\\write1.xlsx";
        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getList() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("LJL" + i);
            list.add(demoData);
        }
        return list;
    }

}
