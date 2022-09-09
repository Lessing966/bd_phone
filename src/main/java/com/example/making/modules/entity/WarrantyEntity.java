package com.example.making.modules.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@TableName("sys_warranty")
public class WarrantyEntity {

    private Integer id;

    @ApiModelProperty(value = "报修单号")
    private String sn;

    private String indexs;

    @TableField(exist = false)
    private int[] index;

    @ApiModelProperty(value = "名称")
    private String wname;

    @ApiModelProperty(value = "位置")
    private String location;

    @ApiModelProperty(value = "报修描述")
    private String describes;

    @ApiModelProperty(value = "紧急状态  紧急等级 0=紧急 1=中 2=低")
    private Integer describeStatus;

//    @ApiModelProperty(value = "报修图1")
    private String sceneUrlone;


//    @ApiModelProperty(value = "报修图2")
    private String sceneUrltwo;

    @ApiModelProperty(value = "报修图地址")
    @TableField(exist = false)
    private String fileurl;

    @ApiModelProperty(value = "报修单状态 报修单状态 0=待处理 1=处理中 2=已完成 3=已取消")
    private Integer status;


    @ApiModelProperty(value = "关联检修id")
    private Integer mId;

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
