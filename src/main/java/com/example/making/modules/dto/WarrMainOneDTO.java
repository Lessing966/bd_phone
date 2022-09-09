package com.example.making.modules.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.making.modules.entity.LoginEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class WarrMainOneDTO {

    @ApiModelProperty(value = "位置")
    private String location;

    @ApiModelProperty(value = "报修名称")
    private String wname;

    @ApiModelProperty(value = "报修描述")
    private String describes;

    @ApiModelProperty(value = "报修图1")
    private String sceneUrlone;

    @ApiModelProperty(value = "报修图2")
    private String sceneUrltwo;

    @ApiModelProperty(value = "检修单号")
    private String taskSn;

    @ApiModelProperty(value = "任务状态")
    private Integer status;

//    @ApiModelProperty(value = "分派人")
//    private String name;
//
//    @ApiModelProperty(value = "联系方式")
//    private String phone;

    private String pid;

    private String task;

    @ApiModelProperty(value = "分派人list")
    private List<UserDTO> user;

    @ApiModelProperty(value = "录音地址")
    private String mp3url;

    private String duration;

    @ApiModelProperty(value = "修改时间")
    private String Time;

    @ApiModelProperty(value = "检修图")
    private String taskUrlone;

    @ApiModelProperty(value = "检修图")
    private String taskUrltwo;


}
