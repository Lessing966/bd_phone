package com.example.making.modules.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.making.common.DateUtils;
import com.example.making.common.StringUtils;
import com.example.making.modules.dao.*;
import com.example.making.modules.dto.UserDTO;
import com.example.making.modules.dto.WarrMainDTO;
import com.example.making.modules.entity.*;
import com.example.making.modules.service.MaintenanceService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    MaintenanceDao maintenanceDao;

    @Autowired
    WarrMainDao warrmain;

    @Autowired
    WarrantyDao warrantyDao;

    @Autowired
    LoginDao userDao;

    @Value("${local.path}")
    String path;

    @Value("${local.url}")
    String url;

    @Override
    public void crateMaintenance(MaintenanceEntity entity) {
        try {
            if(!StringUtils.isEmpty(entity.getFileurl())){
                String[] split = entity.getFileurl().split(",");
                entity.setTaskUrlone(split[0]);
                if(split.length>1){
                    entity.setTaskUrltwo(split[1]);
                }
            }
            entity.setTaskSn(String.valueOf(System.currentTimeMillis()));//订单号 毫秒级时间戳
            entity.setCreateTime(DateUtils.getStringDate());
            entity.setUpdateTime(DateUtils.getStringDate());
            int insert = maintenanceDao.insert(entity);
            if(insert>0 && entity.getW_id()!= 0){
                warrantyDao.updateMidByid(entity.getId(),entity.getW_id());
            }
            if(entity.getW_id()!= 0 && !StringUtils.isEmpty(entity.getP_id())){
                maintenanceDao.updatestatus(entity.getId(),1);
                warrantyDao.updatestatus(entity.getW_id(),1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Page<MaintenanceEntity> getMaintenance(MaintenanceEntity entity) {
        Page<MaintenanceEntity> page = new Page<>(entity.getPageNumber(),entity.getPageSize());
        List<MaintenanceEntity> list =maintenanceDao.getMaintenance(page,entity);
        for(MaintenanceEntity m: list){
            WarrantyEntity wid = warrantyDao.selectOne(new QueryWrapper<WarrantyEntity>().eq("m_id", m.getId()));
            if(!ObjectUtils.isEmpty(wid)){
                m.setWId(wid.getId());
            }
        }
        return page.setRecords(list);
    }

    @Override
    public void warrmain(WarrMainEntity entity) {
        warrmain.insert(entity);
    }


    @Override
    public Object getWarrList(WarrantyEntity entity) {
        Page<WarrantyEntity> page = new Page<>(entity.getPageNumber(),entity.getPageSize());
        List<WarrantyEntity> list =warrantyDao.getWarrList(page,entity);
        return page.setRecords(list);
    }

    @Override
    public void complete(WarrMainDTO dto) {
        //更新检修单状态为 已完成
        maintenanceDao.updatestatus(dto.getMid(),2);
        //
        warrantyDao.updatestatus(dto.getWid(),2);
    }

    @Override
    public Object getMaintenance1(MaintenanceEntity entity) {
        Page<MaintenanceEntity> page = new Page<>(entity.getPageNumber(),entity.getPageSize());
        List<MaintenanceEntity> list =maintenanceDao.getMaintenance1(page,entity);
        for(MaintenanceEntity m: list){
            WarrantyEntity wid = warrantyDao.selectOne(new QueryWrapper<WarrantyEntity>().eq("m_id", m.getId()));
            if(!ObjectUtils.isEmpty(wid)){
                m.setWId(wid.getId());
            }
        }
        return page.setRecords(list);
    }

    @Override
    public void updateMaintenance(MaintenanceEntity entity) {
        try {
            if(!StringUtils.isEmpty(entity.getFileurl())){
                String[] split = entity.getFileurl().split(",");
                entity.setTaskUrlone(split[0]);
                if(split.length>1){
                    entity.setTaskUrltwo(split[1]);
                }
            }
            entity.setUpdateTime(DateUtils.getStringDate());
            int insert = maintenanceDao.updateById(entity);
            if(insert>0 && entity.getW_id()!= 0){
                warrantyDao.updateMidByid(entity.getId(),entity.getW_id());
            }
            if(entity.getW_id()!= 0 && !StringUtils.isEmpty(entity.getP_id())){
                maintenanceDao.updatestatus(entity.getId(),1);
                warrantyDao.updatestatus(entity.getW_id(),1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
