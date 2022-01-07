package com.learn.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Test;

import java.util.List;

/**
 * Description:
 * date: 2021/3/7 19:35
 * Package: com.learn.vodtest
 *
 * @author 李佳乐
 * @version 1.0
 */
public class TestVod {
    @Test
    public void test() throws Exception {
        TestVod.getPlayAuth();
//        TestVod.upload();
    }

    public static void upload() throws Exception {
        String accessKeyId = "LTAI4G3LueT6J6CVnKKc7P6J";
        String accessKeySecret = "jniITpk97zO8VzTTlzm0GOcR1Ikuqt";
        String title = "上传测试视频";
        String fileName = "D:\\guli_excel\\6 - What If I Want to Move Faster.mp4";
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        request.setPartSize(2 * 1024 * 1024L);
        request.setTaskNum(1);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    public static void getPlayAuth() throws Exception {
        DefaultAcsClient client = InitObject.initVodClient("LTAI4G3LueT6J6CVnKKc7P6J"
                , "jniITpk97zO8VzTTlzm0GOcR1Ikuqt");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId("dc2e55c7488043a98ebc32a619b9ac8b");
        response = client.getAcsResponse(request);
        System.out.println("视频凭证 = " + response.getPlayAuth());
    }

    public static void getPlayUrl() throws Exception {
        DefaultAcsClient client = InitObject.initVodClient("LTAI4G3LueT6J6CVnKKc7P6J"
                , "jniITpk97zO8VzTTlzm0GOcR1Ikuqt");
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        request.setVideoId("cb2854730dbf442cb4200a9fae3fce03");
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.println("视频地址 = " + playInfo.getPlayURL() + "\n");
        }
        System.out.println("视频标题 = " + response.getVideoBase().getTitle() + "\n");
    }
}
