package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.AreasEntity;
import com.zyg.manager.service.AreasService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 行政区域县区信息表
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@RestController
@RequestMapping("manager/areas")
public class AreasController {
    @Autowired
    private AreasService areasService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:areas:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = areasService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:areas:info")
    public R info(@PathVariable("id") Integer id){
		AreasEntity areas = areasService.getById(id);

        return R.ok().put("areas", areas);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:areas:save")
    public R save(@RequestBody AreasEntity areas){
		areasService.save(areas);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:areas:update")
    public R update(@RequestBody AreasEntity areas){
		areasService.updateById(areas);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:areas:delete")
    public R delete(@RequestBody Integer[] ids){
		areasService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
