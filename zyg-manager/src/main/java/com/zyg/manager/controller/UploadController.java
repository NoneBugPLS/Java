package com.zyg.manager.controller;

import com.zyg.common.utils.FastDFSClient;
import com.zyg.common.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ------------------------------
 * 功能：
 * 作者：WF
 * 微信：hbxfwf13590332912
 * 创建时间：2021/12/2-10:01
 * ------------------------------
 */
@RestController
@RequestMapping
public class UploadController {

    @PostMapping("/manager/upload")
    public R uploadFile(MultipartFile file) throws Exception {
        //1. 得到fastdfs对象
        FastDFSClient client = new FastDFSClient("classpath:fastdfs.conf");
        //2. 得到文件名
        String filename = file.getOriginalFilename();
        //3. 得到文件后缀名
        String extName = filename.substring(filename.lastIndexOf(".") + 1);
        //4. 开始文件上传
        String path = client.uploadFile(file.getBytes(),extName);     //参数1：代表上传的文件数据 参数2：代表文件后缀名

        //5. 得到文件地址：
        String url = "http://192.168.56.17:8080/" + path;
        //6. 返回
        return R.ok().put("url",url);

    }
}
