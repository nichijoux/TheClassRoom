package com.zh.tcr.model.vo.live;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class LiveVisitorQueryVO {

    @ApiModelProperty(value = "直播课程id")
    private Long liveCourseId;
}

