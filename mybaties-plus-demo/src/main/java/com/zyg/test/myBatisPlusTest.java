package com.zyg.test;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyg.entity.Student;
import com.zyg.entity.StudentVo;
import com.zyg.service.ClassesService;
import com.zyg.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */

@SpringBootTest
public class myBatisPlusTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassesService classesService;

    //1. 列表学生
    @Test
    public void test01(){
        List<Student> all = studentService.findAll();
        for (Student student : all) {
            System.out.println("student = " + student);
        }
    }

    //2. 根据id查询学生
    @Test
    public void test02(){
        //1. 定义要查询的学生id
        int sid = 81;
        Student student = studentService.findById(sid);
        System.out.println("student = " + student);
    }

    //3. 添加学生    可以用没有主键的构造方法先构造学生
    @Test
    public void test03(){
        //1. 先定义要添加的学生信息
        Student student = new Student("谢霆锋",35,"男","香港",1);
        studentService.add(student);
    }

    //4. 修改学生
    @Test
    public void test04(){
         //1. 根据学生id找到学生
        int sid = 82;
        Student student = studentService.findById(sid);
        //判断是否有值
        if (student != null){
            student.setSname("陈冠希");
            student.setAddr("上海");
            student.setAge(18);
            student.setCid(1);
            student.setSex("男");
            studentService.update(student);
        }else {
            System.out.println("没有学号"+ sid + "的学生！");
        }
    }

    //5. 删除学生
    @Test
    public void test05(){
        //1. 根据id删除学生
        int sid = 56;
        studentService.delete(sid);
   }

   //6. 分页
    @Test
    public void test06(){
        //1. 定义分页查询参数
        int page = 1;           //当前页
        int pageSize = 5;       //每页大小
        //2. 分页
        IPage<Student> studentIPage = studentService.findByPage(page,pageSize);
        //3. 查看分页信息
        long pages = studentIPage.getPages();
        long total = studentIPage.getTotal();
        List<Student> records = studentIPage.getRecords();
        System.out.println("page = " + page);
        System.out.println("total = " + total);
        System.out.println("records = " + records);
    }

    //分页待条件查询
    @Test
    public void test07(){
        //1. 定义分页参数
        int page = 1;
        int pageSize=5;
        //2. 封装查询条件  用新建的StudentVo对象
        StudentVo studentVo = new StudentVo();
        studentVo.setSname("小");
        studentVo.setAddr("州");
        //3. 开始分页
        IPage<Student> studentIPage = studentService.search(page,pageSize,studentVo);
        List<Student> records = studentIPage.getRecords();
        long pages = studentIPage.getPages();
        long total = studentIPage.getTotal();
        System.out.println("page = " + page);
        System.out.println("total = " + total);
        System.out.println("records = " + records);
    }
}
