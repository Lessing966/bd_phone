package com.example.making.modules.service.Impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.making.common.DateUtils;
import com.example.making.common.FileUtils;
import com.example.making.common.StringUtils;
import com.example.making.modules.dao.LoginDao;
import com.example.making.modules.dao.MaintenanceDao;
import com.example.making.modules.dao.WarrantyDao;
import com.example.making.modules.dto.UserDTO;
import com.example.making.modules.dto.WarrMainOneDTO;
import com.example.making.modules.entity.MaintenanceEntity;
import com.example.making.modules.entity.WarrantyEntity;
import com.example.making.modules.service.MaintenanceService;
import com.example.making.modules.service.WarrantyService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class WarrantyServiceImpl implements WarrantyService {

    @Autowired
    WarrantyDao warrantyDao;

    @Autowired
    MaintenanceDao maintenanceDao;

    @Autowired
    LoginDao loginDao;

    @Value("${local.path}")
    String path;

    @Value("${local.url}")
    String url;

    @Override
    public void crateWarranty(WarrantyEntity entity) {
       try {
            if(!StringUtils.isEmpty(entity.getFileurl())){
                String[] split = entity.getFileurl().split(",");
                entity.setSceneUrlone(split[0]);
                if(split.length>1){
                    entity.setSceneUrltwo(split[1]);
                }
            }
           entity.setSn(String.valueOf(System.currentTimeMillis()));//订单号 毫秒级时间戳
           entity.setCreateTime(DateUtils.getStringDate());
           entity.setUpdateTime(DateUtils.getStringDate());
           warrantyDao.insert(entity);
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public Page<WarrantyEntity> getWarranty(WarrantyEntity entity) {
        Page<WarrantyEntity> page = new Page<>(entity.getPageNumber(),entity.getPageSize());
        List<WarrantyEntity> list = warrantyDao.getWarranty(page,entity);
        for(WarrantyEntity w:list){
            //转换为int数组
            if(!StringUtils.isEmpty(w.getIndexs())){
                String[] split = w.getIndexs().split(",");
                int[] ints = Arrays.asList(split).stream().mapToInt(Integer::parseInt).toArray();
                w.setIndex(ints);
            }
        }
        return page.setRecords(list);
    }

    @Override
    public void updateWarranty(WarrantyEntity entity) {
        try {
            if(!StringUtils.isEmpty(entity.getFileurl())){
                String[] split = entity.getFileurl().split(",");
                entity.setSceneUrlone(split[0]);
                entity.setSceneUrltwo(split[1]);
            }
            entity.setUpdateTime(DateUtils.getStringDate());
            warrantyDao.updateById(entity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String addFile(MultipartFile file) {
        try {
            String upload = FileUtils.upload(file, path, file.getOriginalFilename());
            return url+upload;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getWarrantyByid(Integer id) {
        List<UserDTO> list =new ArrayList<>();
        WarrMainOneDTO warrantyByid = warrantyDao.getWarrantyByid(id);
        if(!ObjectUtils.isEmpty(warrantyByid)){
            if(null != warrantyByid.getPid()){
                String[] split = warrantyByid.getPid().split(",");
                for(int i=0;i<split.length;i++){
                    UserDTO d = loginDao.selectuser(split[i]);
                    list.add(d);
                }
            }
            warrantyByid.setUser(list);
        }
        return warrantyByid;
    }

    @Override
    @Transactional
    public void addWarrmain(MaintenanceEntity entity) {
        entity.setTaskSn(String.valueOf(System.currentTimeMillis()));
        entity.setCreateTime(DateUtils.getStringDate());
        entity.setUpdateTime(DateUtils.getStringDate());
        int insert = maintenanceDao.insert(entity);
        if(insert>0){
            warrantyDao.updateMidByid(entity.getId(),entity.getW_id());
        }
    }


}
