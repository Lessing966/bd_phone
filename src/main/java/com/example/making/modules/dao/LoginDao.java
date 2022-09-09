package com.example.making.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.making.modules.dto.UserDTO;
import com.example.making.modules.entity.LoginEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LoginDao extends BaseMapper<LoginEntity> {

    List<LoginEntity> getUserList(Page<LoginEntity> page,@Param("entity") LoginEntity entity);

    UserDTO selectuser(@Param("id") String id);


}

