package com.igomall.controller;

import com.igomall.entity.Admin;
import com.igomall.service.AdminService;
import com.igomall.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/account")
    public Map<String,Object> index(String userName, String password, String type, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> data = new HashMap<>();
        data.put("type",type);
        Admin admin = adminService.findByUsername(userName);
        if(admin==null){
            data.put("errorMsg","用户名或者密码错误");
            data.put("status","error");
            return data;
        }

        if(!admin.isValidCredentials(password)){
            data.put("errorMsg","用户名或者密码错误");
            data.put("status","error");
            return data;
        }
        data.put("status","ok");
        data.put("currentAuthority","admin");
        WebUtils.addCookie(request,response,"userId",admin.getId()+"");
        return data;
    }
}
