package com.learn.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Description:
 * date: 2021/3/7 20:36
 * Package: com.learn.vod.service
 *
 * @author 李佳乐
 * @version 1.0
 */
public interface VodService {

    String uploadVideoAliyun(MultipartFile file);

    void removeAllVideo(List videoIdList);

}
