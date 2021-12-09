package com.zyg.shop.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.shop.entity.ItemEntity;
import com.zyg.shop.service.ItemService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 商品表
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-26 22:25:10
 */
@RestController
@RequestMapping("shop/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("shop:item:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = itemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("shop:item:info")
    public R info(@PathVariable("id") String id){
		ItemEntity item = itemService.getById(id);

        return R.ok().put("item", item);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("shop:item:save")
    public R save(@RequestBody ItemEntity item){
		itemService.save(item);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("shop:item:update")
    public R update(@RequestBody ItemEntity item){
		itemService.updateById(item);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("shop:item:delete")
    public R delete(@RequestBody String[] ids){
		itemService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
