package com.trackservice.controller.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "html/login.html";
    }

    @RequestMapping("/home")
    public String home(@AuthenticationPrincipal UserDetails user , Model model){
        model.addAttribute("username",user.getUsername());
        return "html/index.html";
    }

    @RequestMapping("/register")
    public String register(Model model){
        return "html/register.html";
    }


}
