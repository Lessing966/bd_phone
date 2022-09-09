package com.example.making.modules.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.making.modules.entity.MaintenanceEntity;
import com.example.making.modules.entity.WarrantyEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MaintenanceDao extends BaseMapper<MaintenanceEntity> {

    List<MaintenanceEntity> getMaintenance(Page<MaintenanceEntity > page, MaintenanceEntity entity);

    void updatestatus(@Param("mid") Integer mid,@Param("status") Integer status);

    List<MaintenanceEntity> getMaintenance1(Page<MaintenanceEntity> page, MaintenanceEntity entity);
}

