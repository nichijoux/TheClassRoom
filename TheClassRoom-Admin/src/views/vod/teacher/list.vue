<template>
  <div class="app-container">
    <!--查询表单开始-->
    <el-card class="operate-container" shadow="never" align="center">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="名称">
          <el-input v-model="queryCondition.name" placeholder="讲师名" />
        </el-form-item>

        <el-form-item label="头衔">
          <el-select
            v-model="queryCondition.level"
            clearable
            placeholder="头衔"
          >
            <el-option value="1" label="高级讲师" />
            <el-option value="0" label="首席讲师" />
          </el-select>
        </el-form-item>

        <el-form-item label="入驻时间">
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

        <!-- 查询表单操作开始 -->
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
        <!-- 查询表单操作结束 -->
      </el-form>
    </el-card>
    <!-- 查询表单结束 -->

    <el-divider></el-divider>

    <!-- 工具按钮 -->
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets" style="margin-top: 5px"></i>
      <span style="margin-top: 5px">数据列表</span>

      <el-button
        type="success"
        icon="el-icon-edit"
        @click="addTeacherInfo()"
        style="margin-left: 10px; width: 130px"
        >添加</el-button
      >

      <el-button
        type="danger"
        icon="el-icon-delete"
        @click="batchDeleteTeacherInfo()"
        style="width: 130px"
        >批量删除</el-button
      >
    </el-card>

    <!-- 数据表格 -->
    <el-table
      :data="teacherList"
      border
      stripe
      @selection-change="handleSelectionChange"
    >
      <!-- 多选框开始 -->
      <el-table-column type="selection" align="center" />
      <!-- 多选框结束 -->

      <!-- 序号列开始 -->
      <el-table-column label="#" width="50" align="center">
        <template slot-scope="scope">
          {{ (index - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <!-- 序号列结束 -->

      <el-table-column prop="name" label="名称" width="80" align="center" />

      <!-- 头衔列开始 -->
      <el-table-column label="头衔" width="90" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.level === 1" type="success" size="medium"
            >高级讲师</el-tag
          >
          <el-tag v-if="scope.row.level === 0" size="medium">首席讲师</el-tag>
        </template>
      </el-table-column>
      <!-- 头衔列结束 -->

      <!-- 头像列开始 -->
      <el-table-column prop="avatar" label="头像" width="178" align="center">
        <!-- 动态加载url -->
        <!-- imageurl是一个数组 -->
        <template slot-scope="scope">
          <img
            :src="scope.row.avatar"
            width="160"
            height="160"
            class="el-image__inner el-image__preview"
          />
        </template>
      </el-table-column>
      <!-- 头像列结束 -->

      <!-- 简介列开始 -->
      <el-table-column prop="intro" label="简介" align="center" />
      <!-- 简介列结束 -->

      <!-- 排序列开始 -->
      <el-table-column prop="sort" label="排序" width="60" align="center" />
      <!-- 排序列结束 -->

      <!-- 入驻时间列开始 -->
      <el-table-column
        prop="joinDate"
        label="入驻时间"
        width="160"
        align="center"
      />
      <!-- 入驻时间列结束 -->

      <!-- 对讲师修改、删除开始 -->
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button type="primary" icon="el-icon-edit" size="medium" round
              >修改</el-button
            >
          </router-link>

          <el-button
            type="danger"
            icon="el-icon-delete"
            @click="deleteTeacherInfo(scope.row.id)"
            size="medium"
            round
            >删除</el-button
          >
        </template>
      </el-table-column>
      <!-- 对讲师修改、删除结束 -->
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="index"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      style="padding: 30px 0; text-align: center"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="changePageLimit"
      @current-change="changeCurrentIndex"
    />
  </div>
</template>

<script>
import teacherAPI from "@/api/vod/teacher";

export default {
  data() {
    return {
      teacherList: [], //讲师列表
      total: 0, //总记录数
      index: 1, //当前页
      limit: 10, //每页记录数
      dateRange: {}, //时间范围
      queryCondition: {}, //查询条件
      multipleSelection: [], //批量删除选中的讲师列表
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
  created() {
    // 页面渲染之前执行
    this.fetchData();
  },
  methods: {
    // 获取页面
    fetchData(index = 1) {
      if (this.dateRange.length > 0) {
        this.queryCondition.joinDateBegin = this.dateRange[0];
        this.queryCondition.joinDateEnd = this.dateRange[1];
      }
      this.index = index;
      teacherAPI
        .pageQueryTeacherInfo(this.index, this.limit, this.queryCondition)
        .then((response) => {
          this.total = response.data.total;
          this.teacherList = response.data.teachers;
        });
    },
    // 清除条件并重新显示数据
    resetData() {
      this.queryCondition = {};
      this.dateRange = {};
      this.fetchData();
    },
    // 改变每页显示记录数
    changePageLimit(limit) {
      this.limit = limit;
      this.fetchData();
    },
    // 改变页码数
    changeCurrentIndex(index) {
      this.index = index;
      this.fetchData();
    },
    //复选框发生变化，调用方法，选中复选框行内容传递
    handleSelectionChange(selection) {
      this.multipleSelection = selection;
    },
    // 跳转到添加表单页面
    addTeacherInfo() {
      this.$router.push({ path: "/vod/teacher/save" });
    },
    // 讲师删除
    deleteTeacherInfo(id) {
      this.$confirm("此操作将删除该讲师信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        // 调用接口删除
        teacherAPI.deleteTeacherInfo(id).then((response) => {
          // 提示
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          // 刷新
          this.fetchData();
        });
      });
    },
    //批量删除讲师信息
    batchDeleteTeacherInfo() {
      //判断非空
      if (this.multipleSelection.length === 0) {
        this.$message.warning("请选择要删除的记录！");
        return;
      }
      this.$confirm("此操作将删除该讲师信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        var idList = [];
        // [1,2,3]
        // 遍历数组
        for (var i = 0; i < this.multipleSelection.length; i++) {
          var obj = this.multipleSelection[i];
          var id = obj.id;
          //放到数组
          idList.push(id);
        }
        //调用接口删除
        teacherAPI.batchDeleteTeacherInfo(idList).then((response) => {
          //提示
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          //刷新
          this.fetchData();
        });
      });
    },
  },
};
</script>
