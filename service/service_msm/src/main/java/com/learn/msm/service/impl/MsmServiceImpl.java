package com.learn.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.learn.msm.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * Description:
 * date: 2021/3/9 19:44
 * Package: com.learn.msm.service.impl
 *
 * @author 李佳乐
 * @version 1.0
 */
@Service
public class MsmServiceImpl implements MsmService {


    @Override
    public boolean send(HashMap<String, Object> map, String phone) {
        if (StringUtils.isEmpty(phone)) return false;
        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4G9GNwxYQ5mgDQWpDLiv"
                        , "8nNOjdtsiPH0fI5Kkr3XBooPr3WUN0");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "李哥说Java");
        request.putQueryParameter("TemplateCode", "SMS_195861786");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        try {
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
