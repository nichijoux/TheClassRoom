package com.zh.tcr.model.entity.activity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zh.tcr.model.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "CouponInfo")
@TableName("coupon_info")
public class CouponInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物券类型 1 现金券")
    @TableField("coupon_type")
    private String couponType;

    @ApiModelProperty(value = "优惠卷名字")
    @TableField("coupon_name")
    @NotBlank(message = "优惠卷名称不能为空")
    private String couponName;

    @ApiModelProperty(value = "金额")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "使用门槛 0->没门槛")
    @TableField("condition_amount")
    private BigDecimal conditionAmount;

    @ApiModelProperty(value = "可以领取的开始日期")
    @TableField("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @ApiModelProperty(value = "可以领取的结束日期")
    @TableField("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @ApiModelProperty(value = "使用范围[1->全场通用]")
    @TableField("range_type")
    private String rangeType;

    @ApiModelProperty(value = "使用范围描述")
    @TableField("rule_desc")
    @NotBlank(message = "规则描述不能为空")
    private String ruleDesc;

    @ApiModelProperty(value = "发行数量")
    @TableField("publish_count")
    @Min(value = 1, message = "发行数量不能为0或负数")
    private Integer publishCount;

    @ApiModelProperty(value = "每人限领张数")
    @TableField("per_limit")
    private Integer perLimit;

    @ApiModelProperty(value = "已使用数量")
    @TableField("use_count")
    private Integer useCount;

    @ApiModelProperty(value = "领取数量")
    @TableField("receive_count")
    private Integer receiveCount;

    @ApiModelProperty(value = "过期时间")
    @TableField("expire_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expireTime;

    @ApiModelProperty(value = "发布状态[0-未发布，1-已发布]")
    @TableField("publish_status")
    private Boolean publishStatus;

    @ApiModelProperty(value = "使用状态")
    @TableField(exist = false)
    private String couponStatus;

    @ApiModelProperty(value = "优惠券领取表id")
    @TableField(exist = false)
    private Long couponUseId;

    @ApiModelProperty(value = "领取时间")
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date getTime;
}