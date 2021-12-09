package com.zyg.shop.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.shop.entity.ItemCatEntity;
import com.zyg.shop.service.ItemCatService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 商品类目
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-26 22:25:10
 */
@RestController
@RequestMapping("shop/itemcat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("shop:itemcat:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = itemCatService.queryPage(params);

        return R.ok().put("page", page);
    }

    //查询所有分类
    @GetMapping("findAll")
    public R findAll(){
        //通过过滤拿到一级分类
        List<ItemCatEntity> collect = itemCatService.list()
                                                    .stream()
                                                    .filter(f -> f.getParentId().equals("0"))
                                                    .collect(Collectors.toList());
        return R.ok().put("categories1",collect);
    }

    //通过ParentID 查询此分类下的所有分类列表
   @GetMapping("findByParentId/{itemCatId}")
   public R findByParentId(@PathVariable String itemCatId){   //这里@PathVariable("这个值一样就可以省略（本来是要写itemCatId）") 参数名字跟路径传过来的变量名字一样就可以省略
        List<ItemCatEntity> itemCatEntities = itemCatService.findByParentId(itemCatId);
        return R.ok().put("list",itemCatEntities);
   }


    //查询所有分类 不分页
    @GetMapping("findItems")
    public R findItems(){
        List<ItemCatEntity> list = itemCatService.list();
        return R.ok().put("itemCatList",list);
    }
    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("shop:itemcat:info")
    public R info(@PathVariable("id") String id){
		ItemCatEntity itemCat = itemCatService.getById(id);

        return R.ok().put("itemCat", itemCat);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("shop:itemcat:save")
    public R save(@RequestBody ItemCatEntity itemCat){
		itemCatService.save(itemCat);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("shop:itemcat:update")
    public R update(@RequestBody ItemCatEntity itemCat){
		itemCatService.updateById(itemCat);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("shop:itemcat:delete")
    public R delete(@RequestBody String[] ids){
		itemCatService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
