package com.learn.msm.service;

import java.util.HashMap;

/**
 * Description:
 * date: 2021/3/9 19:44
 * Package: com.learn.msm.service
 *
 * @author 李佳乐
 * @version 1.0
 */
public interface MsmService {

    boolean send(HashMap<String, Object> map, String phone);

}
