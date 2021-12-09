package com.zyg.shop.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.shop.entity.SpecificationEntity;
import com.zyg.shop.service.SpecificationService;
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
@RequestMapping("shop/specification")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("shop:specification:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = specificationService.queryPage(params);

        return R.ok().put("page", page);
    }


    //1. 通过typeId找到规格信息
    @GetMapping("findByTypeId/{typeId}")
    public R findByTypeId(@PathVariable String typeId){
        //1. 将找到的规格信息放到一个集合中
        List<Map> spec = specificationService.findByTypeId(typeId);
        //2. 返回
        return R.ok().put("specifications",spec);
    }
    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("shop:specification:info")
    public R info(@PathVariable("id") String id){
		SpecificationEntity specification = specificationService.getById(id);

        return R.ok().put("specification", specification);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("shop:specification:save")
    public R save(@RequestBody SpecificationEntity specification){
		specificationService.save(specification);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("shop:specification:update")
    public R update(@RequestBody SpecificationEntity specification){
		specificationService.updateById(specification);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("shop:specification:delete")
    public R delete(@RequestBody String[] ids){
		specificationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
