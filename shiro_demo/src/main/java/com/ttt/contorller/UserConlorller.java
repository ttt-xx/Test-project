package com.ttt.contorller;

import com.ttt.pojo.Studen;
import com.ttt.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserConlorller {
    @Autowired
    StudentService studentService;

    @RequestMapping("ut")
    public String UserTestDemo() {
        System.out.println("hello");
        return "ut";
    }

    @RequestMapping("ThymeleafDemo")
    public String ThymeleafDemo(Model model) {
        model.addAttribute("name", "小白");
        System.out.println("成功进入主页面");
        return "test";
    }

    @RequestMapping("add")
    public String add() {
        System.out.println("成功进入添加页面");
        return "user/add";
    }

    @RequestMapping("update")
    public String update() {
        System.out.println("成功进入修改页面");
        return "user/update";
    }

    @RequestMapping("toLogin")
    public String toLogin() {
        System.out.println("成功进入登录页面");
        return "login";
    }

    @RequestMapping("noAuth")
    public String noAuth() {
        System.out.println("成功进入未授权页面");
        return "noAuth";
    }

    @RequestMapping("login")
    public String Login(String name, String password, Model model) {
        System.out.println("成功进入登录验证页面");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            subject.login(token);
            //登陆成功
            return "redirect:ThymeleafDemo";
        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在");
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }



}
