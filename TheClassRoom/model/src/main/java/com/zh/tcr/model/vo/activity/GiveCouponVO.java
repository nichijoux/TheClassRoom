package com.zh.tcr.model.vo.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "领取优惠券vo")
public class GiveCouponVO {

	@ApiModelProperty(value = "购物券类型")
	private Integer couponType;

	@ApiModelProperty(value = "优惠卷名字")
	private Long userId;
}