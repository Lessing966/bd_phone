package com.example.making.modules.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("sys_login")
public class LoginEntity {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String head;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "部门名称")
    private String dname;

    @ApiModelProperty(value = "账户")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "用户角色 0-管理员 1-维保用户 2-普通用户")
    private Integer role;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "修改时间")
    private String updateTime;

    @ApiModelProperty(value = "页号")
    @TableField(exist = false)
    private int pageNumber;

    @ApiModelProperty(value = "页面条数")
    @TableField(exist = false)
    private int pageSize;
}
