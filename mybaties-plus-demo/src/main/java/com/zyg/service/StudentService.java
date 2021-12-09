package com.zyg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyg.entity.Student;
import com.zyg.entity.StudentVo;

import java.util.List;

/**
 * 接口名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
public interface StudentService {
    List<Student> findAll();

    Student findById(int sid);

    void add(Student student);

    void update(Student student);

    void delete(int sid);

    IPage<Student> findByPage(int page, int pageSize);

    IPage<Student> search(int page, int pageSize, StudentVo studentVo);
}
