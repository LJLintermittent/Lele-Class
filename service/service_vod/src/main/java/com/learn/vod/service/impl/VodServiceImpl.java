package com.learn.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.learn.servicebase.exceptionhandler.GuLiException;
import com.learn.vod.service.VodService;
import com.learn.vod.utils.ConstantVodUtils;
import com.learn.vod.utils.InitVodClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Description:
 * date: 2021/3/7 20:37
 * Package: com.learn.vod.service.impl
 *
 * @author 李佳乐
 * @version 1.0
 */
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideoAliyun(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID
                    , ConstantVodUtils.ACCESS_KEY_SECRET
                    , title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeAllVideo(List videoIdList) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");
            request.setVideoIds(videoIds);
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuLiException(20001, "删除视频失败");
        }
    }
}
