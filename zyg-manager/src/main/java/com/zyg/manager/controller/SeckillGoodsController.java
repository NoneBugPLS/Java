package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.SeckillGoodsEntity;
import com.zyg.manager.service.SeckillGoodsService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@RestController
@RequestMapping("manager/seckillgoods")
public class SeckillGoodsController {
    @Autowired
    private SeckillGoodsService seckillGoodsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:seckillgoods:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillGoodsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:seckillgoods:info")
    public R info(@PathVariable("id") String id){
		SeckillGoodsEntity seckillGoods = seckillGoodsService.getById(id);

        return R.ok().put("seckillGoods", seckillGoods);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:seckillgoods:save")
    public R save(@RequestBody SeckillGoodsEntity seckillGoods){
		seckillGoodsService.save(seckillGoods);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:seckillgoods:update")
    public R update(@RequestBody SeckillGoodsEntity seckillGoods){
		seckillGoodsService.updateById(seckillGoods);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:seckillgoods:delete")
    public R delete(@RequestBody String[] ids){
		seckillGoodsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
