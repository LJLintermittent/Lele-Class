package com.learn.ucenter.controller;


import com.learn.commonutils.JwtUtils;
import com.learn.commonutils.R;
import com.learn.commonutils.ordervo.MemberOrder;
import com.learn.ucenter.entity.Member;
import com.learn.ucenter.entity.vo.RegisterVo;
import com.learn.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-09
 */
@Api(description = "注册登录用户模块")
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("login")
    public R login(@RequestBody Member member) {
        String token = memberService.login(member);
        return R.ok().data("token", token);
    }

    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Member member = memberService.getById(memberId);
        return R.ok().data("userInfo", member);
    }

    @PostMapping("getUserInfoOrder/{id}")
    public MemberOrder getUserInfoOrder(@PathVariable String id) {
        Member member = memberService.getById(id);
        MemberOrder memberOrder = new MemberOrder();
        BeanUtils.copyProperties(member, memberOrder);
        return memberOrder;
    }

    //查询某一天注册的人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        Integer count = memberService.countRegister(day);
        return R.ok().data("countRegister", count);
    }

}

