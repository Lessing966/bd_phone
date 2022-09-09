package com.example.making.modules.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.making.modules.entity.LoginEntity;

public interface LoginService {
    LoginEntity selectByUserName(String username);

    void addUser(LoginEntity entity);

    Page<LoginEntity> getUserList(LoginEntity entity);

    void updateUser(LoginEntity entity);

    void deleteUser(Integer id);

}
