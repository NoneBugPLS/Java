package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.SellerEntity;
import com.zyg.manager.service.SellerService;
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
@RequestMapping("manager/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:seller:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sellerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{sellerId}")
    //@RequiresPermissions("manager:seller:info")
    public R info(@PathVariable("sellerId") String sellerId){
		SellerEntity seller = sellerService.getById(sellerId);

        return R.ok().put("seller", seller);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:seller:save")
    public R save(@RequestBody SellerEntity seller){
		sellerService.save(seller);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:seller:update")
    public R update(@RequestBody SellerEntity seller){
		sellerService.updateById(seller);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:seller:delete")
    public R delete(@RequestBody String[] sellerIds){
		sellerService.removeByIds(Arrays.asList(sellerIds));

        return R.ok();
    }

}
