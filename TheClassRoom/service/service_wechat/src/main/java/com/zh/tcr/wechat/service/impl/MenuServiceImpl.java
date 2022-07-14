package com.zh.tcr.wechat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.common.exception.TCRException;
import com.zh.tcr.model.entity.wechat.Menu;
import com.zh.tcr.model.vo.wechat.MenuVO;
import com.zh.tcr.wechat.mapper.MenuMapper;
import com.zh.tcr.wechat.service.MenuService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    private WxMpService wxMpService;

    @Autowired
    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    // 获取所有菜单,并将一二级菜单进行封装
    @Override
    public List<MenuVO> getAllMenuInfo() {
        // 获取所有菜单列表
        List<Menu> menuList = baseMapper.selectList(null);
        // 从所有菜单数据获取所有一级菜单数据（parent_id=0）
        List<Menu> oneMenuList = menuList.stream()
                .filter(menu -> menu.getParentId() == 0)
                .collect(Collectors.toList());
        List<Menu> twoMenuList = menuList.stream()
                .filter(menu -> menu.getParentId() != 0)
                .collect(Collectors.toList());

        List<MenuVO> finalMenuList = new ArrayList<>();
        // 将一级菜单封装到map中
        Map<Long, MenuVO> oneMenuMap = new HashMap<>();
        for (Menu menu : oneMenuList) {
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(menu, menuVO);
            menuVO.setChildren(new ArrayList<>());
            oneMenuMap.put(menu.getId(), menuVO);

            finalMenuList.add(menuVO);
        }
        for (Menu menu : twoMenuList) {
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(menu, menuVO);
            MenuVO oneMenu = oneMenuMap.get(menu.getParentId());
            oneMenu.getChildren().add(menuVO);
        }

        return finalMenuList;
    }

    // 同步菜单,将菜单数据转为微信公众号要求的json格式,其中一级菜单为button,子菜单为sub_button
    @Override
    public void syncWechatMenu() {
        // 获取所有菜单数据
        List<MenuVO> menuVOList = getAllMenuInfo();
        // 封装button结构
        JSONArray buttonList = new JSONArray();
        for (MenuVO oneMenuVO : menuVOList) {
            // json对象  一级菜单
            JSONObject one = new JSONObject();
            one.put("name", oneMenuVO.getName());
            // 如果一级菜单其实是个按钮
            if (oneMenuVO.getType() == null) {
                //json数组   二级菜单
                JSONArray subButton = new JSONArray();
                for (MenuVO twoMenuVO : oneMenuVO.getChildren()) {
                    JSONObject view = new JSONObject();
                    view.put("type", twoMenuVO.getType());
                    // TODO:部署到阿里云服务器
                    if (twoMenuVO.getType().equals("view")) {
                        view.put("name", twoMenuVO.getName());
                        view.put("url", "https://xnzn2g.i996.me/#"
                                + twoMenuVO.getUrl());
                    } else {
                        view.put("name", twoMenuVO.getName());
                        view.put("key", twoMenuVO.getMenuKey());
                    }
                    subButton.add(view);
                }
                one.put("sub_button", subButton);
            } else {
                one.put("type", oneMenuVO.getType());
                one.put("key", oneMenuVO.getMenuKey());
            }
            buttonList.add(one);
        }
        //封装最外层button部分
        JSONObject button = new JSONObject();
        button.put("button", buttonList);

        try {
            String menuId =
                    wxMpService.getMenuService().menuCreate(button.toJSONString());
            System.out.println("menuId" + menuId);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new TCRException(20001, "公众号菜单同步失败");
        }
    }

    // 获取所有一级菜单
    @Override
    public List<Menu> getOneMenuInfo() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        return baseMapper.selectList(wrapper);
    }

    // 删除微信公众号菜单
    @Override
    public void deleteWechatMenu() {
        try {
            wxMpService.getMenuService().menuDelete();
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new TCRException(20001, "公众号菜单删除失败");
        }
    }
}
