<template>

  <div id="message">
    <div class="title">错题集</div>
    <el-tabs v-model="activeName">
      <el-tab-pane label="选择题" name="first">
        <div class="wrapper">
          <div class="all">
            <ul class="msglist">
              <li class="list"
                  @mouseenter="enter(index)"
                  @mouseleave="leave(index)"
                  v-for="(data,index) in msg" :key="index"
              >
                <p class="title1">题目：{{data.question}}</p>
                <p class="questionA">A:{{data.answerA}}</p><p>B:{{data.answerB}}</p>
                <p class="questionC">C:{{data.answerC}}</p><p>D:{{data.answerD}}</p>
                <p class="answer">你的答案:{{data.rights}}</p><p>正确答案:{{data.rightAnswer}}</p>
                <el-button type="success" @click="deletemulti(data.questionId)">已掌握</el-button>
              </li>
            </ul>
          </div>
          <div class="pagination">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pagination.current"
              :page-sizes="[4,6,8,10]"
              :page-size="pagination.size"
              layout="total, sizes, prev, pager, next, jumper"
              :total="pagination.total">
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="判断题" name="second">
        <div class="wrapper">
          <div class="all">
            <ul class="msglist">
              <li class="list"
                  @mouseenter="enter(index)"
                  @mouseleave="leave(index)"
                  v-for="(data,index) in msg1" :key="index"
              >
                <p class="title1">题目：{{data.question}}</p>
                <p class="answer">你的答案:{{data.rights}}</p><p>正确答案:{{data.answer}}</p>
                <el-button type="success" @click="deletejudge(data.questionId)">已掌握</el-button>
                <!--<div v-for="(replayData,index2) in data.replays" :key="index2">-->
                  <!--<p class="comment"><i class="iconfont icon-huifuxiaoxi"></i>{{replayData.replay}}</p>-->
                <!--</div>-->
              </li>
            </ul>
          </div>
          <div class="pagination">
            <el-pagination
              @size-change="handleSizeChange1"
              @current-change="handleCurrentChange1"
              :current-page="pagination1.current"
              :page-sizes="[4,6,8,10]"
              :page-size="pagination1.size"
              layout="total, sizes, prev, pager, next, jumper"
              :total="pagination1.total">
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="填空题" name="third">
        <div class="wrapper">
          <div class="all">
            <ul class="msglist">
              <li class="list"
                  @mouseenter="enter(index)"
                  @mouseleave="leave(index)"
                  v-for="(data,index) in msg2" :key="index"
              >
                <p class="title1">题目：{{data.question}}</p>
                <p class="answer">正确答案:{{data.answer}}</p>
                <el-button type="success" @click="deleteFill(data.questionId)">已掌握</el-button>
                <!--<div v-for="(replayData,index2) in data.replays" :key="index2">-->
                <!--<p class="comment"><i class="iconfont icon-huifuxiaoxi"></i>{{replayData.replay}}</p>-->
                <!--</div>-->
              </li>
            </ul>
          </div>
          <div class="pagination">
            <el-pagination
              @size-change="handleSizeChange2"
              @current-change="handleCurrentChange2"
              :current-page="pagination1.current"
              :page-sizes="[4,6,8,10]"
              :page-size="pagination1.size"
              layout="total, sizes, prev, pager, next, jumper"
              :total="pagination1.total">
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  export default {
      //name: "errorQuestion",
      data(){
          return{
            activeName: 'first',
            pagination: { //分页后的留言列表
              current: 1, //当前页
              total: null, //记录条数
              size: 6 //每页条数
            },
            pagination1: { //分页后的留言列表
              current: 1, //当前页
              total: null, //记录条数
              size: 6 //每页条数
            },
            pagination2: { //分页后的留言列表
              current: 1, //当前页
              total: null, //记录条数
              size: 6 //每页条数
            },

            msg:[],
            msg1:[],
            msg2:[]

          }
      },
    created() {
      this.getErroeMulti()
      this.getErroeJudge()
      this.getErroeFill()
    },
    methods:{

      //根据学生的id获取选择题错题
      getErroeMulti() {
        let studentId = this.$cookies.get("cid")
        //  执行controller里的方法
        this.$axios(`/api/mutilerrors/${this.pagination.current}/${this.pagination.size}/${studentId}`).then(res => {
          let status = res.data.code
          console.log(res.data.data.records)
          if(status == 200) {
            this.msg = res.data.data.records
            this.pagination = res.data.data
          }
        })
      },
      getErroeJudge() {
        let studentId = this.$cookies.get("cid")
        // console.log(studentId)
        this.$axios(`/api/judgeerrors/${this.pagination1.current}/${this.pagination1.size}/${studentId}`).then(res => {
          let status = res.data.code
          console.log(res.data.data.records)
          if(status == 200) {
            this.msg1 = res.data.data.records
            this.pagination1 = res.data.data
          }
        })
      },
      getErroeFill(){
        let studentId = this.$cookies.get("cid")
        // console.log(studentId)
        this.$axios(`/api/fillerrors/${this.pagination2.current}/${this.pagination2.size}/${studentId}`).then(res => {
          let status = res.data.code
          console.log(res.data.data.records)
          if(status == 200) {
            this.msg2 = res.data.data.records
            this.pagination2 = res.data.data
          }
        })
      },
      //改变当前记录条数
      handleSizeChange(val) {
        this.pagination.size = val
        this.getErroeMulti()
      },
      //改变当前页码，重新发送请求
      handleCurrentChange(val) {
        this.pagination.current = val
        this.getErroeMulti()
      },
      //改变当前记录条数
      handleSizeChange1(val) {
        this.pagination1.size = val
        this.getErroeJudge()
      },
      //改变当前页码，重新发送请求
      handleCurrentChange1(val) {
        this.pagination1.current = val
        this.getErroeJudge()
      },
      //改变当前记录条数
      handleSizeChange2(val) {
        this.pagination2.size = val
        this.getErroeFill()
      },
      //改变当前页码，重新发送请求
      handleCurrentChange2(val) {
        this.pagination2.current = val
        this.getErroeFill()
      },
      enter(index) {
        this.flag = true
        this.current = index
      },
      leave(index) {
        this.flag = false;
        this.current = index;
      },
      deletemulti(questionId){
        console.log(questionId)
          this.$axios({
            url:`/api/deleteErrorMulti/${questionId}`,
            method: 'delete',
          }).then(res=> {
            this.getErroeMulti()
          })
      },
      deletejudge(questionId){
        console.log(questionId)
        this.$axios({
          url:`/api/deleteErrorJudge/${questionId}`,
          method: 'delete',
        }).then(res=> {
          this.getErroeJudge()
        })
      },
      deleteFill(questionId){
        console.log(questionId)
        this.$axios({
          url:`/api/deleteErrorFill/${questionId}`,
          method: 'delete',
        }).then(res=> {
          this.getErroeFill()
        })
      },
    }
    }
</script>

<style scoped>
.pagination {
  display: flex;
  justify-content: center;
}
#message {
  width: 980px;
  margin: 0 auto;
}
.title {
  margin: 20px;
}

.title1{
  margin:  20px  20px  20px 0px;
}
.content {
  padding: 20px 0px;
}

#message .wrapper {
  background-color: #fff;
  padding: 20px;

}
#message .judge {
  background-color: #fff;
  padding: 20px;

}
</style>
