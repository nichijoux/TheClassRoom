package com.zh.tcr.live.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.live.mapper.LiveCourseGoodsMapper;
import com.zh.tcr.live.service.LiveCourseGoodsService;
import com.zh.tcr.model.entity.live.LiveCourseGoods;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 直播课程关联推荐表 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-13
 */
@Service
public class LiveCourseGoodsServiceImpl extends ServiceImpl<LiveCourseGoodsMapper, LiveCourseGoods> implements LiveCourseGoodsService {
    // 根据课程id获取商品列表
    @Override
    public List<LiveCourseGoods> getGoodsListByCourseId(Long id) {
        return baseMapper.selectList(new LambdaQueryWrapper<LiveCourseGoods>()
                .eq(LiveCourseGoods::getLiveCourseId, id));
    }
}
