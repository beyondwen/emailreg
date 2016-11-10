package com.wenhao.netshop.web.controller;

import com.wenhao.netshop.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lw on 2016/11/10.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping("/index")
    public String index(User user) {
        System.out.println(user);
        return "register/register";
    }
}
