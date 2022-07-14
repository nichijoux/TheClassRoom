import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },
  // 讲师管理
  {
    path: '/teacher',
    component: Layout,
    redirect: '/teacher/list',
    name: '讲师管理',
    meta: { title: '讲师管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'list',
        name: 'teacherList',
        component: () => import('@/views/vod/teacher/list'),
        meta: { title: '讲师列表', icon: 'table' }
      },
      {
        path: 'save',
        name: 'teacherSave',
        component: () => import('@/views/vod/teacher/form'),
        meta: { title: '添加讲师', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: 'teacherEdit',
        component: () => import('@/views/vod/teacher/form'),
        meta: { title: '修改讲师信息', icon: 'tree' },
        hidden: true
      }
    ]
  },
  // 课程分类管理
  {
    path: '/subject',
    component: Layout,
    redirect: '/subject/index',
    name: '课程分类管理',
    meta: { title: '课程分类管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'index',
        name: 'subjectList',
        component: () => import('@/views/vod/subject/index'),
        meta: { title: '课程分类列表', icon: 'table' }
      },
    ]
  },
  // 课程管理
  {
    path: '/course',
    component: Layout,
    redirect: '/course/list',
    name: '课程管理',
    meta: { title: '课程管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'list',
        name: 'courseList',
        component: () => import('@/views/vod/course/list'),
        meta: { title: '课程列表', icon: 'table' }
      },
      {
        path: 'save',
        name: 'courseSave',
        component: () => import('@/views/vod/course/form'),
        meta: { title: '添加课程', icon: 'tree' }
      },
      {
        path: 'info/:id',
        name: 'courseInfoEdit',
        component: () => import('@/views/vod/course/form'),
        meta: { title: '编辑课程', icon: 'tree' },
        hidden: true
      },
      {
        path: 'chapter/:id',
        name: 'courseChapterEdit',
        component: () => import('@/views/vod/course/form'),
        meta: { title: '编辑大纲', icon: 'tree' },
        hidden: true
      },
      {
        path: 'chart/:id',
        name: 'courseChart',
        component: () => import('@/views/vod/course/chart'),
        meta: { title: '课程统计', icon: 'tree' },
        hidden: true
      },
    ]
  },
  // 订单管理
  {
    path: '/order',
    component: Layout,
    redirect: '/order/list',
    name: '订单管理',
    meta: { title: '订单管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'list',
        name: 'orderList',
        component: () => import('@/views/order/list'),
        meta: { title: '订单列表', icon: 'table' }
      },
    ]
  },
  // 营销管理
  {
    path: '/activity',
    component: Layout,
    redirect: '/couponInfo/list',
    name: '营销管理',
    meta: { title: '营销管理', icon: 'el-icon-football' },
    alwaysShow: true,
    children: [
      {
        path: 'couponInfo/list',
        name: 'CouponInfo',
        component: () => import('@/views/activity/couponInfo/list'),
        meta: { title: '优惠券列表' }
      },
      {
        path: 'couponInfo/add',
        name: 'CouponInfoAdd',
        component: () => import('@/views/activity/couponInfo/form'),
        meta: { title: '添加优惠卷' },
      },
      {
        path: 'couponInfo/edit/:id',
        name: 'CouponInfoEdit',
        component: () => import('@/views/activity/couponInfo/form'),
        meta: { title: '编辑优惠卷', noCache: true },
        hidden: true
      },
      {
        path: 'couponInfo/show/:id',
        name: 'CouponInfoShow',
        component: () => import('@/views/activity/couponInfo/show'),
        meta: { title: '详情', noCache: true },
        hidden: true
      }
    ]
  },
  // 微信公众号菜单管理
  {
    path: '/wechat',
    component: Layout,
    redirect: '/wechat/menu/list',
    name: 'Wechat',
    meta: {
      title: '公众号菜单管理',
      icon: 'el-icon-refrigerator'
    },
    alwaysShow: true,
    children: [
      {
        path: 'menu/list',
        name: 'Menu',
        component: () => import('@/views/wechat/menu/list'),
        meta: { title: '菜单列表' }
      }
    ]
  },
  // 直播管理
  {
    path: '/live',
    component: Layout,
    redirect: '/live/liveCourse/list',
    name: 'Live',
    meta: {
      title: '直播管理',
      icon: 'el-icon-bangzhu'
    },
    alwaysShow: true,
    children: [
      {
        path: 'liveCourse/list',
        name: 'liveCourseList',
        component: () => import('@/views/live/liveCourse/list'),
        meta: { title: '直播列表' }
      },
      {
        path: 'liveCourse/config/:id',
        name: 'liveCourseConfig',
        component: () => import('@/views/live/liveCourse/config'),
        meta: { title: '直播配置' },
        hidden: true
      },
      {
        path: 'liveVisitor/list/:id',
        name: 'liveVisitor',
        component: () => import('@/views/live/liveVisitor/list'),
        meta: { title: '观看记录' },
        hidden: true
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
