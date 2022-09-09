package com.example.making.modules.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.making.modules.dto.WarrMainDTO;
import com.example.making.modules.entity.*;

public interface MaintenanceService {

    void crateMaintenance(MaintenanceEntity entity);

    Page<MaintenanceEntity> getMaintenance(MaintenanceEntity entity);

    void warrmain(WarrMainEntity dto);

    Object getWarrList(WarrantyEntity entity);

    void complete(WarrMainDTO dto);

    Object getMaintenance1(MaintenanceEntity entity);

    void updateMaintenance(MaintenanceEntity entity);

}

