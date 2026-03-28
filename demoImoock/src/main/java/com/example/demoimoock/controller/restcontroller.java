package com.example.demoimoock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author dwt
 * @version V1.0
 * @Package com.example.demoimoock.controller
 * @date 2026/3/28 13:21
 * @description:
 */
@RestController
public class restcontroller {
    @GetMapping("/get")
    public String hello(){
        return "get方法，";
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        file.transferTo(new File("E:\\"+file.getOriginalFilename()));
        return "post方法，上传成功";
    }
}
