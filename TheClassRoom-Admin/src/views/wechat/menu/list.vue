<template>
  <div class="app-container">
    <!-- 工具条开始 -->
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets" style="margin-top: 5px"></i>
      <span style="margin-top: 5px">数据列表</span>

      <div align="center">
        <!-- 同步公众号菜单按钮开始 -->
        <el-button type="success" size="mini" @click="syncWechatMenu"
          >同步公众号菜单</el-button
        >
        <!-- 同步公众号菜单按钮结束 -->
        <!-- 删除公众号菜单按钮开始 -->
        <el-button
          type="danger"
          size="mini"
          @click="deleteWechatMenu"
          style="margin-left: 10px"
          >删除公众号菜单</el-button
        >
        <!-- 删除公众号菜单按钮结束 -->
        <!-- 添加菜单按钮开始 -->
        <el-button type="primary" size="mini" @click="addWechatMenu"
          >添加公众号菜单</el-button
        >
        <!-- 添加菜单按钮结束 -->
      </div>
    </el-card>
    <!-- 工具条结束 -->

    <el-table
      :data="list"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      default-expand-all
      :tree-props="{ children: 'children' }"
    >
      <el-table-column label="名称" prop="name" width="350"></el-table-column>
      <el-table-column label="类型" width="100" align="center">
        <template slot-scope="scope">
          {{
            scope.row.type == "view"
              ? "链接"
              : scope.row.type == "click"
              ? "事件"
              : ""
          }}
        </template>
      </el-table-column>
      <el-table-column label="菜单URL" prop="url"></el-table-column>
      <el-table-column
        label="菜单KEY"
        prop="menuKey"
        width="130"
      ></el-table-column>
      <el-table-column label="排序号" prop="sort" width="70"></el-table-column>
      <el-table-column label="操作" width="170" align="center">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.parentId > 0"
            type="text"
            size="mini"
            @click="edit(scope.row.id)"
            >修改</el-button
          >
          <el-button
            v-if="scope.row.parentId > 0"
            type="text"
            size="mini"
            @click="removeDataById(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%">
      <el-form
        ref="flashPromotionForm"
        label-width="150px"
        size="small"
        style="padding-right: 40px"
      >
        <el-form-item label="选择一级菜单">
          <el-select v-model="menu.parentId" placeholder="请选择">
            <el-option
              v-for="item in list"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item v-if="menu.parentId == 1" label="菜单名称">
          <el-select
            v-model="menu.name"
            placeholder="请选择"
            @change="liveCourseChanged"
          >
            <el-option
              v-for="item in liveCourseList"
              :key="item.id"
              :label="item.courseName"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item v-if="menu.parentId == 2" label="菜单名称">
          <el-select
            v-model="menu.name"
            placeholder="请选择"
            @change="subjectChanged"
          >
            <el-option
              v-for="item in subjectList"
              :key="item.id"
              :label="item.title"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item v-if="menu.parentId == 3" label="菜单名称">
          <el-input v-model="menu.name" />
        </el-form-item>
        <el-form-item label="菜单类型">
          <el-radio-group v-model="menu.type">
            <el-radio label="view">链接</el-radio>
            <el-radio label="click">事件</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="menu.type == 'view'" label="链接">
          <el-input v-model="menu.url" />
        </el-form-item>
        <el-form-item v-if="menu.type == 'click'" label="菜单KEY">
          <el-input v-model="menu.menuKey" />
        </el-form-item>
        <el-form-item label="排序">
          <el-input v-model="menu.sort" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate()" size="small"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>
<script>
import menuAPI from "@/api/wechat/menu";
//import liveCourseApi from '@/api/live/liveCourse'
import subjectApi from "@/api/vod/subject";

const defaultForm = {
  id: null,
  parentId: 1,
  name: "",
  nameId: null,
  sort: 1,
  type: "view",
  menuKey: "",
  url: "",
};

