<template>
  <div class="app-container">
    <!-- 课程信息表单 -->
    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input
          v-model="courseInfo.title"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"
        />
      </el-form-item>
      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          />
        </el-select>
      </el-form-item>
      <!-- 所属分类：级联下拉列表 -->
      <el-form-item label="课程类别">
        <!-- 一级分类 -->
        <el-select
          v-model="courseInfo.subjectParentId"
          placeholder="请选择"
          @change="subjectChanged"
        >
          <el-option
            v-for="subject in subjectList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
        <!-- 二级分类 -->
        <el-select v-model="courseInfo.subjectId" placeholder="请选择">
          <el-option
            v-for="subject in subjectLevelTwoList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="总课时">
        <el-input-number
          :min="0"
          v-model="courseInfo.lessonNum"
          controls-position="right"
          placeholder="请填写课程的总课时数"
        />
      </el-form-item>
      <!-- 课程简介-->
      <el-form-item label="课程简介">
        <el-input v-model="courseInfo.description" type="textarea" rows="5" />
      </el-form-item>
      <!-- 课程封面 -->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleCoverSuccess"
          :before-upload="beforeCoverUpload"
          :on-error="handleCoverError"
          :action="BASE_API + '/service_vod/file/uploadAvator'"
          class="cover-uploader"
        >
          <img v-if="courseInfo.cover" :src="courseInfo.cover" />
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-form-item>

      <el-form-item label="课程价格">
        <el-input-number
          :min="0"
          v-model="courseInfo.price"
          controls-position="right"
          placeholder="免费课程请设置为0元"
        />
        元
      </el-form-item>
    </el-form>

    <!-- 保存按钮开始 -->
    <div style="text-align: center">
      <el-button
        :disabled="saveBtnDisabled"
        type="primary"
        @click="saveAndNext()"
        >保存并下一步</el-button
      >
    </div>
    <!-- 保存按钮结束 -->
  </div>
</template>

<script>
import courseAPI from "@/api/vod/course";
import teacherAPI from "@/api/vod/teacher";
import subjectAPI from "@/api/vod/subject";

export default {
  data() {
    return {
      BASE_API: "http://localhost:8500",
      saveBtnDisabled: false, // 按钮是否禁用
      courseInfo: {
        // 表单数据
        price: 0,
        lessonNum: 0,
        // 以下解决表单数据不全时insert语句非空校验
        teacherId: "",
        subjectId: "",
        subjectParentId: "",
        cover: "",
        description: "",
      },
      teacherList: [], // 讲师列表
      subjectList: [], // 一级分类列表
      subjectLevelTwoList: [], // 二级分类列表
    };
  },
  created() {
    if (this.$parent.courseId) {
      // 回显
      this.fetchCourseInfoById(this.$parent.courseId);
    } else {
      // 新增
      // 初始化分类列表
      this.initSubjectList();
    }
    // 获取讲师列表
    this.initTeacherList();
  },
  methods: {
    // 获取课程信息
    fetchCourseInfoById(id) {
      courseAPI.getCourseInfo(id).then((response) => {
        this.courseInfo = response.data.course;
        // 初始化分类列表
        subjectAPI.getChildSubject(0).then((response) => {
          this.subjectList = response.data.list;
          // 填充二级菜单：遍历一级菜单列表，
          this.subjectList.forEach((subject) => {
            // 找到和courseInfo.subjectParentId一致的父类别记录
            if (subject.id === this.courseInfo.subjectParentId) {
              // 拿到当前类别下的子类别列表，将子类别列表填入二级下拉菜单列表
              subjectAPI.getChildList(subject.id).then((response) => {
                this.subjectLevelTwoList = response.data.list;
              });
            }
          });
        });
      });
    },
    // 获取讲师列表
    initTeacherList() {
      teacherAPI.getAllTeacherInfo().then((response) => {
        this.teacherList = response.data.teachers;
      });
    },

    // 初始化分类列表
    initSubjectList() {
      subjectAPI.getChildSubject(0).then((response) => {
        this.subjectList = response.data.list;
      });
    },

    // 选择一级分类，切换二级分类
    subjectChanged(value) {
      subjectAPI.getChildSubject(value).then((response) => {
        this.courseInfo.subjectId = "";
        this.subjectLevelTwoList = response.data.list;
      });
    },

    // 上传成功回调
    handleCoverSuccess(res, file) {
      this.courseInfo.cover = res.data.cover;
    },

    // 上传校验
    beforeCoverUpload(file) {
      const fileType = file.type;
      const isImage =
        [
          "image/png",
          "image/jpg",
          "image/jpeg",
          "image/bmp",
          "image/gif",
          "image/webp",
          "image/psd",
          "image/svg",
          "image/tiff",
        ].indexOf(fileType.toLowerCase()) !== -1;

      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isImage) {
        this.$message.error("上传文件应为图片格式");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isImage && isLt2M;
    },

    // 错误处理
    handleCoverError() {
      console.log("error");
      this.$message.error("上传失败");
    },

    // 保存并下一步
    saveAndNext() {
      this.saveBtnDisabled = true;
      if (!this.$parent.courseId) {
        this.saveData();
      } else {
        this.updateData();
      }
      this.saveBtnDisabled = false;
    },
    // 修改
    updateData() {
      courseAPI.updateCourseInfo(this.courseInfo).then((response) => {
        if (response.success) {
          this.$message.success(response.message);
          this.$parent.courseId = response.data.courseId; // 获取courseId
          this.$parent.active = 1; // 下一步
        } else {
          this.$message.warning(response.message);
        }
      });
    },

    // 添加课程
    saveData() {
      courseAPI.addCourseInfo(this.courseInfo).then((response) => {
        if (response.success) {
          this.$message.success(response.message);
          this.$parent.courseId = response.data.courseId; // 获取courseId
          this.$parent.active = 1; // 下一步
        } else {
          this.$message.warning(response.message);
        }
      });
    },
  },
};
</script>
<style scoped>
.tinymce-container {
  position: relative;
  line-height: normal;
}
.cover-uploader .avatar-uploader-icon {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;

  font-size: 28px;
  color: #8c939d;
  width: 640px;
  height: 357px;
  line-height: 357px;
  text-align: center;
}
.cover-uploader .avatar-uploader-icon:hover {
  border-color: #409eff;
}
.cover-uploader img {
  width: 640px;
  height: 357px;
  display: block;
}
</style>
