package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.PayLogEntity;
import com.zyg.manager.service.PayLogService;
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
@RequestMapping("manager/paylog")
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:paylog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = payLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{outTradeNo}")
    //@RequiresPermissions("manager:paylog:info")
    public R info(@PathVariable("outTradeNo") String outTradeNo){
		PayLogEntity payLog = payLogService.getById(outTradeNo);

        return R.ok().put("payLog", payLog);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:paylog:save")
    public R save(@RequestBody PayLogEntity payLog){
		payLogService.save(payLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:paylog:update")
    public R update(@RequestBody PayLogEntity payLog){
		payLogService.updateById(payLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:paylog:delete")
    public R delete(@RequestBody String[] outTradeNos){
		payLogService.removeByIds(Arrays.asList(outTradeNos));

        return R.ok();
    }

}
