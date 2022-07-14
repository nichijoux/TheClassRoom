package com.zh.tcr.model.entity.vod;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zh.tcr.model.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "Subject")
@TableName("subject")
public class Subject extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "类别名称")
	@TableField("title")
	private String title;

	@ApiModelProperty(value = "父ID")
	@TableField("parent_id")
	private Long parentId;

	@ApiModelProperty(value = "排序字段")
	@TableField("sort")
	private Integer sort;

	@ApiModelProperty(value = "是否包含子节点")
	@TableField(exist = false)
	private boolean hasChildren;
}