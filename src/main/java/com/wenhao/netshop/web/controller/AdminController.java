package com.wenhao.netshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lw on 2016/11/11.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/list")
    public String list() {
        return "admin/list";
    }
}
