package com.example.springbootdemotemplatethymeleaf.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version: java version 1.8
 * @Author: dwt
 * @description:
 * @date: 2026-02-22 15:29
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @PostMapping("/login")
    public ModelAndView login(User user, HttpServletRequest request) {

        System.out.println("22222222");

        ModelAndView mv = new ModelAndView();

        mv.addObject(user);
        mv.setViewName("redirect:/");

        request.getSession().setAttribute("user", user);
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        System.out.println("33333333");
        return new ModelAndView("page/login");
    }
}
