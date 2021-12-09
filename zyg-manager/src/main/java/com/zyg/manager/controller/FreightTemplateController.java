package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.FreightTemplateEntity;
import com.zyg.manager.service.FreightTemplateService;
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
@RequestMapping("manager/freighttemplate")
public class FreightTemplateController {
    @Autowired
    private FreightTemplateService freightTemplateService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:freighttemplate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = freightTemplateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:freighttemplate:info")
    public R info(@PathVariable("id") String id){
		FreightTemplateEntity freightTemplate = freightTemplateService.getById(id);

        return R.ok().put("freightTemplate", freightTemplate);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:freighttemplate:save")
    public R save(@RequestBody FreightTemplateEntity freightTemplate){
		freightTemplateService.save(freightTemplate);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:freighttemplate:update")
    public R update(@RequestBody FreightTemplateEntity freightTemplate){
		freightTemplateService.updateById(freightTemplate);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:freighttemplate:delete")
    public R delete(@RequestBody String[] ids){
		freightTemplateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
