<template>
  <div class="app-container">
    <el-descriptions
      class="margin-top"
      title="优惠卷信息"
      :column="2"
      :size="size"
      border
    >
      <el-descriptions-item label="优惠卷名称">
        {{ couponInfo.couponName }}
      </el-descriptions-item>

      <el-descriptions-item label="优惠卷类型">
        {{ couponInfo.couponType == "REGISTER" ? "注册卷" : "推荐赠送卷" }}
      </el-descriptions-item>

      <el-descriptions-item label="发行数量">
        {{ couponInfo.publishCount }}
      </el-descriptions-item>

      <el-descriptions-item label="每人限领次数">
        {{ couponInfo.perLimit }}
      </el-descriptions-item>

      <el-descriptions-item label="领取数量">
        {{ couponInfo.receiveCount }}
      </el-descriptions-item>

      <el-descriptions-item label="使用数量">
        {{ couponInfo.useCount }}
      </el-descriptions-item>

      <el-descriptions-item label="领取时间">
        {{ couponInfo.startTime }}至{{ couponInfo.endTime }}
      </el-descriptions-item>

      <el-descriptions-item label="过期时间">
        {{ couponInfo.expireTime }}
      </el-descriptions-item>

      <el-descriptions-item label="规则描述">
        {{ couponInfo.ruleDesc }}
      </el-descriptions-item>
    </el-descriptions>

    <h4>优惠券发放列表&nbsp;&nbsp;&nbsp;</h4>
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      border
      style="width: 100%; margin-top: 10px"
    >
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (index - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="param.nickName" label="用户昵称" align="center" />

      <el-table-column
        prop="param.phone"
        label="手机号"
        width="120"
        align="center"
      />
      <el-table-column label="使用状态" width="80" align="center">
        <template slot-scope="scope">
          {{ scope.row.couponStatus == "NOT_USED" ? "未使用" : "已使用" }}
        </template>
      </el-table-column>
      <el-table-column prop="getTime" label="获取时间" align="center" />
      <el-table-column prop="usingTime" label="使用时间" align="center" />
      <el-table-column prop="usedTime" label="支付时间" align="center" />
      <el-table-column prop="expireTime" label="过期时间" align="center" />
    </el-table>
    <!-- 分页组件 -->
    <el-pagination
      :current-page="index"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      style="padding: 30px 0; text-align: center"
      layout="sizes, prev, pager, next, jumper, ->, total, slot"
      @current-change="fetchData"
      @size-change="changePageLimit"
    />

    <div style="margin-top: 15px" align="center">
      <el-form label-width="0px">
        <el-form-item>
          <el-button @click="back">返回</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import couponInfoAPI from "@/api/activity/couponInfo";

export default {
  data() {
    return {
      listLoading: false, // 数据是否正在加载

      couponId: null,
      couponInfo: {},

      list: null, // 优惠卷列表
      total: 0, // 数据库中的总记录数
      index: 1, // 当前页码
      limit: 10, // 每页记录数
      searchObj: {}, // 查询表单对象
    };
  },

  // 监听器
  watch: {
    $route(to, from) {
      this.init();
    },
  },

  // 生命周期方法（在路由切换，组件不变的情况下不会被调用）
  created() {
    this.couponId = this.$route.params.id;
    // 获取优惠券信息
    this.fetchDataById();
    this.fetchData();
  },

  methods: {
    // 根据id查询记录
    fetchDataById() {
      couponInfoAPI.getCouponInfo(this.couponId).then((response) => {
        this.couponInfo = response.data.couponInfo;
      });
    },

    // 当页码发生改变的时候
    changePageLimit(limit) {
      this.limit = limit;
      this.fetchData();
    },

    // 加载优惠卷列表数据
    fetchData(index = 1) {
      // 异步获取远程数据（ajax）
      this.index = index;
      this.searchObj.couponId = this.couponId;
      couponInfoAPI
        .pageQueryCouponUseInfo(this.index, this.limit, this.searchObj)
        .then((response) => {
          this.list = response.data.couponUseInfos;
          this.total = response.data.total;

          // 数据加载并绑定成功
          this.listLoading = false;
        });
    },

    back() {
      this.$router.push({ path: "/activity/couponInfo/list" });
    },
  },
};
</script>
<style>
.app-container h4 {
  color: #606266;
}
</style>
