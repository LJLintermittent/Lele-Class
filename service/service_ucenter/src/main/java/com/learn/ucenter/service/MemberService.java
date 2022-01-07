package com.learn.ucenter.service;

import com.learn.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-09
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    void register(RegisterVo registerVo);

    Member getMemberByOpenId(String openid);

    Integer countRegister(String day);
}
