package com.zh.tcr.live.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.entity.live.LiveCourseGoods;

import java.util.List;

/**
 * <p>
 * 直播课程关联推荐表 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-13
 */
public interface LiveCourseGoodsService extends IService<LiveCourseGoods> {

    // 根据课程id获取商品列表
    List<LiveCourseGoods> getGoodsListByCourseId(Long id);
}
