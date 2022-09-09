package com.example.making.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.making.modules.dto.WarrMainOneDTO;
import com.example.making.modules.entity.WarrantyEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface WarrantyDao extends BaseMapper<WarrantyEntity> {

    List<WarrantyEntity> getWarranty(Page<WarrantyEntity> page,@Param("entity") WarrantyEntity entity);


    WarrMainOneDTO getWarrantyByid(Integer id);

    void updateMidByid(@Param("id") Integer id, @Param("wid") int wId);

    List<WarrantyEntity> getWarrList(Page<WarrantyEntity> page,@Param("entity") WarrantyEntity entity);

    void updatestatus(@Param("wid") Integer wid,@Param("status") Integer status);

}
