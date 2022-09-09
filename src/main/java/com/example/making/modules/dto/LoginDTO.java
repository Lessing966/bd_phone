package com.example.making.modules.dto;

import io.swagger.annotations.Api;
import lombok.Data;

@Data
@Api(tags = "登录参数")
public class LoginDTO {

    private String username;

    private String password;

}
