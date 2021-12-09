package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.ItemEntity;
import com.zyg.manager.service.ItemService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 商品表
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@RestController
@RequestMapping("manager/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:item:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = itemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:item:info")
    public R info(@PathVariable("id") String id){
		ItemEntity item = itemService.getById(id);

        return R.ok().put("item", item);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:item:save")
    public R save(@RequestBody ItemEntity item){
		itemService.save(item);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:item:update")
    public R update(@RequestBody ItemEntity item){
		itemService.updateById(item);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:item:delete")
    public R delete(@RequestBody String[] ids){
		itemService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
