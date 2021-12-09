package com.zyg.manager.controller;

import org.springframework.ui.Model;
import com.zyg.manager.entity.ContentEntity;
import com.zyg.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@Controller
@RequestMapping

public class IndexController {

    @Autowired
    private ContentService contentService;
    //1. 默认页面
    @GetMapping({"/","/index.html"})
    public String index(Model model){
        //1. 轮播广告.查询广告
        List<ContentEntity> list = contentService.findAll();
        //2. 放到model中
        model.addAttribute("contentList",list);



        return "index";   //物理试图：/templates/index.html   因为你在resource 里面的配置文件里面配了
    }
}
