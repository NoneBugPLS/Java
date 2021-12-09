package com.zyg.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyg.entity.Student;
import com.zyg.entity.StudentVo;
import com.zyg.mapper.ClassesMapper;
import com.zyg.mapper.StudentMapper;
import com.zyg.service.ClassesService;
import com.zyg.service.StudentService;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassesMapper classesMapper;
    //列表学生
    @Override
    public List<Student> findAll() {
        List<Student> students = studentMapper.selectList(null);
        for (Student student : students) {
            student.setCname(classesMapper.selectById(student.getCid()).getCname());
        }
        return students;
    }

    //id查询学生
    @Override
    public Student findById(int sid) {
        return studentMapper.selectById(sid);
    }
    //添加学生
    @Override
    public void add(Student student) {
        studentMapper.insert(student);
    }

    //修改学生
    @Override
    public void update(Student student) {
        studentMapper.updateById(student);
    }

    //删除学生
    @Override
    public void delete(int sid) {
        studentMapper.deleteById(sid);
    }

    //分页查询
    @Override
    public IPage<Student> findByPage(int page, int pageSize) {
        //开始分页，用selectpage
        //1. 封装分页参数对象
        IPage <Student> ipage = new Page<>(page,pageSize);
        //2. 封装查询条件
        Wrapper<Student> wrapper = new QueryWrapper<>();   //这里没有查询条件不写
        //3. 分页结果返回
        return studentMapper.selectPage(ipage, wrapper);
       //参数一代表分页参数对象，参数二代表条件
    }

    @Override
    public IPage<Student> search(int page, int pageSize, StudentVo studentVo) {
        //开始
        //1. 封装分页参数对象
        IPage <Student> ipage = new Page<>(page,pageSize);
        //2. 封装查询条件
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        //2.1 查询条件判断
        if (studentVo != null){
            if (!StringUtils.isEmpty(studentVo.getSname())){
                wrapper.like("sname",studentVo.getSname());               //这里这个like第一歌参数是数据库对应的列名column
            }
            if (!StringUtils.isEmpty(studentVo.getAddr())){
                wrapper.like("addr",studentVo.getAddr());
            }
            if (studentVo.getCid()!= null){
                wrapper.like("cid",studentVo.getCid());
            }
        }
        return studentMapper.selectPage(ipage,wrapper);
    }


}
