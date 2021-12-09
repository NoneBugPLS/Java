package com.zyg.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName
public class Student {

    @TableId(type = IdType.AUTO)        //tableid表示是主键，然后auto是自动增加
    private Integer sid;                //这里我们用integer而不是int是因为用它的包装类功能更强大！


    private String sname;
    private Integer age;
    private String sex;
    private String addr;
    private Integer cid;

    //自动填充
    @TableField(fill = FieldFill.INSERT)        //表示插入时自动填充数据
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)        //表示插入时自动填充数据
    private LocalDateTime updateTime;


    //逻辑删除                      这里默认0是没被删的  1 是被删会被过滤的
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)          //表示下面这个属性并没有出现在数据库中，只是我们这个项目自己定义的
    private String cname;

    public Student(String sname, Integer age, String sex, String addr, Integer cid) {
        this.sname = sname;
        this.age = age;
        this.sex = sex;
        this.addr = addr;
        this.cid = cid;
    }
}
