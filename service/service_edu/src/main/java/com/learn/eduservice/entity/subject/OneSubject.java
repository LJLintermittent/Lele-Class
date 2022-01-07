package com.learn.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/3/4 15:30
 * Package: com.learn.eduservice.entity.subject
 *
 * @author 李佳乐
 * @version 1.0
 */
@Data
public class OneSubject {

    private String id;
    private String title;

    private List<TwoSubject> children = new ArrayList<>();
}
