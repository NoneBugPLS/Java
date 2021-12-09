package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.AddressEntity;
import com.zyg.manager.service.AddressService;
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
@RequestMapping("manager/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:address:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = addressService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:address:info")
    public R info(@PathVariable("id") String id){
		AddressEntity address = addressService.getById(id);

        return R.ok().put("address", address);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:address:save")
    public R save(@RequestBody AddressEntity address){
		addressService.save(address);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:address:update")
    public R update(@RequestBody AddressEntity address){
		addressService.updateById(address);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:address:delete")
    public R delete(@RequestBody String[] ids){
		addressService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
