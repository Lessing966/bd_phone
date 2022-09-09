package com.example.making.modules.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.making.modules.entity.MaintenanceEntity;
import com.example.making.modules.entity.WarrantyEntity;
import org.springframework.web.multipart.MultipartFile;

public interface WarrantyService {
    void crateWarranty(WarrantyEntity entity);

    Page<WarrantyEntity> getWarranty(WarrantyEntity entity);

    void updateWarranty(WarrantyEntity entity);

    String addFile(MultipartFile file);

    Object getWarrantyByid(Integer id);

    void addWarrmain(MaintenanceEntity entity);

}
