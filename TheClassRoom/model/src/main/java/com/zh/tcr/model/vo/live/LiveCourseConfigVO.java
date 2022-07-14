package com.zh.tcr.model.vo.live;

import com.zh.tcr.model.entity.live.LiveCourseConfig;
import com.zh.tcr.model.entity.live.LiveCourseGoods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "LiveCourseConfig")
public class LiveCourseConfigVO extends LiveCourseConfig {

	@ApiModelProperty(value = "商品列表")
	private List<LiveCourseGoods> liveCourseGoodsList;
}