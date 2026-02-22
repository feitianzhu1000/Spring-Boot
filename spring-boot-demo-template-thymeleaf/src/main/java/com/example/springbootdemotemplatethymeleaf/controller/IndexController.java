package com.example.springbootdemotemplatethymeleaf.controller;

import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version: java version 1.8
 * @Author: dwt
 * @description:
 * @date: 2026-02-22 15:28
 */

@Slf4j
@Controller
public class IndexController {
    @GetMapping(value = {"", "/"})
    public ModelAndView index(HttpServletRequest request) {

        System.out.println("11111111");
        ModelAndView mv = new ModelAndView();

        User user = (User) request.getSession().getAttribute("user");

        System.out.println("user: " + user);

        if (ObjectUtil.isNull(user)) {
            mv.setViewName("redirect:/user/login");
        } else {
            mv.setViewName("page/index");
            mv.addObject(user);
        }

        return mv;
    }
}
