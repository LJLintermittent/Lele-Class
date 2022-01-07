package com.learn.educms.controller;


import com.learn.commonutils.R;
import com.learn.educms.entity.CrmBanner;
import com.learn.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-09
 */
@Api(description = "banner前台管理模块")
@RestController
@RequestMapping("/educms/banner/front")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @Cacheable(key = "'selectIndexList'",value = "banner")
    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        int count = bannerService.count(null);
        return R.ok().data("list", list).data("total",count);
    }


}

