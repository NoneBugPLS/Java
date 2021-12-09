package com.zyg.shop.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.shop.entity.SpecificationOptionEntity;
import com.zyg.shop.service.SpecificationOptionService;
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
@RequestMapping("shop/specificationoption")
public class SpecificationOptionController {
    @Autowired
    private SpecificationOptionService specificationOptionService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("shop:specificationoption:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = specificationOptionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("shop:specificationoption:info")
    public R info(@PathVariable("id") String id){
		SpecificationOptionEntity specificationOption = specificationOptionService.getById(id);

        return R.ok().put("specificationOption", specificationOption);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("shop:specificationoption:save")
    public R save(@RequestBody SpecificationOptionEntity specificationOption){
		specificationOptionService.save(specificationOption);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("shop:specificationoption:update")
    public R update(@RequestBody SpecificationOptionEntity specificationOption){
		specificationOptionService.updateById(specificationOption);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("shop:specificationoption:delete")
    public R delete(@RequestBody String[] ids){
		specificationOptionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
