package com.learn.ucenter.mapper;

import com.learn.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-03-09
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer countRegister(String day);

}
