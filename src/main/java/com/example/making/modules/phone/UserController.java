package com.example.making.modules.phone;

import com.example.making.common.Extend;
import com.example.making.common.R;
import com.example.making.common.StringUtils;
import com.example.making.modules.entity.LoginEntity;
import com.example.making.modules.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
@Api(value = "",tags = "小程序用户管理")
public class UserController {

    @Autowired
    LoginService loginService;

    @Extend
    @ApiOperation("新增用户")
    @PostMapping("addUser")
    public R addUser(@RequestBody LoginEntity entity){
        if(!StringUtils.isEmpty(entity.getUsername())){
            LoginEntity entity1 = loginService.selectByUserName(entity.getUsername());
            if(!ObjectUtils.isEmpty(entity1)){
                return R.error(404,"用户名已存在");
            }
        }
        loginService.addUser(entity);
        return R.ok();
    }


    @ApiOperation("用户列表")
    @PostMapping("getUser")
    public R getUserList(@RequestBody LoginEntity entity){
        return R.ok().put("data",loginService.getUserList(entity));
    }

    @Extend
    @ApiOperation("编辑用户")
    @PostMapping("updateUser")
    public R updateUser(@RequestBody LoginEntity entity){
        loginService.updateUser(entity);
        return R.ok();
    }

    @Extend
    @ApiOperation("删除用户")
    @GetMapping("deleteUser")
    public R deleteUser(Integer id){
        loginService.deleteUser(id);
        return R.ok();
    }
}