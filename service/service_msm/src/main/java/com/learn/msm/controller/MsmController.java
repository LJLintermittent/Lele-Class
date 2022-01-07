package com.learn.msm.controller;

import com.learn.commonutils.R;
import com.learn.msm.service.MsmService;
import com.learn.msm.utils.RandomUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * date: 2021/3/9 19:43
 * Package: com.learn.msm.controller
 *
 * @author 李佳乐
 * @version 1.0
 */
@Api(description = "短信服务模块")
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("sendMsm/{phone}")
    public R sendMsm(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return R.error().message("您在一分钟内已发过短信，请稍后再发");
        }
        code = RandomUtil.getSixBitRandom();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        boolean isSend = msmService.send(map, phone);
        if (isSend) {
            redisTemplate.opsForValue().set(phone, code, 1, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("发送短信失败");
        }
    }

}
