package com.learn.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * Description:
 * date: 2021/3/3 19:54
 * Package: com.learn.demo.excel
 *
 * @author 李佳乐
 * @version 1.0
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {

    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println(demoData);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
