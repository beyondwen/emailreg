package com.wenhao.netshop.web.controller;

import com.wenhao.netshop.dao.UserMapper;
import com.wenhao.netshop.domain.User;
import com.wenhao.netshop.exception.ServiceException;
import com.wenhao.netshop.service.RegisterValidateService;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.expression.ParseException;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private UserMapper dao;

    @Resource
    private RegisterValidateService service;

    @RequestMapping("/index")
    public String index(User user) {
        System.out.println(user);
        return "register/register";
    }

    @RequestMapping("/login")
    public String login(User u, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(u.getUsername(), u.getPassword());
        try {
            subject.login(token);
            return "register/login_success";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //model.addAttribute("error","用户名或密码错误") ;
            return "";
        }
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView load(HttpServletRequest request, HttpServletResponse response, User user) throws ParseException {
        String action = request.getParameter("action");
        System.out.println("-----r----" + action);
        ModelAndView mav = new ModelAndView();

        if ("register".equals(action)) {
            //注册
            String email = request.getParameter("email");
            service.processregister(user);//发邮箱激活
            mav.addObject("text", "注册成功");
            mav.setViewName("register/register_success");
        } else if ("activate".equals(action)) {
            //激活
            String email = request.getParameter("email");//获取email
            String validateCode = request.getParameter("validateCode");//激活码

            try {
                service.processActivate(email, validateCode);//调用激活方法
                mav.setViewName("register/activate_success");
            } catch (ServiceException e) {
                request.setAttribute("message", e.getMessage());
                mav.setViewName("register/activate_failure");
            }
        }
        return mav;
    }

    @ResponseBody
    @RequestMapping("/validateusername")
    public String validateUsername(String username) {
        User user = dao.selectByUsername(username);
        if (user != null) {
            return "false";
        } else {
            return "success";
        }
    }

    @ResponseBody
    @RequestMapping("/validateemail")
    public String validateEmail(String email) {
        User user = dao.selectByEmail(email);
        if (user != null) {
            return "false";
        } else {
            return "success";
        }
    }
}
