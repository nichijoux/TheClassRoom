<template>
  <div class="app-container">
    <el-card class="operate-container" shadow="never" align="center">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-input v-model="queryCondition.outTradeNo" placeholder="订单号" />
        </el-form-item>
        <el-form-item>
          <el-input v-model="queryCondition.phone" placeholder="手机" />
        </el-form-item>
        <!-- 订单状态开始 -->
        <el-form-item>
          <el-select
            v-model="queryCondition.orderStatus"
            placeholder="订单状态"
            class="v-select patient-select"
          >
            <el-option
              v-for="item in statusList"
              :key="item.status"
              :label="item.name"
              :value="item.status"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 订单状态结束 -->

        <el-form-item label="订单创建时间">
          <el-date-picker
            v-model="dateRange"
            type="datetimerange"
            align="right"
            unlink-panels
            range-separator="至"
            start-placeholder="开始月份"
            end-placeholder="结束月份"
            :picker-options="pickerOptions"
            clearable
          >
          </el-date-picker>
        </el-form-item>

        <!-- 查询清空按钮开始 -->
        <div>
          <el-button type="primary" icon="el-icon-search" @click="fetchData()"
            >查询</el-button
          >
          <el-button
            type="warning"
            icon="el-icon-refresh-left"
            @click="resetData()"
            >清空</el-button
          >
        </div>
        <!-- 查询清空按钮结束 -->
      </el-form>
    </el-card>
    <!-- 列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" width="60" align="center">
        <template slot-scope="scope">
          {{ (index - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column
        prop="outTradeNo"
        label="订单号"
        width="160"
        align="center"
      />
      <el-table-column
        prop="courseName"
        label="课程名称"
        width="160"
        align="center"
      >
        <template slot-scope="scope">
          {{ scope.row.param.courseName }}
        </template>
      </el-table-column>
      <el-table-column
        prop="finalAmount"
        label="订单金额"
        width="90"
        align="center"
      />
      <el-table-column prop="nickName" label="下单用户" align="center" />
      <el-table-column prop="phone" label="用户手机" align="center" />
      <el-table-column
        prop="payTime"
        label="支付时间"
        width="156"
        align="center"
      />
      <el-table-column prop="orderStatus" label="订单状态" align="center">
        <template slot-scope="scope">
          {{ scope.row.orderStatus == 0 ? "未支付" : "已支付" }}
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="下单时间"
        width="156"
        align="center"
      />
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="index"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      style="padding: 30px 0; text-align: center"
      layout="sizes, prev, pager, next, jumper, ->, total, slot"
      @current-change="changeCurrentIndex"
      @size-change="changePageLimit"
    />
  </div>
</template>

<script>
import orderInfoAPI from "@/api/order/orderinfo";

export default {
  data() {
    return {
      listLoading: true, // 数据是否正在加载
      list: null, // 订单列表
      total: 0, // 数据库中的总记录数
      index: 1, // 默认页码
      limit: 10, // 每页记录数
      dateRange: {}, // 查询对象中的日期对象
      queryCondition: {}, // 查询表单对象
      statusList: [
        {
          status: 0,
          name: "未支付",
        },
        {
          status: 1,
          name: "已支付",
        },
      ],
      // 默认选取日期
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
    };
  },
  // 生命周期函数：内存准备完毕，页面尚未渲染
  created() {
    this.fetchData();
  },
  methods: {
    // 当页码发生改变的时候
    changePageLimit(limit) {
      this.limit = limit;
      this.fetchData();
    },
    // 改变页码数
    changeCurrentIndex(index) {
      this.index = index;
      this.fetchData();
    },
    // 加载banner列表数据
    fetchData(index = 1) {
      // 异步获取远程数据（ajax）
      this.index = index;
      if (this.dateRange.length > 0) {
        this.queryCondition.createTimeBegin = this.dateRange[0];
        this.queryCondition.createTimeEnd = this.dateRange[1];
      }
      orderInfoAPI
        .pageQueryOrderInfo(this.index, this.limit, this.queryCondition)
        .then((response) => {
          this.list = response.data.orderInfos;
          this.total = response.data.total;
          // 数据加载并绑定成功
          this.listLoading = false;
        });
    },
    // 重置查询表单
    resetData() {
      this.queryCondition = {};
      this.dateRange = {};
      this.fetchData();
    },
  },
};
</script>
