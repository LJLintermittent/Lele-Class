package com.learn.eduservice.controller;

import com.learn.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * Description:模拟登录模块 前端控制器
 * date: 2021/3/2 15:02
 * Package: com.learn.eduservice.controller
 *
 * @author 李佳乐
 * @version 1.0
 */
@Api(description = "模拟登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