export default {
  // 定义数据
  data() {
    return {
      list: [],

      liveCourseList: [],
      subjectList: [],

      dialogVisible: false,
      menu: defaultForm,
      saveBtnDisabled: false,
    };
  },

  // 当页面加载时获取数据
  created() {
    this.fetchData();
    // this.fetchLiveCourse()
    this.fetchSubject();
  },

  methods: {
    // 调用api层获取数据库中的数据
    fetchData() {
      console.log("加载列表");
      menuAPI.getAllMenuInfo().then((response) => {
        this.list = response.data.menus;
        console.log(this.list);
      });
    },

    // fetchLiveCourse() {
    //   liveCourseApi.findLatelyList().then(response => {
    //     this.liveCourseList = response.data
    //     this.liveCourseList.push({'id': 0, 'courseName': '全部列表'})
    //   })
    // },

    fetchSubject() {
      console.log("加载列表");
      subjectApi.getChildSubject(0).then((response) => {
        this.subjectList = response.data.list;
      });
    },
    // 同步微信菜单
    syncWechatMenu() {
      this.$confirm("你确定上传菜单吗, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return menuAPI.syncWechatMenu();
        })
        .then((response) => {
          this.fetchData();
          this.$message.success(response.message);
        })
        .catch((error) => {
          console.log("error", error);
          // 当取消时会进入catch语句:error = 'cancel'
          // 当后端服务抛出异常时：error = 'error'
          if (error === "cancel") {
            this.$message.info("取消上传");
          }
        });
    },
    // 删除菜单
    deleteWechatMenu() {
      this.$confirm("你确定删除菜单吗, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return menuAPI.deleteWechatMenu();
        })
        .then((response) => {
          this.fetchData();
          this.$message.success(response.message);
        })
        .catch((error) => {
          console.log("error", error);
          // 当取消时会进入catch语句:error = 'cancel'
          // 当后端服务抛出异常时：error = 'error'
          if (error === "cancel") {
            this.$message.info("删除失败");
          }
        });
    },
    // 根据id删除数据
    removeDataById(id) {
      // debugger
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // promise
          // 点击确定，远程调用ajax
          return menuAPI.deleteMenuInfo(id);
        })
        .then((response) => {
          this.fetchData(this.page);
          if (response.code) {
            this.$message({
              type: "success",
              message: "删除成功!",
            });
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    addWechatMenu() {
      this.dialogVisible = true;
      this.menu = Object.assign({}, defaultForm);
    },

    edit(id) {
      this.dialogVisible = true;
      this.fetchDataById(id);
    },

    fetchDataById(id) {
      menuAPI.getMenuInfo(id).then((response) => {
        this.menu = response.data.menu;
      });
    },

    saveOrUpdate() {
      this.saveBtnDisabled = true; // 防止表单重复提交

      if (!this.menu.id) {
        this.saveData();
      } else {
        this.updateData();
      }
    },

    // 新增
    saveData() {
      menuAPI.addMenuInfo(this.menu).then((response) => {
        if (response.code) {
          this.$message({
            type: "success",
            message: response.message,
          });
          this.dialogVisible = false;
          this.fetchData(this.page);
        }
      });
    },

    // 根据id更新菜单
    updateData() {
      menuAPI.updateMenuInfo(this.menu).then((response) => {
        if (response.code) {
          this.$message({
            type: "success",
            message: response.message,
          });
          this.dialogVisible = false;
          this.fetchData(this.page);
        }
      });
    },

    // 根据id查询菜单
    fetchDataById(id) {
      menuAPI.getMenuInfo(id).then((response) => {
        this.menu = response.data.menu;
      });
    },

    subjectChanged(item) {
      console.info(item);
      this.menu.name = item.title;
      this.menu.url = "/course/" + item.id;
    },

    liveCourseChanged(item) {
      console.info(item);
      this.menu.name = item.courseName;
      if (item.id == 0) {
        this.menu.url = "/live";
      } else {
        this.menu.url = "/liveInfo/" + item.id;
      }
    },
  },
};
</script>
