package com.zh.tcr.live.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.live.mapper.LiveVisitorMapper;
import com.zh.tcr.live.service.LiveVisitorService;
import com.zh.tcr.model.entity.live.LiveVisitor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 直播来访者记录表 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-13
 */
@Service
public class LiveVisitorServiceImpl extends ServiceImpl<LiveVisitorMapper, LiveVisitor> implements LiveVisitorService {

}
