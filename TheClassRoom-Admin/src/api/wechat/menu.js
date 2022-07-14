import request from '@/utils/request'

const WECHAT_MENU_URL = '/service_wechat/menu'

export default {
    // 获取所有微信菜单项
    getAllMenuInfo() {
        return request({
            url: `${WECHAT_MENU_URL}/getAllMenuInfo`,
            method: 'GET'
        })
    },
    // 获取所有一级菜单
    getAllOneMenuInfo() {
        return request({
            url: `${WECHAT_MENU_URL}/getAllOneMenuInfo`,
            method: 'GET'
        })
    },
    // 根据id获取菜单
    getMenuInfo(id) {
        return request({
            url: `${WECHAT_MENU_URL}/getMenuInfo/${id}`,
            method: 'GET'
        })
    },
    // 增加菜单信息
    addMenuInfo(menu) {
        return request({
            url: `${WECHAT_MENU_URL}/addMenuInfo`,
            method: 'POST',
            data: menu
        })
    },
    // 更新菜单信息
    updateMenuInfo(menu) {
        return request({
            url: `${WECHAT_MENU_URL}/updateMenuInfo`,
            method: 'POST',
            data: menu
        })
    },
    // 根据id删除菜单信息
    deleteMenuInfo(id) {
        return request({
            url: `${WECHAT_MENU_URL}/deleteMenuInfo/${id}`,
            method: 'DELETE'
        })
    },
    // 同步微信公众号的菜单
    syncWechatMenu() {
        return request({
            url: `${WECHAT_MENU_URL}/syncWechatMenu`,
            method: 'GET'
        })
    },
    // 删除微信公众号的菜单
    deleteWechatMenu() {
        return request({
            url: `${WECHAT_MENU_URL}/deleteWechatMenu`,
            method: 'DELETE'
        })
    }
}