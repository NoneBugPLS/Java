package com.zyg.manager.service.impl;

import com.zyg.manager.entity.SpecificationOptionEntity;
import com.zyg.manager.service.SpecificationOptionService;
import com.zyg.manager.vo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.SpecificationDao;
import com.zyg.manager.entity.SpecificationEntity;
import com.zyg.manager.service.SpecificationService;


@Service("specificationService")
public class SpecificationServiceImpl extends ServiceImpl<SpecificationDao, SpecificationEntity> implements SpecificationService {

    @Autowired
    private SpecificationOptionService optionService;
    //列表规格
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //1. 定义查询包装器对象
        QueryWrapper<SpecificationEntity> QueryWrapper = new QueryWrapper<>();
        //2. 封装查询条件
        //2.1 先拿到查询参数
        Object key = params.get("key");
        if (key != null){
            QueryWrapper.like("id",key)
                        .or()
                        .like("spec_name",key);
        }
        IPage<SpecificationEntity> page = this.page(
                new Query<SpecificationEntity>().getPage(params),
                QueryWrapper
        );

        return new PageUtils(page);
    }

    //添加规格信息：
    @Override
    public void save(Specification specification) {
        //.1 添加规格
        this.save(specification.getSpec());
        //1.2 得到规格ID
        String id = specification.getSpec().getId();
        //2. 添加规格选项
        //2.1 给每个规格选项设置对应的规格ID
        for (SpecificationOptionEntity option : specification.getOptions()) {
            option.setSpecId(id);
            //2.2 添加规格选项信息
            optionService.save(option);
        }


    }


    //根据id查询规格及其属性
    @Override
    public Specification findById(String id) {
        //1.通过id拿到规格
        SpecificationEntity byId = this.getById(id);
        //2. 根据规格查询规格选项
        List<SpecificationOptionEntity>  options = optionService.list(new QueryWrapper<SpecificationOptionEntity>().eq("spec_id", id));
        //3. 把规格跟选项放进去
        Specification specification = new Specification();
        specification.setSpec(byId);
        specification.setOptions(options);
        return specification;
    }

    //修改
    @Override
    public void update(Specification specification) {
        //1. 修改规格
        this.updateById(specification.getSpec());
        //2. 修改规格选项，做法就是先把现在的删了再把新传过来的对象信息update
        //2.1 根据规格id 从多方表中删除规格选项
        optionService.remove(new QueryWrapper<SpecificationOptionEntity>().eq("spec_id",specification.getSpec().getId()));
        //2.2 再添加规格选项列表
        for (SpecificationOptionEntity option : specification.getOptions()) {
            //2.3 关联外键
            option.setSpecId(specification.getSpec().getId());
            //2.4  添加规格选项
            optionService.save(option);
        }
    }

    @Override
    public void delete(List<String> ids) {
        for (String id : ids) {
            //删除规格
            this.removeById(id);
            //删除规格选项
            optionService.remove(new QueryWrapper<SpecificationOptionEntity>().eq("spec_id",id));
        }
    }

}