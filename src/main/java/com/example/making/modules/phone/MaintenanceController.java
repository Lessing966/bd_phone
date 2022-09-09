package com.example.making.modules.phone;

import com.example.making.common.Extend;
import com.example.making.common.JwtUtils;
import com.example.making.common.R;
import com.example.making.modules.dao.LoginDao;
import com.example.making.modules.dto.WarrMainDTO;
import com.example.making.modules.entity.LoginEntity;
import com.example.making.modules.entity.MaintenanceEntity;
import com.example.making.modules.entity.WarrantyEntity;
import com.example.making.modules.service.MaintenanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value = "",tags = "小程序检修单接口")
@RequestMapping("/app")
public class MaintenanceController {

    @Autowired
    MaintenanceService service;
    @Autowired
    LoginDao loginDao;

    @Extend
    @ApiOperation("创建检修单")
    @PostMapping("/crateMaintenance")
    public R crateMaintenance(MaintenanceEntity entity, HttpServletRequest request){
        String userid = JwtUtils.getUserid(request);
        entity.setUid(Integer.valueOf(userid));
        service.crateMaintenance(entity);
        return R.ok();
    }

    @ApiOperation("检修单列表")
    @PostMapping("/getMaintenance")
    public R getWarranty(@RequestBody MaintenanceEntity entity, HttpServletRequest request){
        String userid = JwtUtils.getUserid(request);
        LoginEntity entity1 = loginDao.selectById(userid);
        Integer role = entity1.getRole();
        if(role == 0){
            entity.setUid(null);
            return R.ok().put("data",service.getMaintenance(entity));
        }else if(role == 1){
            entity.setUid(Integer.valueOf(userid));
            return R.ok().put("data",service.getMaintenance1(entity));
        }else {
            entity.setUid(Integer.valueOf(userid));
            return R.ok().put("data",service.getMaintenance(entity));
        }
    }

    @Extend
    @ApiOperation("编辑检修单")
    @PostMapping("/updateMaintenance")
    public R updateMaintenance(@RequestBody MaintenanceEntity entity){
//        String userid = JwtUtils.getUserid(request);
//        entity.setUid(Integer.valueOf(userid));
        service.updateMaintenance(entity);
        return R.ok();
    }


    @ApiOperation("获取任务关联列表")
    @PostMapping("/getWarrList")
    public R getWarrList(@RequestBody WarrantyEntity entity){
        return R.ok().put("data",service.getWarrList(entity));
    }


    @Extend
    @ApiOperation("检修完成按钮")
    @PostMapping("/complete")
    public R complete(@RequestBody WarrMainDTO dto){
        service.complete(dto);
        return R.ok();
    }
}
