package com.learn.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.eduservice.entity.EduSubject;
import com.learn.eduservice.entity.excel.SubjectData;
import com.learn.eduservice.entity.subject.OneSubject;
import com.learn.eduservice.entity.subject.TwoSubject;
import com.learn.eduservice.listener.SubjectExcelListener;
import com.learn.eduservice.mapper.EduSubjectMapper;
import com.learn.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-03
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        //查询所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        List<OneSubject> finalSubjectList = new ArrayList<>();

        //封装一级分类
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject OneEduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(OneEduSubject, oneSubject);
            finalSubjectList.add(oneSubject);
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int m = 0; m < twoSubjectList.size(); m++) {
                //获取每个二级分类
                EduSubject twoEduSubject = twoSubjectList.get(m);
                if (twoEduSubject.getParentId().equals(OneEduSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoEduSubject, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);
        }
        return finalSubjectList;
    }
}
