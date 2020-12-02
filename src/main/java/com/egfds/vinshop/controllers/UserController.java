package com.egfds.vinshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final String LOGINSTATUS = "loginstatus";

    /*@RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String email, @RequestParam("password") String password, HttpSession
                        httpSession){
        if(email.equals("Michaelberko3@gmail.com") && password.equals("123")){
            httpSession.setAttribute(LOGINSTATUS, "yes");
        }
        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String loginPage() {
        return "user/login";
    }*/

}
