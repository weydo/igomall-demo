package com.igomall.controller;

import com.igomall.entity.Admin;
import com.igomall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class IndexController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String index(){
        return "index";
    }

    @PostMapping("/currentUser")
    public Admin currentUser(){
        return adminService.find(1L);
    }

    @PostMapping("/update")
    public Admin update(Long id,String email,String name,String department){
        Admin admin = adminService.find(id);
        System.out.println(admin.getRoles());
        admin.setEmail(email);
        admin.setName(name);
        admin.setDepartment(department);
        adminService.update(admin);
        return admin;
    }
}
