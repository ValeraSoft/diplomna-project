package com.spring_final.controller;

import com.spring_final.model.Authority;
import com.spring_final.model.User;
import com.spring_final.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SetAdmin {

    @Autowired
    UserService service;

    @RequestMapping("/setAdmin")
    public String setAdmin(@RequestParam("id") Integer userId, @RequestParam("admin") boolean isAdmin) {

        User user = service.getUser(userId);
        if(!isAdmin) {
            service.setAuthority(user, Authority.getAdminAuthority());
        } else {
            service.deleteAuthority(userId, Authority.getAdminAuthority());
        }

        return "redirect:/users";
    }
}
