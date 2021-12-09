package com.zyg.search.controller;


import com.zyg.search.entity.ItemVo;
import com.zyg.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@Controller
public class searchController {
    @Autowired
    private ItemSearchService searchService;

    //1. 映射页面
    @GetMapping({"/","/search.html","/search"})
    public String index(Model model, ItemVo Vo){   //vo： 用于查询参数的对象    model 是后端查询到结果后，返回给前段的对象
        //2. 通过前端传入的参数查询数据
        Map<String,Object> resultMap = searchService.search(Vo);
        //3. 把查询结果放到model中
        //3.1 把查询结果放到model
        model.addAttribute("resultMap",resultMap);
        //3.2 把原始数据放到model；
        model.addAttribute("vo",Vo);
        return "search";
    }
}
