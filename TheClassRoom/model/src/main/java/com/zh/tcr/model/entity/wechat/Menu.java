package com.zh.tcr.model.entity.wechat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zh.tcr.model.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "菜单")
@TableName("menu")
public class Menu extends BaseEntity {

    @ApiModelProperty(value = "id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "类型")
    @NotBlank(message = "菜单类型不能为空")
    private String type;

    @ApiModelProperty(value = "网页 链接，用户点击菜单可打开链接")
    private String url;

    @ApiModelProperty(value = "菜单KEY值，用于消息接口推送")
    @TableField("menu_key")
    private String menuKey;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}