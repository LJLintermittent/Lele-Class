package com.learn.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.commonutils.JwtUtils;
import com.learn.commonutils.MD5;
import com.learn.servicebase.exceptionhandler.GuLiException;
import com.learn.ucenter.entity.Member;
import com.learn.ucenter.entity.vo.RegisterVo;
import com.learn.ucenter.mapper.MemberMapper;
import com.learn.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-09
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(Member member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuLiException(20001, "登陆失败");
        }
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Member mobileMember = baseMapper.selectOne(wrapper);
        if (mobileMember == null) {
            throw new GuLiException(20001, "登陆失败");
        }
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new GuLiException(20001, "登陆失败");
        }
        if (mobileMember.getIsDisabled()) {
            throw new GuLiException(20001, "登陆失败");
        }
        String token = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        String mobile = registerVo.getMobile();
        String code = registerVo.getCode();
        if (StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(code)) {
            throw new GuLiException(20001, "注册失败");
        }
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)) {
            throw new GuLiException(20001, "注册失败");
        }
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new GuLiException(20001, "此手机号已注册!");
        }
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("C:\\Users\\20127\\Desktop\\API文档\\lei.png");
        baseMapper.insert(member);

    }

    @Override
    public Member getMemberByOpenId(String openid) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        Member member = baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    public Integer countRegister(String day) {
        return baseMapper.countRegister(day);
    }
}
