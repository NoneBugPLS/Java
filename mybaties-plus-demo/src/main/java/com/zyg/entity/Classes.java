package com.zyg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

/**
 * 类名：班级类
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Classes {
    @TableId(type = IdType.AUTO)
    private Integer cid;
    private String cname;
}
