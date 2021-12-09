package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.GoodsDescEntity;
import com.zyg.manager.service.GoodsDescService;
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
@RequestMapping("manager/goodsdesc")
public class GoodsDescController {
    @Autowired
    private GoodsDescService goodsDescService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:goodsdesc:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsDescService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{goodsId}")
    //@RequiresPermissions("manager:goodsdesc:info")
    public R info(@PathVariable("goodsId") String goodsId){
		GoodsDescEntity goodsDesc = goodsDescService.getById(goodsId);

        return R.ok().put("goodsDesc", goodsDesc);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:goodsdesc:save")
    public R save(@RequestBody GoodsDescEntity goodsDesc){
		goodsDescService.save(goodsDesc);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:goodsdesc:update")
    public R update(@RequestBody GoodsDescEntity goodsDesc){
		goodsDescService.updateById(goodsDesc);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:goodsdesc:delete")
    public R delete(@RequestBody String[] goodsIds){
		goodsDescService.removeByIds(Arrays.asList(goodsIds));

        return R.ok();
    }

}
