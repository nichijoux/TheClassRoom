package com.zh.tcr.live.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.common.client.CourseFeignClient;
import com.zh.tcr.common.client.UserInfoFeignClient;
import com.zh.tcr.common.exception.TCRException;
import com.zh.tcr.common.utils.DateUtil;
import com.zh.tcr.live.mapper.LiveCourseMapper;
import com.zh.tcr.live.mtcloud.CommonResult;
import com.zh.tcr.live.mtcloud.MTCloud;
import com.zh.tcr.live.service.*;
import com.zh.tcr.model.entity.live.*;
import com.zh.tcr.model.entity.user.UserInfo;
import com.zh.tcr.model.entity.vod.Teacher;
import com.zh.tcr.model.vo.live.LiveCourseConfigVO;
import com.zh.tcr.model.vo.live.LiveCourseFormVO;
import com.zh.tcr.model.vo.live.LiveCourseGoodsView;
import com.zh.tcr.model.vo.live.LiveCourseVO;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>
 * 直播课程表 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-13
 */
@Service
public class LiveCourseServiceImpl extends ServiceImpl<LiveCourseMapper, LiveCourse> implements LiveCourseService {
    private CourseFeignClient courseFeignClient;

    private UserInfoFeignClient userInfoFeignClient;

    private LiveCourseDescriptionService liveCourseDescriptionService;

    private LiveCourseAccountService liveCourseAccountService;

    private LiveCourseConfigService liveCourseConfigService;

    private LiveCourseGoodsService liveCourseGoodsService;

    private MTCloud mtCloud;

    @Autowired
    public void setCourseFeignClient(CourseFeignClient courseFeignClient) {
        this.courseFeignClient = courseFeignClient;
    }

    @Autowired
    public void setUserInfoFeignClient(UserInfoFeignClient userInfoFeignClient) {
        this.userInfoFeignClient = userInfoFeignClient;
    }

    @Autowired
    public void setLiveCourseAccountService(LiveCourseAccountService liveCourseAccountService) {
        this.liveCourseAccountService = liveCourseAccountService;
    }

    @Autowired
    public void setLiveCourseDescriptionService(LiveCourseDescriptionService liveCourseDescriptionService) {
        this.liveCourseDescriptionService = liveCourseDescriptionService;
    }

    @Autowired
    public void setLiveCourseConfigService(LiveCourseConfigService liveCourseConfigService) {
        this.liveCourseConfigService = liveCourseConfigService;
    }

    @Autowired
    public void setLiveCourseGoodsService(LiveCourseGoodsService liveCourseGoodsService) {
        this.liveCourseGoodsService = liveCourseGoodsService;
    }

    @Autowired
    public void setMtCloud(MTCloud mtCloud) {
        this.mtCloud = mtCloud;
    }

    // 分页查询直播列表
    @Override
    public IPage<LiveCourse> pageQueryLiveInfo(Page<LiveCourse> livePage) {
        IPage<LiveCourse> pageModel = baseMapper.selectPage(livePage, null);
        // 遍历直播集合,获取讲师信息
        pageModel.getRecords().forEach(item -> {
            Long teacherId = item.getTeacherId();
            Teacher teacher = courseFeignClient.remoteGetTeacherInfo(teacherId);
            item.getParam().put("teacherName", teacher.getName());
            item.getParam().put("teacherLevel", teacher.getLevel());
        });
        return pageModel;
    }

