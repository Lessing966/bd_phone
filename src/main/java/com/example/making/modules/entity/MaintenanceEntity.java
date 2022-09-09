package com.example.making.modules.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.making.modules.dto.UserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@TableName("sys_maintenance")
public class MaintenanceEntity {

    private Integer id;


    @ApiModelProperty(value = "关联报修单Id")
    @TableField(exist = false)
    private int wId;

    @TableField(exist = false)
    private int w_id;

    @ApiModelProperty(value = "检修单号")
    private String taskSn;

    @ApiModelProperty(value = "检修名称")
    private String taskName;

    @ApiModelProperty(value = "检修位置")
    private String location;

    @ApiModelProperty(value = "检修描述")
    private String task;

    @ApiModelProperty(value = "任务紧急状态 0=紧急 1=中 2=低")
    private String t_status;

    @ApiModelProperty(value = "任务紧急状态 0=紧急 1=中 2=低")
    private String tStatus;

    @ApiModelProperty(value = "检修图")
    private String taskUrlone;

    @ApiModelProperty(value = "检修图")
    private String taskUrltwo;

    @ApiModelProperty(value = "检修图地址")
    @TableField(exist = false)
    private String fileurl;

    @ApiModelProperty(value = "录音地址")
    private String mp3url;

    @ApiModelProperty(value = "录音秒数")
    private String duration;

    @ApiModelProperty(value = "任务状态")
    private Integer status;

    @ApiModelProperty(value = "分派人id")
    private String p_id;

    @ApiModelProperty(value = "分派人id")
    private String pId;

    @ApiModelProperty(value = "分派人list")
    private List<UserDTO> user;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "修改时间")
    private String updateTime;

    private Integer uid;

    @ApiModelProperty(value = "页号")
    @TableField(exist = false)
    private int pageNumber;

    @ApiModelProperty(value = "页面条数")
    @TableField(exist = false)
    private int pageSize;
}
