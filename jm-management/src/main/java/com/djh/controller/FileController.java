package com.djh.controller;

import com.aliyun.oss.AliyunOSSOperator;
import com.djh.Result;
import com.djh.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 其他接口 - 文件上传
 */
@Slf4j
@RestController
public class FileController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 上传图片 - /upload
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件: {}", file == null ? null : file.getOriginalFilename());
        if (file == null || file.isEmpty()) {
            throw new BusinessException("上传文件不能为空");
        }
        // 取原始文件的后缀名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        // 用UUID生成唯一文件名，避免同名文件相互覆盖
        String objectName = UUID.randomUUID().toString() + suffix;
        String url = aliyunOSSOperator.upload(file.getBytes(), objectName);
        return Result.success(url);
    }
}
