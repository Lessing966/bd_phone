package com.example.making.modules.phone;

import com.example.making.common.Extend;
import com.example.making.common.JwtUtils;
import com.example.making.common.R;
import com.example.making.modules.dao.LoginDao;
import com.example.making.modules.entity.LoginEntity;
import com.example.making.modules.entity.MaintenanceEntity;
import com.example.making.modules.entity.WarrantyEntity;
import com.example.making.modules.service.LoginService;
import com.example.making.modules.service.WarrantyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value = "",tags = "小程序报修单接口")
@RequestMapping("/app")
public class WarrantyController {

    @Autowired
    WarrantyService warrantyService;

    @Autowired
    LoginDao loginDao;

    @Extend
    @ApiOperation("创建报修单")
    @PostMapping("/crateWarranty")
    public R crateWarranty(WarrantyEntity entity, HttpServletRequest request){
        String userid = JwtUtils.getUserid(request);
        entity.setUid(Integer.valueOf(userid));
        warrantyService.crateWarranty(entity);
        return R.ok();
    }

    @ApiOperation("上传文件")
    @PostMapping("/addFile")
    public R addFile(MultipartFile file){
        String s = warrantyService.addFile(file);
        return R.ok().put("url",s);
    }

    @ApiOperation("报修单列表")
    @PostMapping("/getWarranty")
    public R getWarranty(@RequestBody WarrantyEntity entity, HttpServletRequest request){
        String userid = JwtUtils.getUserid(request);
        LoginEntity entity1 = loginDao.selectById(userid);
        Integer role = entity1.getRole();
        if(role == 0){
            entity.setUid(null);
        }else {
            entity.setUid(Integer.valueOf(userid));
        }
        return R.ok().put("data",warrantyService.getWarranty(entity));
    }

    @Extend
    @ApiOperation("编辑报修单")
    @PostMapping("/updateWarranty")
    public R updateWarranty(@RequestBody WarrantyEntity entity){
       warrantyService.updateWarranty(entity);
        return R.ok();
    }


    @ApiOperation("报修单详情")
    @GetMapping("/getWarrantyByid")
    public R getWarrantyByid(Integer id){
        return R.ok().put("data",warrantyService.getWarrantyByid(id));
    }


    @ApiOperation("创建检修接口")
    @PostMapping("/addWarrmain")
    public R addWarrmain(@RequestBody MaintenanceEntity entity, HttpServletRequest request){
        String userid = JwtUtils.getUserid(request);
        entity.setUid(Integer.valueOf(userid));
        warrantyService.addWarrmain(entity);
        return R.ok();
    }


}
