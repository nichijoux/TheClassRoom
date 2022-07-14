package com.zh.tcr.model.vo.vod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "讲师查询条件")
public class TeacherQueryCondition {
    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "入驻时间")
    private String joinDateBegin;

    @ApiModelProperty(value = "入驻时间")
    private String joinDateEnd;
}
