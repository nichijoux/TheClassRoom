package com.zh.tcr.vod.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 视频来访者记录表
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VideoVisitor对象", description="视频来访者记录表")
public class VideoVisitor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "课程id")
    private Long courseId;

    @ApiModelProperty(value = "视频id")
    private Long videoId;

    @ApiModelProperty(value = "来访者用户id")
    private String userId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "进入时间")
    private String joinTime;

    @ApiModelProperty(value = "离开的时间")
    private String leaveTime;

    @ApiModelProperty(value = "用户停留的时间(单位：秒)")
    private Long duration;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;


}
