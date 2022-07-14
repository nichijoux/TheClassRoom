<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <!-- element-ui步骤开始 -->
    <el-steps
      :active="active"
      finish-status="success"
      simple
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="发布课程" />
    </el-steps>
    <!-- element-ui步骤结束 -->

    <!-- 填写课程基本信息 -->
    <BaseInfo v-if="active === 0" />
    <!-- 创建课程大纲 -->
    <Chapter v-if="active === 1" />
    <!-- 发布课程 -->
    <Publish v-if="active === 2 || active === 3" />
  </div>
</template>

<script>
// 引入子组件,分别为课程基本信息、课程章节以及发布课程
import BaseInfo from "@/views/vod/course/components/BaseInfo";
import Chapter from "@/views/vod/course/components/Chapter";
import Publish from "@/views/vod/course/components/Publish";

export default {
  components: { BaseInfo, Chapter, Publish }, // 注册子组件
  data() {
    return {
      active: 0,
      courseId: null,
    };
  },
  created() {
    // 获取路由id
    if (this.$route.params.id) {
      this.courseId = this.$route.params.id;
    }
    if (this.$route.name === "courseInfoEdit") {
      this.active = 0;
    }
    if (this.$route.name === "courseChapterEdit") {
      this.active = 1;
    }
  },
};
</script>
