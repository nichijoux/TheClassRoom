package com.zh.tcr.model.entity.vod;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zh.tcr.model.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "Teacher")
@TableName("teacher")
public class Teacher extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师姓名")
    @NotBlank(message = "讲师姓名不能为空")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "讲师简介")
    @NotBlank(message = "讲师简历不能为空")
    @TableField("intro")
    private String intro;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    @NotBlank(message = "讲师资历不能为空")
    @TableField("career")
    private String career;

    @ApiModelProperty(value = "头衔 0高级讲师 1首席讲师")
    @Min(value = 0, message = "头衔数据只能为0或1")
    @Max(value = 1, message = "头衔数据只能为0或1")
    @TableField("level")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "排序")
    @Min(value = 0, message = "排序数据最小为0")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "入驻时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("join_date")
    private Date joinDate;
}