package com.zyg.shop.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.shop.entity.SellerEntity;
import com.zyg.shop.service.SellerService;
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
@RequestMapping("shop/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("shop:seller:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sellerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{sellerId}")
    //@RequiresPermissions("shop:seller:info")
    public R info(@PathVariable("sellerId") String sellerId){
		SellerEntity seller = sellerService.getById(sellerId);

        return R.ok().put("seller", seller);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("shop:seller:save")
    public R save(@RequestBody SellerEntity seller){
		sellerService.save(seller);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("shop:seller:update")
    public R update(@RequestBody SellerEntity seller){
		sellerService.updateById(seller);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("shop:seller:delete")
    public R delete(@RequestBody String[] sellerIds){
		sellerService.removeByIds(Arrays.asList(sellerIds));

        return R.ok();
    }

}
