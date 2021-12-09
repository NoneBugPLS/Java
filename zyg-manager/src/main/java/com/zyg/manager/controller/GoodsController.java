package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.GoodsEntity;
import com.zyg.manager.service.GoodsService;
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
@RequestMapping("manager/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:goods:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:goods:info")
    public R info(@PathVariable("id") String id){
		GoodsEntity goods = goodsService.getById(id);

        return R.ok().put("goods", goods);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:goods:save")
    public R save(@RequestBody GoodsEntity goods){
		goodsService.save(goods);

        return R.ok();
    }

    //修改状态
    @PostMapping("updateStatus/{status}/{ids}")
    public R updateStatus(@PathVariable String status, @PathVariable String[] ids){
        goodsService.updateStatus(status,ids);
        return R.ok();
    }
    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:goods:update")
    public R update(@RequestBody GoodsEntity goods){
		goodsService.updateById(goods);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:goods:delete")
    public R delete(@RequestBody String[] ids){
		goodsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
