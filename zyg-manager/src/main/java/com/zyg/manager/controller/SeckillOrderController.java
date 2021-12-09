package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.SeckillOrderEntity;
import com.zyg.manager.service.SeckillOrderService;
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
@RequestMapping("manager/seckillorder")
public class SeckillOrderController {
    @Autowired
    private SeckillOrderService seckillOrderService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:seckillorder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:seckillorder:info")
    public R info(@PathVariable("id") String id){
		SeckillOrderEntity seckillOrder = seckillOrderService.getById(id);

        return R.ok().put("seckillOrder", seckillOrder);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:seckillorder:save")
    public R save(@RequestBody SeckillOrderEntity seckillOrder){
		seckillOrderService.save(seckillOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:seckillorder:update")
    public R update(@RequestBody SeckillOrderEntity seckillOrder){
		seckillOrderService.updateById(seckillOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:seckillorder:delete")
    public R delete(@RequestBody String[] ids){
		seckillOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
