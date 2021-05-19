//查询所有题库
<template>

  <div class="exam">
    <div>

      <el-button size="small" style="display: inline-flex;margin-right: 10px;" type="primary" @click="exportmultiWord"  icon="el-icon-download"  plain>下载选择题模板</el-button>
      <el-button size="small" style="display: inline-flex;margin-right: 10px;" type="primary" @click="exportjufiWord"  icon="el-icon-download" plain>下载填空/判断题模板</el-button>
      <el-button size="small" style="display: inline-flex;margin-right: 10px;" type="primary" @click="exportjufiWord"  icon="el-icon-download" plain>下载简答题模板</el-button>

      <el-upload
        :show-file-list="false"
        :before-upload="beforeUpload"
        :on-success="onSuccess"
        :on-error="onError"
        :disabled="importDisabled"
        style="display: inline-flex;margin-right: 10px;"
        action="/api/importMulti">
        <el-button size="small" :disabled="importDisabled" type="success" :icon="importDataIcon">
          {{importDataTextMuilt}}
        </el-button>
      </el-upload>

      <el-upload
        :show-file-list="false"
        :before-upload="beforeUpload"
        :on-success="onSuccess"
        :on-error="onError"
        :disabled="importDisabled"
        style="display: inline-flex;margin-right: 10px;"
        action="/api/importJudge">
        <el-button size="small" :disabled="importDisabled" type="success" :icon="importDataIcon">
          {{importDataTextJudge}}
        </el-button>
      </el-upload>

      <el-upload
        :show-file-list="false"
        :before-upload="beforeUpload"
        :on-success="onSuccess"
        :on-error="onError"
        :disabled="importDisabled"
        style="display: inline-flex;margin-right: 10px;"
        action="/api/importFill">
        <el-button size="small" :disabled="importDisabled" type="success" :icon="importDataIcon">
          {{importDataTextFill}}
        </el-button>
      </el-upload>

      <el-upload
        :show-file-list="false"
        :before-upload="beforeUpload"
        :on-success="onSuccess"
        :on-error="onError"
        :disabled="importDisabled"
        style="display: inline-flex;margin-right: 10px;"
        action="/api/importFill">
        <el-button size="small" :disabled="importDisabled" type="success" :icon="importDataIcon">
          {{importDataTextAnswer}}
        </el-button>
      </el-upload>
    </div><br/>
    <el-table :data="pagination.records" border :row-class-name="tableRowClassName">
      <el-table-column fixed="left" prop="subject" label="试卷名称" width="180"></el-table-column>
      <el-table-column prop="question" label="题目信息" width="490"></el-table-column>
      <el-table-column prop="section" label="所属章节" width="200"></el-table-column>
      <el-table-column prop="typess" label="题目类型" width="200"></el-table-column>
      <el-table-column prop="score" label="试题分数" width="150"></el-table-column>
      <el-table-column prop="level" label="难度等级" width="133"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row.questionId,scope.row.typess)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteRecord(scope.row.questionId,scope.row.typess)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[6, 10]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total"
      class="page"
    ></el-pagination>

    <!-- 编辑对话框-->
    <el-dialog
      title="编辑题目信息"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="试卷名称">
            <el-input v-model="form.subject"></el-input>
          </el-form-item>
          <el-form-item label="题目信息">
            <el-input v-model="form.question"></el-input>
          </el-form-item>
          <el-form-item label="所属章节">
            <el-input v-model="form.section"></el-input>
          </el-form-item>
          <el-form-item label="试题分数">
            <el-input v-model="form.score"></el-input>
          </el-form-item>
          <el-form-item label="难度等级">
            <el-input v-model="form.level"></el-input>
          </el-form-item>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer" >
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form:{},
      pagination: {
        //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 10 //每页条数
      },
      dialogVisible : false,

      // 导入按钮的文本
      importDataTextMuilt: '导入选择题',
      importDataTextJudge: '导入判断题',
      importDataTextFill: '导入填空题',
      importDataTextAnswer:'导入简答题',

      // 导入按钮的图标
      importDataIcon: 'el-icon-upload2',
      // 导入按钮是否被禁用
      importDisabled: false,
      importBtnDisabled: false, // 按钮是否禁用,
    };
  },
  created() {
    this.getAnswerInfo();
  },
  methods: {

    edit(questionId,typess) { //编辑试卷
      this.$cookies.set("types",typess)
      this.dialogVisible = true
      if (typess == "选择题") {
        this.$axios(`/api/selectMutilById/${questionId}`).then(res => { //根据试卷id请求后台
          if (res.data.code == 200) {
            this.form = res.data.data
          }
        })
      }
      else if (typess == "判断题") {
        this.$axios(`/api/selectJudgeById/${questionId}`).then(res => { //根据试卷id请求后台
          if (res.data.code == 200) {
            this.form = res.data.data
          }
        })
      }
      else {
        this.$axios(`/api/selectFillById/${questionId}`).then(res => { //根据试卷id请求后台
          if (res.data.code == 200) {
            this.form = res.data.data
          }
        })
      }
    },
    handleClose(done) { //关闭提醒
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        }).catch(_ => {});
    },
    submit() { //提交修改后的试卷信息
      let typess = this.$cookies.get("types")
      this.dialogVisible = false
      console.log(typess)
      if (typess == "选择题"){
      this.$axios({
        url: '/api/mutil',
        method: 'put',
        data: {
          ...this.form
        }
      }).then(res => {
        if (res.data.code == 200) {
          this.$message({ //成功修改提示
            message: '更新成功',
            type: 'success'
          })
          this.$cookies.remove("types")
        }
        this.getAnswerInfo()
      })
    }
    else if (typess == "判断题") {
        this.$axios({
          url: '/api/judge',
          method: 'put',
          data: {
            ...this.form
          }
        }).then(res => {
          if (res.data.code == 200) {
            this.$message({ //成功修改提示
              message: '更新成功',
              type: 'success'
            })
            this.$cookies.remove("types")
          }
          this.getAnswerInfo()
        })
      }
      else {
        this.$axios({
          url: '/api/fill',
          method: 'put',
          data: {
            ...this.form
          }
        }).then(res => {
          if (res.data.code == 200) {
            this.$message({ //成功修改提示
              message: '更新成功',
              type: 'success'
            })
            this.$cookies.remove("types")
          }
          this.getAnswerInfo()
        })
      }
    },
    deleteRecord(questionId,typess) {
      console.log(questionId)
      this.$confirm("确定删除该记录吗,该操作不可逆！！！","删除提示",{
        confirmButtonText: '确定删除',
        cancelButtonText: '算了,留着',
        type: 'danger'
      }).then(()=> { //确认删除
        if (typess == '选择题') {
          this.$axios({

            url: `/api/deleteMutil/${questionId}`,
            method: 'delete',

          }).then(res => {
            this.getAnswerInfo()
          })
        }
        else if (typess == '判断题'){
          this.$axios({

            url: `/api/deleteJudge/${questionId}`,
            method: 'delete',

          }).then(res => {
            this.getAnswerInfo()
          })
        }
        else {
          this.$axios({

            url: `/api/deleteFill/${questionId}`,
            method: 'delete',

          }).then(res => {
            this.getAnswerInfo()
          })
        }
        }).catch(() => {

        })

    },
    getAnswerInfo() {
      //分页查询所有试卷信息
      this.$axios(
        `/api/answers/${this.pagination.current}/${this.pagination.size}`
      )
        .then(res => {
          this.pagination = res.data.data;
          console.log(res);
        })
        .catch(error => {});
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getAnswerInfo();
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getAnswerInfo();
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 == 0) {
        return "warning-row";
      } else {
        return "success-row";
      }
    },
    // 导入文件失败后回调
    onError() {
      this.importDataTextMuilt = '导入选择题';
      this.importDataTextJudge = '导入判断题';
      this.importDataTextFill = '导入填空题';
      this.importDataIcon = 'el-icon-upload2';
      this.importDisabled = false;
      // this.initEmps();
      this.$message.success("导入失败！");
    },
    // 导入文件成功后回调
    onSuccess() {
      // 成功后文本修改为原来的导入数据
      this.importDataTextMuilt = '导入选择题';
      this.importDataTextJudge = '导入判断题';
      this.importDataTextFill = '导入填空题';
      // 图标修改
      this.importDataIcon = 'el-icon-upload2';
      // 将上传组件改为允许使用
      this.importDisabled = false;
      // 调用刷新数据的方法
      // this.initEmps();
      // message 弹出消息
      this.$message.success("导入成功！");
    },
    // 上传文件调用
    beforeUpload() {
      // 将文本修改为正在导入
      this.importDataText = '正在导入';
      // 修改其图标
      this.importDataIcon = 'el-icon-loading';
      // 将其上传组件暂时禁用
      this.importDisabled = true;
    },
    exportmultiWord () {
      window.location.href="http://localhost:8090/static/选择题模板.xlsx" ;
    },
    exportjufiWord(){
      window.location.href="http://localhost:8090/static/填空判断题模板.xlsx" ;
    }
  }
};
</script>
<style lang="scss" scoped>
.exam {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit {
    margin-left: 20px;
  }
  .el-table tr {
    background-color: #DD5862 !important;
  }
}
  .el-table .warning-row {
    background: #000 !important;
    
  }

  .el-table .success-row {
    background: #DD5862;
  }

</style>
