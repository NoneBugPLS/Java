package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.CitiesEntity;
import com.zyg.manager.service.CitiesService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 行政区域地州市信息表
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@RestController
@RequestMapping("manager/cities")
public class CitiesController {
    @Autowired
    private CitiesService citiesService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:cities:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = citiesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:cities:info")
    public R info(@PathVariable("id") Integer id){
		CitiesEntity cities = citiesService.getById(id);

        return R.ok().put("cities", cities);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:cities:save")
    public R save(@RequestBody CitiesEntity cities){
		citiesService.save(cities);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:cities:update")
    public R update(@RequestBody CitiesEntity cities){
		citiesService.updateById(cities);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:cities:delete")
    public R delete(@RequestBody Integer[] ids){
		citiesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
