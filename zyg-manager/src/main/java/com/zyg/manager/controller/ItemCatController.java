package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.ItemCatEntity;
import com.zyg.manager.service.ItemCatService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 商品类目
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@RestController
@RequestMapping("manager/itemcat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:itemcat:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = itemCatService.queryPage(params);

        return R.ok().put("page", page);
    }

    // 查询itemCatlist
    @GetMapping("findItemCats")
    public R findAll2(){
        return R.ok().put("itemCatList",itemCatService.list());
    }

    // 查询模板ID 并放到redis中
    @GetMapping("findAll")
    private R findAll(){
        List<ItemCatEntity> itemCatEntities = itemCatService.findAll();
        return  R.ok().put("list",itemCatEntities);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:itemcat:info")
    public R info(@PathVariable("id") String id){
		ItemCatEntity itemCat = itemCatService.getById(id);

        return R.ok().put("itemCat", itemCat);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:itemcat:save")
    public R save(@RequestBody ItemCatEntity itemCat){
		itemCatService.save(itemCat);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:itemcat:update")
    public R update(@RequestBody ItemCatEntity itemCat){
		itemCatService.updateById(itemCat);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:itemcat:delete")
    public R delete(@RequestBody String[] ids){
		itemCatService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
