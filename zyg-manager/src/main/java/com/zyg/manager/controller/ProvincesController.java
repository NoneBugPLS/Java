package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.ProvincesEntity;
import com.zyg.manager.service.ProvincesService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 省份信息表
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@RestController
@RequestMapping("manager/provinces")
public class ProvincesController {
    @Autowired
    private ProvincesService provincesService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:provinces:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = provincesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:provinces:info")
    public R info(@PathVariable("id") Integer id){
		ProvincesEntity provinces = provincesService.getById(id);

        return R.ok().put("provinces", provinces);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:provinces:save")
    public R save(@RequestBody ProvincesEntity provinces){
		provincesService.save(provinces);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:provinces:update")
    public R update(@RequestBody ProvincesEntity provinces){
		provincesService.updateById(provinces);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:provinces:delete")
    public R delete(@RequestBody Integer[] ids){
		provincesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
