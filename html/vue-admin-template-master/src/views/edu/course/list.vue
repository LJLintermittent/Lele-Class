<template>
  <div class="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.title" placeholder="课程名称" />
      </el-form-item>

      <el-form-item>
        <el-select
          v-model="courseQuery.status"
          clearable
          placeholder="课程状态"
        >
          <el-option :value="'Normal'" label="已发布" />
          <el-option :value="'Draft'" label="未发布" />
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="courseQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="courseQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getListPage()"
        >查询</el-button
      >
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <el-table :data="list" border fit highlight-current-row>
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (current - 1) * size + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="title" label="课程名称" width="80" />

      <el-table-column prop="cover" label="课程封面" width="80">
        <template slot-scope="scope">
          <img :src="scope.row.cover" width="50" height="50" />
        </template>
      </el-table-column>

      <el-table-column label="课程状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === "Normal" ? "已发布" : "未发布" }}
        </template>
      </el-table-column>

      <el-table-column prop="lessonNum" label="课时数" width="80" />

      <el-table-column prop="price" label="课程价格/元" width="100" />

      <el-table-column prop="" label="课程简介" width="273" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160" />

      <el-table-column prop="gmtModified" label="更新时间" width="160" />

      <el-table-column label="操作" width="180" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/info/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >编辑课程信息</el-button
            >
          </router-link>

          <router-link :to="'/course/chapter/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >编辑课程大纲</el-button
            >
          </router-link>
        </template>
      </el-table-column>

      <el-table-column label="发布" width="85" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="publish(scope.row.id)"
            ><i class="el-icon-upload el-icon--right"></i
          ></el-button>
        </template>
      </el-table-column>

      <el-table-column label="删除" width="70" align="center">
        <template slot-scope="scope">
          <el-button
            type="danger"
            icon="el-icon-delete"
            circle
            @click="removeDataById(scope.row.id)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="current"
      :page-size="size"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="getListPage"
    />
  </div>
</template>

<script>
import course from "@/api/edu/course";
export default {
  data() {
    return {
      list: null, //查询后接口返回的集合
      current: 1, //默认第一页
      size: 4, //每页显示5条记录
      total: 0, //总记录数
      courseQuery: {},
      courseId: "",
    };
  },
  created() {
    this.getListPage();
  },
  methods: {
    publish(id) {
      this.$confirm("此操作将发布该课程，一经发布无法撤回, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          course.publishCourse(id).then((response) => {
            this.$message({
              type: "success",
              message: "发布成功!",
            });
            this.getListPage(this.current);
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消发布",
          });
        });
    },
    getListPage(current = 1) {
      this.current = current;
      course
        .getCourseListPage(this.current, this.size, this.courseQuery)
        .then((response) => {
          this.list = response.data.rows;
          this.total = response.data.total;
        });
    },
    removeDataById(id) {
      this.$confirm("此操作将永久删除该课程所有记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          //点击确定，执行then方法
          //调用删除的方法
          course.deleteCourse(id).then((response) => {
            //删除成功
            //提示信息
            this.$message({
              type: "success",
              message: "删除成功!",
            });
            //回到列表页面
            this.getListPage(this.current);
          });
        }) //点击取消，执行catch方法
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    resetData() {
      //表单输入项数据清空
      this.courseQuery = {};
      //查询所有课程数据
      this.getListPage();
    },
  },
};
</script>