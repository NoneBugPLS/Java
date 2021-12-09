package com.zyg.shop.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.shop.entity.TypeTemplateEntity;
import com.zyg.shop.service.TypeTemplateService;
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
@RequestMapping("shop/typetemplate")
public class TypeTemplateController {
    @Autowired
    private TypeTemplateService typeTemplateService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("shop:typetemplate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = typeTemplateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("shop:typetemplate:info")
    public R info(@PathVariable("id") String id){
		TypeTemplateEntity typeTemplate = typeTemplateService.getById(id);

        return R.ok().put("typeTemplate", typeTemplate);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("shop:typetemplate:save")
    public R save(@RequestBody TypeTemplateEntity typeTemplate){
		typeTemplateService.save(typeTemplate);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("shop:typetemplate:update")
    public R update(@RequestBody TypeTemplateEntity typeTemplate){
		typeTemplateService.updateById(typeTemplate);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("shop:typetemplate:delete")
    public R delete(@RequestBody String[] ids){
		typeTemplateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
