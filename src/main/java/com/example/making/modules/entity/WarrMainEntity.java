package com.example.making.modules.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_war_main")
public class WarrMainEntity {

    private Integer wId;

    private Integer mId;
}
