package com.zyg.manager.vo;


import com.zyg.manager.entity.SpecificationEntity;
import com.zyg.manager.entity.SpecificationOptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:  用于组合规格及规格选项的类
 * @Date: Create in 2019/4/24 09:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specification implements Serializable {
    private SpecificationEntity spec;                       //规格
    private List<SpecificationOptionEntity> options;        //规格对应的规格选项
    /**
     * 前端反映：
     * {"spec":{"id":11,"specName":"aa"},"options":[{"id":1,"optionName":"aa-01","specId":11}]}
     *
     * {"spec":{"specName":"aa"},"options":[{"optionName":"aa","orders":"aa-1"},{"optionName":"bb","orders":"bb-1"}]}
     *
     * {"specName": "aa", "options": [ { "optionName": "a", "orders": "b" }, { "optionName": "d", "orders": "c" } ]}
     */
}
