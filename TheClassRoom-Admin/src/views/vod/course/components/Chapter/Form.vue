<template>
  <!-- 添加和修改章节表单 -->
  <el-dialog :visible="dialogVisible" title="添加章节" @close="close()">
    <el-form :model="chapter" label-width="120px">
      <el-form-item label="章节标题">
        <el-input v-model="chapter.title" />
      </el-form-item>
      <el-form-item label="章节排序">
        <el-input-number v-model="chapter.sort" :min="0" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="saveOrUpdate()">确 定</el-button>
      <el-button type="dannger" @click="close()">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import chapterAPI from "@/api/vod/chapter";
export default {
  data() {
    return {
      // 是否可以看见本对话框
      dialogVisible: false,
      // 表单数据
      chapter: {
        sort: 0,
      },
    };
  },
  methods: {
    open(chapterId) {
      this.dialogVisible = true;
      if (chapterId) {
        chapterAPI.getChapterInfo(chapterId).then((response) => {
          this.chapter = response.data.chapter;
        });
      }
    },

    close() {
      this.dialogVisible = false;
      // 重置表单
      this.resetForm();
    },
    // 重置表单
    resetForm() {
      this.chapter = {
        sort: 0,
      };
    },
    // 保存或者更新
    saveOrUpdate() {
      if (!this.chapter.id) {
        this.save();
      } else {
        this.update();
      }
    },
    // 保存数据
    save() {
      // 获取课程id
      this.chapter.courseId = this.$parent.$parent.courseId;
      // 添加课程章节信息
      chapterAPI.addChapterInfo(this.chapter).then((response) => {
        this.$message.success(response.message);
        // 关闭组件
        this.close();
        // 刷新列表
        this.$parent.fetchNodeList();
      });
    },
    // 更新数据
    update() {
      chapterAPI.updateChapterInfo(this.chapter).then((response) => {
        this.$message.success(response.message);
        // 关闭组件
        this.close();
        // 刷新列表
        this.$parent.fetchNodeList();
      });
    },
  },
};
</script>