    // 添加直播课程
    @Override
    public void addLiveCourse(LiveCourseFormVO liveCourseFormVO) {
        //LiveCourseFormVo -- LiveCourse
        LiveCourse liveCourse = new LiveCourse();
        BeanUtils.copyProperties(liveCourseFormVO, liveCourse);
        //获取讲师信息
        Teacher teacher =
                courseFeignClient.remoteGetTeacherInfo(liveCourseFormVO.getTeacherId());
        //调用方法添加直播课程
        //创建map集合，封装直播课程其他参数
        HashMap<Object, Object> options = new HashMap<>();
        options.put("scenes", 2);//直播类型。1: 教育直播，2: 生活直播。默认 1，说明：根据平台开通的直播类型填写
        options.put("password", liveCourseFormVO.getPassword());
//       course_name 课程名称
//       account 发起直播课程的主播账号
//       start_time 课程开始时间,格式: 2015-01-10 12:00:00
//       end_time 课程结束时间,格式: 2015-01-10 13:00:00
//       nickname  昵称
//       accountIntro 主播介绍
//       options 其他参数
        try {
            // 获取返回结果
            String res = mtCloud.courseAdd(liveCourse.getCourseName(),
                    teacher.getId().toString(),
                    new DateTime(liveCourse.getStartTime()).toString("yyyy-MM-dd HH:mm:ss"),
                    new DateTime(liveCourse.getEndTime()).toString("yyyy-MM-dd HH:mm:ss"),
                    teacher.getName(),
                    teacher.getIntro(),
                    options);
            //把创建之后返回结果判断
            CommonResult<JSONObject> commonResult = JSON.parseObject(res, CommonResult.class);
            if (Integer.parseInt(commonResult.getCode()) == MTCloud.CODE_SUCCESS) {
                //成功添加直播

                //添加直播基本信息
                JSONObject object = commonResult.getData();
                Long course_id = object.getLong("course_id");//直播课程id
                liveCourse.setCourseId(course_id);
                baseMapper.insert(liveCourse);

                //添加直播描述信息
                LiveCourseDescription liveCourseDescription = new LiveCourseDescription();
                liveCourseDescription.setDescription(liveCourseFormVO.getDescription());
                liveCourseDescription.setLiveCourseId(liveCourse.getId());
                liveCourseDescriptionService.save(liveCourseDescription);

                //添加直播账号信息
                LiveCourseAccount liveCourseAccount = new LiveCourseAccount();
                liveCourseAccount.setLiveCourseId(liveCourse.getId());
                liveCourseAccount.setZhuboAccount(object.getString("bid"));
                liveCourseAccount.setZhuboPassword(liveCourseFormVO.getPassword());
                liveCourseAccount.setAdminKey(object.getString("admin_key"));
                liveCourseAccount.setUserKey(object.getString("user_key"));
                liveCourseAccount.setZhuboKey(object.getString("zhubo_key"));
                liveCourseAccountService.save(liveCourseAccount);
            } else {
                System.out.println(commonResult.getmsg());
                throw new TCRException(20001, "直播创建失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除直播课程
    @Override
    public void deleteLiveCourse(Long id) {
        //根据id查询直播课程信息
        LiveCourse liveCourse = baseMapper.selectById(id);
        if (liveCourse != null) {
            //获取直播courseId
            Long courseId = liveCourse.getCourseId();
            try {
                //调用方法删除平台直播课程
                mtCloud.courseDelete(courseId.toString());
                //删除表数据
                baseMapper.deleteById(id);
            } catch (Exception e) {
                e.printStackTrace();
                throw new TCRException(20001, "删除直播课程失败");
            }
        }
    }

    // 更新直播课程信息
    @Override
    public void updateLiveCourse(LiveCourseFormVO liveCourseFormVO) {
//根据id获取直播课程基本信息
        LiveCourse liveCourse = baseMapper.selectById(liveCourseFormVO.getId());
        BeanUtils.copyProperties(liveCourseFormVO, liveCourse);
        //讲师
        Teacher teacher =
                courseFeignClient.remoteGetTeacherInfo(liveCourseFormVO.getTeacherId());

        //  course_id 课程ID
        //  account 发起直播课程的主播账号
        //  course_name 课程名称
        //  start_time 课程开始时间,格式:2015-01-01 12:00:00
        //  end_time 课程结束时间,格式:2015-01-01 13:00:00
        //  nickname 	主播的昵称
        //  accountIntro 	主播的简介
        //  options 		可选参数
        HashMap<Object, Object> options = new HashMap<>();
        try {
            String res = mtCloud.courseUpdate(liveCourse.getCourseId().toString(),
                    teacher.getId().toString(),
                    liveCourse.getCourseName(),
                    new DateTime(liveCourse.getStartTime()).toString("yyyy-MM-dd HH:mm:ss"),
                    new DateTime(liveCourse.getEndTime()).toString("yyyy-MM-dd HH:mm:ss"),
                    teacher.getName(),
                    teacher.getIntro(),
                    options);
            //返回结果转换，判断是否成功
            CommonResult<JSONObject> commonResult = JSON.parseObject(res, CommonResult.class);
            if (Integer.parseInt(commonResult.getCode()) == MTCloud.CODE_SUCCESS) {
                JSONObject object = commonResult.getData();
                //更新直播课程基本信息
                liveCourse.setCourseId(object.getLong("course_id"));
                baseMapper.updateById(liveCourse);
                //直播课程描述信息更新
                LiveCourseDescription liveCourseDescription =
                        liveCourseDescriptionService.getLiveCourseById(liveCourse.getId());
                liveCourseDescription.setDescription(liveCourseFormVO.getDescription());
                liveCourseDescriptionService.updateById(liveCourseDescription);
            } else {
                throw new TCRException(20001, "修改直播课程失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取直播课程信息和描述信息
    @Override
    public LiveCourseFormVO getLiveCourseVO(Long id) {
        //获取直播课程基本信息
        LiveCourse liveCourse = baseMapper.selectById(id);
        //获取直播课程描述信息
        LiveCourseDescription liveCourseDescription = liveCourseDescriptionService.getLiveCourseById(id);
        //封装
        LiveCourseFormVO liveCourseFormVO = new LiveCourseFormVO();
        BeanUtils.copyProperties(liveCourse, liveCourseFormVO);
        liveCourseFormVO.setDescription(liveCourseDescription.getDescription());
        return liveCourseFormVO;
    }

    // 获取最近的直播
    @Override
    public List<LiveCourseVO> getLatelyLiveList() {
        List<LiveCourseVO> liveCourseVOList = baseMapper.getLatelyLiveList();
        for (LiveCourseVO liveCourseVO : liveCourseVOList) {
            //封装开始和结束时间
            liveCourseVO.setStartTimeString(new DateTime(liveCourseVO.getStartTime()).toString("yyyy年MM月dd HH:mm"));
            liveCourseVO.setEndTimeString(new DateTime(liveCourseVO.getEndTime()).toString("HH:mm"));
            //封装讲师
            Long teacherId = liveCourseVO.getTeacherId();
            Teacher teacher = courseFeignClient.remoteGetTeacherInfo(teacherId);
            liveCourseVO.setTeacher(teacher);

            //封装直播状态
            liveCourseVO.setLiveStatus(this.getLiveStatus(liveCourseVO));
        }
        return liveCourseVOList;
    }

    // 修改配置
    @Override
    public void updateLiveConfig(LiveCourseConfigVO liveCourseConfigVO) {
        //1 修改直播配置表
        LiveCourseConfig liveCourseConfig = new LiveCourseConfig();
        BeanUtils.copyProperties(liveCourseConfigVO, liveCourseConfig);
        if (liveCourseConfigVO.getId() == null) {
            liveCourseConfigService.save(liveCourseConfig);
        } else {
            liveCourseConfigService.updateById(liveCourseConfig);
        }

        //2 修改直播商品表
        //根据课程id删除直播商品列表
        LambdaQueryWrapper<LiveCourseGoods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LiveCourseGoods::getLiveCourseId, liveCourseConfigVO.getLiveCourseId());
        liveCourseGoodsService.remove(wrapper);
        //添加商品列表
        if (!CollectionUtils.isEmpty(liveCourseConfigVO.getLiveCourseGoodsList())) {
            liveCourseGoodsService.saveBatch(liveCourseConfigVO.getLiveCourseGoodsList());
        }

        //3 修改在直播平台
        this.updateLifeConfig(liveCourseConfigVO);
    }

    // 根据课程id获取直播账号信息
    @Override
    public LiveCourseAccount getLiveCourseAccountByCourseId(Long id) {
        return liveCourseAccountService.getLiveCourseAccountByCourseId(id);
    }

    // 获取直播配置信息
    @Override
    public LiveCourseConfigVO getLiveCourseConfig(Long id) {
        //根据课程id查询配置信息
        LiveCourseConfig liveCourseConfig = liveCourseConfigService.getCourseConfigByCourseId(id);
        //封装LiveCourseConfigVo
        LiveCourseConfigVO liveCourseConfigVO = new LiveCourseConfigVO();
        if (liveCourseConfig != null) {
            //查询直播课程商品列表
            List<LiveCourseGoods> liveCourseGoodslist = liveCourseGoodsService.getGoodsListByCourseId(id);
            //封装到liveCourseConfigVo里面
            BeanUtils.copyProperties(liveCourseConfig, liveCourseConfigVO);
            //封装商品列表
            liveCourseConfigVO.setLiveCourseGoodsList(liveCourseGoodslist);
        }
        return liveCourseConfigVO;
    }

    // 批量删除直播课程
    @Override
    public void batchDeleteLiveCourse(List<Long> ids) {
        for (Long id : ids) {
            deleteLiveCourse(id);
        }
    }

    // 获取用户accessToken
    @Override
    public JSONObject getAccessToken(Long id, Long userId) {
        //根据课程id获取直播课程信息
        LiveCourse liveCourse = baseMapper.selectById(id);
        //根据用户id获取用户信息
        UserInfo userInfo = userInfoFeignClient.remoteGetUserInfo(userId);
        //封装需要参数
        HashMap<Object, Object> options = new HashMap<>();
        /*
         *  course_id      课程ID
         *  uid            用户唯一ID
         *  nickname       用户昵称
         *  role           用户角色，枚举见:ROLE 定义
         *  expire         有效期,默认:3600(单位:秒)
         *  options        可选项，包括:gender:枚举见上面GENDER定义,avatar:头像地址
         */
        try {
            String res = mtCloud.courseAccess(liveCourse.getCourseId().toString(),
                    userId.toString(),
                    userInfo.getNickName(),
                    MTCloud.ROLE_USER,
                    3600,
                    options);
            CommonResult<JSONObject> commonResult = JSON.parseObject(res, CommonResult.class);
            if (Integer.parseInt(commonResult.getCode()) == MTCloud.CODE_SUCCESS) {
                JSONObject object = commonResult.getData();
                System.out.println("access::" + object.getString("access_token"));
                return object;
            } else {
                throw new TCRException(20001, "获取失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 用户获取直播课程信息
    @Override
    public Map<String, Object> userGetLiveCourseInfo(Long courseId) {
        LiveCourse liveCourse = this.getById(courseId);
        liveCourse.getParam().put("startTimeString", new DateTime(liveCourse.getStartTime()).toString("yyyy年MM月dd HH:mm"));
        liveCourse.getParam().put("endTimeString", new DateTime(liveCourse.getEndTime()).toString("yyyy年MM月dd HH:mm"));
        Teacher teacher = courseFeignClient.remoteGetTeacherInfo(liveCourse.getTeacherId());
        LiveCourseDescription liveCourseDescription = liveCourseDescriptionService.getLiveCourseById(courseId);

        Map<String, Object> map = new HashMap<>();
        map.put("liveCourse", liveCourse);
        map.put("liveStatus", this.getLiveStatus(liveCourse));
        map.put("teacher", teacher);
        if (null != liveCourseDescription) {
            map.put("description", liveCourseDescription.getDescription());
        } else {
            map.put("description", "");
        }
        return map;
    }

    //修改在直播平台
    private void updateLifeConfig(LiveCourseConfigVO liveCourseConfigVO) {
        LiveCourse liveCourse =
                baseMapper.selectById(liveCourseConfigVO.getLiveCourseId());
        //封装平台方法需要参数
        //参数设置
        HashMap<Object, Object> options = new HashMap<>();
        //界面模式
        options.put("pageViewMode", liveCourseConfigVO.getPageViewMode());
        //观看人数开关
        JSONObject number = new JSONObject();
        number.put("enable", liveCourseConfigVO.getNumberEnable());
        options.put("number", number.toJSONString());
        //观看人数开关
        JSONObject store = new JSONObject();
        number.put("enable", liveCourseConfigVO.getStoreEnable());
        number.put("type", liveCourseConfigVO.getStoreType());
        options.put("store", number.toJSONString());
        //商城列表
        List<LiveCourseGoods> liveCourseGoodsList = liveCourseConfigVO.getLiveCourseGoodsList();
        if (!CollectionUtils.isEmpty(liveCourseGoodsList)) {
            List<LiveCourseGoodsView> liveCourseGoodsViewList = new ArrayList<>();
            for (LiveCourseGoods liveCourseGoods : liveCourseGoodsList) {
                LiveCourseGoodsView liveCourseGoodsView = new LiveCourseGoodsView();
                BeanUtils.copyProperties(liveCourseGoods, liveCourseGoodsView);
                liveCourseGoodsViewList.add(liveCourseGoodsView);
            }
            JSONObject goodsListEdit = new JSONObject();
            goodsListEdit.put("status", "0");
            options.put("goodsListEdit ", goodsListEdit.toJSONString());
            options.put("goodsList", JSON.toJSONString(liveCourseGoodsViewList));
        }
        try {
            String res = mtCloud.courseUpdateLifeConfig(liveCourse.getCourseId().toString(), options);
            CommonResult<JSONObject> commonResult = JSON.parseObject(res, CommonResult.class);
            if (Integer.parseInt(commonResult.getCode()) != MTCloud.CODE_SUCCESS) {
                throw new TCRException(20001, "修改配置信息失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TCRException(20001, "修改配置信息失败");
        }
    }


    // 直播状态 0：未开始 1：直播中 2：直播结束
    private int getLiveStatus(LiveCourse liveCourse) {
        // 直播状态 0：未开始 1：直播中 2：直播结束
        int liveStatus = 0;
        Date curTime = new Date();
        if (DateUtil.dateCompare(curTime, liveCourse.getStartTime())) {
            liveStatus = 0;
        } else if (DateUtil.dateCompare(curTime, liveCourse.getEndTime())) {
            liveStatus = 1;
        } else {
            liveStatus = 2;
        }
        return liveStatus;
    }
}
