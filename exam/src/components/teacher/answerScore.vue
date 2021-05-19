<template>
<div id="answer">
  <el-table :data="pagination.records" border>
    <el-table-column fixed="left" prop="studentName" label="姓名" width="50"></el-table-column>
    <el-table-column prop="question" label="题目" width="300"></el-table-column>
    <el-table-column prop="answers" label="答题内容" width="295"></el-table-column>
    <el-table-column prop="answer" label="标准答案" width="300"></el-table-column>
    <el-table-column prop="score" label="分数" width="50"></el-table-column>
    <el-table-column fixed="right" label="操作" width="150">
      <template slot-scope="scope">
        <el-button @click="edit(scope.row.answerId,scope.row.studentId)" type="primary" size="small">打分</el-button>
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

  <el-dialog
    title="编辑考试信息"
    :visible.sync="dialogVisible"
    width="30%"
    :before-close="handleClose">
    <section class="update">
      <el-form ref="form"  label-width="80px">
        <el-form-item label="分数">
          <el-input v-model="score"></el-input>
        </el-form-item>
      </el-form>
    </section>
    <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit()">确 定</el-button>
      </span>
  </el-dialog>
</div>
    
</template>

<script>
    export default {
        // name: "answerScore"
      data(){
          return{
            pagination: {
              //分页后的考试信息
              current: 1, //当前页
              total: null, //记录条数
              size: 6 //每页条数
            },
            score:null,
            studentId:null,
            answerId:null,
            dialogVisible: false
          }
      },
      created() {
        this.getanswersInfo();
      },
      methods:{
        getanswersInfo(){
          //分页查询所有试题信息
          let examCode = this.$route.query.examCode
          console.log(examCode)

          this.$axios(`/api/answers/${this.pagination.current}/${this.pagination.size}/${examCode}`).then(res => {
            console.log(res.data.data)
            this.pagination = res.data.data;

          }).catch(error => {});
        },
        edit(answerId,studentId) { //打分
          this.dialogVisible = true
          this.studentId = studentId
          this.answerId = answerId
          console.log(studentId)
          // this.$axios(`/api/answerScore/${answerId}`).then(res => { //根据试卷id请求后台
          //   if(res.data.code == 200) {
          //     console.log(res.data.data)
          //     this.score = res.data.data
          //   }
          // })
        },

        //提交分数
        submit() {
          this.dialogVisible = false
          let score = this.score
          let studentId = this.studentId
          let answerId = this.answerId
          console.log(answerId)
          let examCode = this.$route.query.examCode
          this.$axios({
            url: `/api/answerScore/${score}`,
            method: 'put'
          }).then(res => {
            if(res.data.code == 200) {
              this.$message({ //成功修改提示
                message: '打分成功',
                type: 'success'
              })
            }
          }).then(
            this.$axios({
              url: `/api/updatescore/${score}/${examCode}/${studentId}`,
              method:'put'
            })
          ).then(
            this.$axios({
              url: `api/deleteAnswer/${answerId}`,
              method:"delete"
            })
          ).then(
            this.getanswersInfo()
          )

        },
        handleClose(done) { //关闭提醒
          this.$confirm('确认关闭？')
            .then(_ => {
              done();
            }).catch(_ => {});
        },
        //改变当前记录条数
        handleSizeChange(val) {
          this.pagination.size = val;
          this.getanswersInfo();
        },
        //改变当前页码，重新发送请求
        handleCurrentChange(val) {
          this.pagination.current = val;
          this.getanswersInfo();
        },
      }
    }
</script>

<style lang="scss" scoped>
#answer {
  padding: 0px 40px;
  position: relative;
  .box{
    width: 600px;
    height: 400px;
  }
  .notFound {
    position: absolute;
    top: 0px;
    left: 0px;
  }
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .el-table tr {
    background-color: #dd5862 !important;
  }
}
.el-table .warning-row {
  background: #000 !important;
}

.el-table .success-row {
  background: #dd5862;
}
</style>
