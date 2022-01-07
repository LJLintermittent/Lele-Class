package com.learn.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.commonutils.R;
import com.learn.educms.entity.CrmBanner;
import com.learn.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-09
 */
@Api(description = "banner后台管理模块")
@RestController
@RequestMapping("/educms/banner/admin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "分页查询Banner")
    @GetMapping("pageBanner/{current}/{size}")
    public R pageBanner(@PathVariable long current,long size){
        Page<CrmBanner> page = new Page<>(current,size);
        bannerService.page(page,null);
        return R.ok().data("items",page.getRecords()).data("total",page.getTotal());
    }

    @ApiOperation(value = "增加Banner")
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return R.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }

}

