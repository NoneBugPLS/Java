package com.zyg.shop.controller;

import com.zyg.common.utils.FastDFSClient;
import com.zyg.common.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@RestController
public class UploadController {

    //1. 进行文件上传
    @PostMapping("/shop/upload")
    public R upload(MultipartFile file) throws Exception {                //必须跟前端传入的formdata中的key名字一致

        //1.1 构造fastDFS客户端（工具对象）
        FastDFSClient client = new FastDFSClient("classpath:fastdfs.conf");
        //1.2 得到文件名
        String filename = file.getOriginalFilename();
        //1.3 得到文件后缀名
        String extName = filename.substring(filename.lastIndexOf(".") + 1);
        //1.4 开始文件上传
        String path = client.uploadFile(file.getBytes(),extName);            //参数1：上传的二进制内容 参数2：文件后缀名

        //1.5 得到上传的文件路径
        String url = "http://192.168.56.17:8080/" + path;

        //1.6 返回
        return R.ok().put("url",url);

    }
}
