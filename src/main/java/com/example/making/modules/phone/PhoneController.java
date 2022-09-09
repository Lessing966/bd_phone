package com.example.making.modules.phone;

import com.example.making.common.*;
import com.example.making.modules.dto.LoginDTO;
import com.example.making.modules.entity.LoginEntity;
import com.example.making.modules.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "",tags = "小程序后台接口")
@RequestMapping("/app")
public class PhoneController {

    @Autowired
    LoginService loginService;

    @Autowired
    RedisUtils redisUtils;

    @Extend
    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginDTO dto){
        if(StringUtils.isEmpty(dto.getUsername()) || StringUtils.isEmpty(dto.getPassword())){
            return R.error(404,"账户或密码不能为空");
        }
        LoginEntity entity = loginService.selectByUserName(dto.getUsername());
        if(ObjectUtils.isEmpty(entity)){
            return R.error(404,"用户不存在");
        }
        if(!MD5Utils.MD5Encode(dto.getPassword()).equals(entity.getPassword())){
            return R.error(404,"密码错误");
        }
        String token = JwtUtils.createToken(String.valueOf(entity.getId()), entity.getPhone());//生产jwttoken
        redisUtils.set(String.valueOf(entity.getId()),token);//存入redis

        return R.ok().put("user",entity)
                .put("token",token);
    }


}
