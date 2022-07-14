package com.zh.tcr.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.entity.wechat.Menu;
import com.zh.tcr.model.vo.wechat.MenuVO;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
public interface MenuService extends IService<Menu> {
    // 获取所有菜单,并将一二级菜单进行封装
    List<MenuVO> getAllMenuInfo();

    // 同步微信公众号菜单
    void syncWechatMenu();

    // 获取所有一级菜单
    List<Menu> getOneMenuInfo();

    // 删除微信公众号菜单
    void deleteWechatMenu();
}
