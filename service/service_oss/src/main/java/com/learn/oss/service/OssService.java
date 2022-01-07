package com.learn.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 * date: 2021/3/3 15:26
 * Package: com.learn.oss.service
 *
 * @author 李佳乐
 * @version 1.0
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);

}
