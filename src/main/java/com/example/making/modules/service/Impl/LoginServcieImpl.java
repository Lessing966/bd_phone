package com.example.making.modules.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.making.common.DateUtils;
import com.example.making.common.MD5Utils;
import com.example.making.modules.dao.LoginDao;
import com.example.making.modules.entity.LoginEntity;
import com.example.making.modules.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServcieImpl implements LoginService {

    @Autowired
    LoginDao loginDao;

    @Override
    public LoginEntity selectByUserName(String username) {
        return loginDao.selectOne(new QueryWrapper<LoginEntity>().eq("username",username));
    }

    @Override
    public void addUser(LoginEntity entity) {
        entity.setCreateTime(DateUtils.getStringDate());
        entity.setUpdateTime(DateUtils.getStringDate());
        String md5paswod = MD5Utils.MD5Encode(entity.getPassword());
        entity.setPassword(md5paswod);
        loginDao.insert(entity);
    }

    @Override
    public Page<LoginEntity> getUserList(LoginEntity entity) {
        Page<LoginEntity> page =new Page<>(entity.getPageNumber(),entity.getPageSize());
        List<LoginEntity> list = loginDao.getUserList(page,entity);
        return page.setRecords(list);
    }

    @Override
    public void updateUser(LoginEntity entity) {
        entity.setUpdateTime(DateUtils.getStringDate());
        String md5paswod = MD5Utils.MD5Encode(entity.getPassword());
        entity.setPassword(md5paswod);
        loginDao.updateById(entity);
    }

    @Override
    public void deleteUser(Integer id) {
        loginDao.deleteById(id);
    }


}
