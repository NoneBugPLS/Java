package com.zyg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVo {
    private String sname;
    private String addr;
    private Integer cid;
}
