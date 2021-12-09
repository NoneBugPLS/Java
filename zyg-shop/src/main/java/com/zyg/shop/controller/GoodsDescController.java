package com.zyg.shop.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.shop.entity.GoodsDescEntity;
import com.zyg.shop.service.GoodsDescService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-26 22:25:10
 */
@RestController
@RequestMapping("shop/goodsdesc")
public class GoodsDescController {
    @Autowired
    private GoodsDescService goodsDescService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("shop:goodsdesc:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsDescService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{goodsId}")
    //@RequiresPermissions("shop:goodsdesc:info")
    public R info(@PathVariable("goodsId") String goodsId){
		GoodsDescEntity goodsDesc = goodsDescService.getById(goodsId);

        return R.ok().put("goodsDesc", goodsDesc);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("shop:goodsdesc:save")
    public R save(@RequestBody GoodsDescEntity goodsDesc){
		goodsDescService.save(goodsDesc);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("shop:goodsdesc:update")
    public R update(@RequestBody GoodsDescEntity goodsDesc){
		goodsDescService.updateById(goodsDesc);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("shop:goodsdesc:delete")
    public R delete(@RequestBody String[] goodsIds){
		goodsDescService.removeByIds(Arrays.asList(goodsIds));

        return R.ok();
    }

}
