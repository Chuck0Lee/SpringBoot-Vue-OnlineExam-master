package com.exam.controller;

import com.exam.entity.Admin;
import com.exam.entity.ApiResult;
import com.exam.entity.Teacher;
import com.exam.serviceimpl.AdminServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AdminController {

    private AdminServiceImpl adminService;
    @Autowired
    public AdminController(AdminServiceImpl adminService){
        this.adminService = adminService;
    }


    /**
    * @Description: 查询所有管理员
    * @Author: liqichang
    * @Date: 2021/4/28 15:50
    */
    @GetMapping("/admins")
    public ApiResult findAll(){
        System.out.println("查询全部");
        return ApiResultHandler.success(adminService.findAll());
    }

    /**
    * @Description: 根据id查询
    * @Author: liqichang
    * @Date: 2021/4/28 15:50
    */
    @GetMapping("/admin/{adminId}")
    public ApiResult findById(@PathVariable("adminId") Integer adminId){
        System.out.println("根据ID查找");
        return ApiResultHandler.success(adminService.findById(adminId));
    }
    /**
    * @Description: 删除用户
    * @Author: liqichang
    * @Date: 2021/4/28 15:50
    */
    @DeleteMapping("/admin/{adminId}")
    public ApiResult deleteById(@PathVariable("adminId") Integer adminId){
        adminService.deleteById(adminId);
        return ApiResultHandler.success();
    }


    @PutMapping("/adminPWD")
    public ApiResult updatePwd(@RequestBody Admin admin) {
        adminService.updatePWD(admin);
        return ApiResultHandler.buildApiResult(200,"密码更新成功",null);
    }

    /**
    * @Description: 更新信息
    * @Author: liqichang
    * @Date: 2021/4/28 15:53
    */
    @PutMapping("/admin/{adminId}")
    public ApiResult update(@PathVariable("adminId") Integer adminId, Admin admin){
        return ApiResultHandler.success(adminService.update(admin));
    }
    /**
    * @Description: 添加管理员
    * @Author: liqichang
    * @Date: 2021/4/28 15:53
    */
    @PostMapping("/admin")
    public ApiResult add(Admin admin){
        return ApiResultHandler.success(adminService.add(admin));
    }
}
