// 添加题库
<template>
  <div class="add">
    <el-tabs v-model="activeName">
    <el-tab-pane name="first">
      <span slot="label"><i class="el-icon-circle-plus"></i>添加试题</span>
      <section class="append">
        <ul>
          <li>
            <span>题目类型:</span>
            <el-select v-model="optionValue" placeholder="请选择题型" class="w150">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == '选择题'">
            <span>所属章节：</span>
            <el-input
              placeholder="请输入对应章节"
              v-model="postChange.section"
              class="w150"
              clearable>
            </el-input>
          </li>
          <li v-if="optionValue == '填空题'">
            <span>所属章节：</span>
            <el-input
              placeholder="请输入对应章节"
              v-model="postFill.section"
              class="w150"
              clearable>
            </el-input>
          </li>
          <li v-if="optionValue == '判断题'">
            <span>所属章节：</span>
            <el-input
              placeholder="请输入对应章节"
              v-model="postJudge.section"
              class="w150"
              clearable>
            </el-input>
          </li>
          <li v-if="optionValue == '简答题'">
            <span>所属章节：</span>
            <el-input
              placeholder="请输入对应章节"
              v-model="postAnswer.section"
              class="w150"
              clearable>
            </el-input>
          </li>
          <li v-if="optionValue == '选择题'">
            <span>难度等级:</span>
            <el-select v-model="postChange.level" placeholder="选择难度等级" class="w150">
              <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == '填空题'">
            <span>难度等级:</span>
            <el-select v-model="postFill.level" placeholder="选择难度等级" class="w150">
              <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == '判断题'">
            <span>难度等级:</span>
            <el-select v-model="postJudge.level" placeholder="选择难度等级" class="w150">
              <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == '选择题'">
            <span>正确选项:</span>
            <el-select v-model="postChange.rightAnswer" placeholder="选择正确答案" class="w150">
              <el-option
                v-for="item in rights"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
        </ul>
        <!-- 选择题部分 -->
        <div class="change" v-if="optionValue == '选择题'">
          <div class="title">
            <el-tag>题目:</el-tag><span>在下面的输入框中输入题目,形如--DNS 服务器和DHCP服务器的作用是（）</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postChange.question"
              placeholder="请输入题目内容"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="options">
            <ul>
              <li>
                <el-tag type="success">A</el-tag>
                <el-input
                  placeholder="请输入选项A的内容"
                  v-model="postChange.answerA"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">B</el-tag>
                <el-input
                  placeholder="请输入选项B的内容"
                  v-model="postChange.answerB"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">C</el-tag>
                <el-input
                  placeholder="请输入选项C的内容"
                  v-model="postChange.answerC"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">D</el-tag>
                <el-input
                  placeholder="请输入选项D的内容"
                  v-model="postChange.answerD"
                  clearable="">
                </el-input>
              </li>
            </ul>
          </div>
          <div class="title">
            <el-tag>解析:</el-tag><span>在下面的输入框中输入题目解析</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postChange.analysis"
              placeholder="请输入答案解析"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="submit">
            <el-button type="primary" @click="changeSubmit()">立即添加</el-button>
          </div>
        </div>
        <!-- 填空题部分 -->
        <div class="change fill" v-if="optionValue == '填空题'">
          <div class="title">
            <el-tag>题目:</el-tag><span>输入题目,形如--从计算机网络系统组成的角度看，计算机网络可以分为()和()。注意需要考生答题部分一定要用括号（英文半角）括起来。</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postFill.question"
              placeholder="请输入题目内容"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="fillAnswer">
            <el-tag>正确答案:</el-tag>
            <el-input v-model="postFill.answer"></el-input>
          </div>
          <div class="title analysis">
            <el-tag type="success">解析:</el-tag><span>下方输入框中输入答案解析</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postFill.analysis"
              placeholder="请输入答案解析"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="submit">
            <el-button type="primary" @click="fillSubmit()">立即添加</el-button>
          </div>
        </div>
        <!-- 判断题 -->
        <div class="change judge" v-if="optionValue == '判断题'">
          <div class="title">
            <el-tag>题目:</el-tag><span>在下面的输入框中输入题目</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postJudge.question"
              placeholder="请输入题目内容"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="judgeAnswer">
            <el-radio v-model="postJudge.answer" label="T">正确</el-radio>
            <el-radio v-model="postJudge.answer" label="F">错误</el-radio>
          </div>
          <div class="title">
            <el-tag>解析:</el-tag><span>在下面的输入框中输入题目解析</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postJudge.analysis"
              placeholder="请输入答案解析"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="submit">
            <el-button type="primary" @click="judgeSubmit()">立即添加</el-button>
          </div>
        </div>
        <div class="change judge" v-if="optionValue == '简答题'">
          <div class="title">
            <el-tag>题目:</el-tag><span>在下面的输入框中输入题目</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postAnswer.question"
              placeholder="请输入题目内容"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="title">
            <el-input
              type="textarea"
              rows="4"
              v-model="postAnswer.answer"
              placeholder="请输入标准答案"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="title">
            <el-tag>解析:</el-tag><span>在下面的输入框中输入题目解析</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postAnswer.analysis"
              placeholder="请输入答案解析"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="submit">
            <el-button type="primary" @click="answerSubmit()">立即添加</el-button>
          </div>
        </div>
      </section>
    </el-tab-pane>
    <el-tab-pane name="second">
      <span slot="label"><i class="iconfont icon-daoru-tianchong"></i>在线组卷</span>
      <div class="box">
        <ul>
          <li>
            <span>试题难度:</span>
            <el-select v-model="difficultyValue" placeholder="试题难度" class="w150">
              <el-option
                v-for="item in difficulty"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li>
            <span>试题章节</span>
            <el-select v-model="sectionValue" placeholder="试题章节" class="w150" @change="mutilNum()">
              <el-option
                v-for="item in section"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li>
            <span>选择题数量：</span> <el-input type="text" v-model="changeNumber"></el-input>
            &nbsp;&nbsp;<span>题库共有{{this.mutilnum}}道题</span>
          </li>
          <li>
            <span>填空题数量：</span> <el-input type="text" v-model="fillNumber"></el-input>
            &nbsp;&nbsp;<span>题库共有{{this.judgenum}}道题</span>
          </li>
          <li>
            <span>判断题数量：</span> <el-input type="text" v-model="judgeNumber"></el-input>
            &nbsp;&nbsp;<span>题库共有{{this.fillnum}}道题</span>
          </li>
          <li>
            <el-button type="primary" @click="create()">立即组卷</el-button>
          </li>
        </ul>
      </div>
    </el-tab-pane>
      <el-tab-pane name="third">
        <span slot="label"><i class="el-icon-circle-plus"></i>导入题库</span>

        <div class="box">
          <div>

              <el-button size="small" style="display: inline-flex;margin-right: 10px;" type="primary" @click="exportmultiWord"  icon="el-icon-download"  plain>下载选择题模板</el-button>
            <el-button size="small" style="display: inline-flex;margin-right: 10px;" type="primary" @click="exportjufiWord"  icon="el-icon-download" plain>下载填空/判断题模板</el-button>
            <el-button size="small" style="display: inline-flex;margin-right: 10px;" type="primary" @click="exportanswerWord"  icon="el-icon-download" plain>下载简答题模板</el-button>
          </div>
          <br/>

          <div>
          <el-upload
            :show-file-list="false"
            :before-upload="beforeUpload"
            :on-success="onSuccess"
            :on-error="onError"
            :disabled="importDisabled"
            :data="{paperId:paperssId}"
            style="display: inline-flex;margin-right: 10px;"
            action="/api/importMulti2">
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
            action="/api/importJudge2">
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
            action="/api/importFill2">
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
              action="/api/importFill2">
              <el-button size="small" :disabled="importDisabled" type="success" :icon="importDataIcon">
                {{importDataTextAnswer}}
              </el-button>
            </el-upload>
          </div>
        </div>
      </el-tab-pane>
  </el-tabs>
  </div>
</template>

<script>
  // import Qs from 'qs';

export default {
  data() {
    return {
      changeNumber: null, //选择题出题数量
      fillNumber: null, //填空题出题数量
      judgeNumber: null, //判断题出题数量
      mutilnum:0,
      judgenum:0,
      fillnum:0,
      activeName: 'first',  //活动选项卡
      options: [ //题库类型
        {
          value: '选择题',
          label: '选择题'
        },
        {
          value: '填空题',
          label: '填空题'
        },
        {
          value: '判断题',
          label: '判断题'
        },
        {
          value: '简答题',
          label: '简答题'
        },
      ],
      difficulty: [ //试题难度
        {
          value: '简单',
          label: '简单'
        },
        {
          value: '一般',
          label: '一般'
        },
        {
          value: '困难',
          label: '困难'
        }
      ],
      difficultyValue: '简单',
      sectionValue:'请选择章节',
      levels: [ //难度等级
        {
          value: '1',
          label: '1'
        },
        {
          value: '2',
          label: '2'
        },
        {
          value: '3',
          label: '3'
        },
        {
          value: '4',
          label: '4'
        },
        {
          value: '5',
          label: '5'
        },
      ],
      rights: [ //正确答案
        {
          value: 'A',
          label: 'A'
        },
        {
          value: 'B',
          label: 'B'
        },
        {
          value: 'C',
          label: 'C'
        },
        {
          value: 'D',
          label: 'D'
        },
      ],
      section:[
        {
          value:'第一章',
          label:'第一章'
        },
        {
          value:'第二章',
          label:'第二章'
        },
        {
          value:'第三章',
          label:'第三章'
        },
        {
          value:'第四章',
          label:'第四章'
        },
        {
          value:'第五章',
          label:'第五章'
        },
      ],
      paperId: null,
      paperssId:this.$route.query.paperId,
      optionValue: '选择题', //题型选中值
      subject: '', //试卷名称用来接收路由参数
      postChange: { //选择题提交内容
        subject: '', //试卷名称
        level: '', //难度等级选中值 
        rightAnswer: '', //正确答案选中值
        section: '', //对应章节
        question: '', //题目
        analysis: '', //解析
        answerA: '',
        answerB: '',
        answerC: '',
        answerD: '',
      },
      postFill: { //填空题提交内容
        subject: '', //试卷名称
        level: '', //难度等级选中值 
        answer: '', //正确答案
        section: '', //对应章节
        question: '', //题目
        analysis: '', //解析
      },
      postJudge: { //判断题提交内容
        subject: '', //试卷名称
        level: '', //难度等级选中值 
        answer: '', //正确答案
        section: '', //对应章节
        question: '', //题目
        analysis: '', //解析
      },
      postAnswer:{
        subject:'',
        answer:'',
        section:'',
        question:'',
        analysis:''
      },
      postPaper: { //考试管理表对应字段
        paperId: null,
        questionType: null, // 试卷类型 1--选择题  2--填空题   3--判断题
        questionId: null,
      },
      // 导入按钮的文本
      importDataTextMuilt: '导入选择题',
      importDataTextJudge: '导入判断题',
      importDataTextFill: '导入填空题',
      importDataTextAnswer: '导入简答题',

      // 导入按钮的图标
      importDataIcon: 'el-icon-upload2',
      // 导入按钮是否被禁用
      importDisabled: false,
      importBtnDisabled: false, // 按钮是否禁用,
      // loading: false,
      // downloadUrl: "/static/test.xlsx",//excel模板文件，可以放到OSS中，也可以就放到vue项目中的static目录中，提供给用户下载

    };
  },
  created() {
    this.getParams()
  },
  methods: {

    // handleClick(tab, event) {
    //   console.log(tab, event);
    // },

    create() {
      let subject = this.$route.query.subject;
      console.log(this.sectionValue)

      this.$axios({
        url: '/api/item',
        method: 'post',

        data: {
          changeNumber: this.changeNumber,
          fillNumber: this.fillNumber,
          judgeNumber: this.judgeNumber,
          paperId: this.paperId,
          subject: subject,
          section:this.sectionValue
        }
      }).then(res => {

        console.log(res)
        let data = res.data
        if(data.code==200){
          setTimeout(() => {
            this.$router.push({path: '/selectAnswer'})
          },1000)
           this.$message({
            message: data.message,
            type: 'success'
          })
        }else if(data.code==400){
            this.$message({
            message: data.message,
            type: 'error'
          })
        }

      })
    },
    getParams() {
      let subject = this.$route.query.subject //获取试卷名称
      let paperId = this.$route.query.paperId //获取paperId
      this.paperId = paperId
      this.subject = subject
      this.postPaper.paperId = paperId
    },
    changeSubmit() { //选择题题库提交
      this.postChange.subject = this.subject
      this.$axios({ //提交数据到选择题题库表
        url: '/api/MultiQuestion',
        method: 'post',
        data: {
          ...this.postChange          
        }
      }).then(res => { //添加成功显示提示
        let status = res.data.code
        if(status == 200) {
          this.$message({
            message: '已添加到题库',
            type: 'success'
          })
          this.postChange = {}
        }
      }).then(() => {
        this.$axios(`/api/multiQuestionId`).then(res => { //获取当前题目的questionId
          let questionId = res.data.data.questionId
          this.postPaper.questionId = questionId
          this.postPaper.questionType = 1
          this.$axios({
            url: '/api/paperManage',
            method: 'Post',
            data: {
              ...this.postPaper
            }
          })
        })
      })
    },
    fillSubmit() { //填空题提交
      this.postFill.subject = this.subject
      this.$axios({
        url: '/api/fillQuestion',
        method: 'post',
        data: {
          ...this.postFill
        }
      }).then(res => {
        let status = res.data.code
        if(status == 200) {
          this.$message({
            message: '已添加到题库',
            type: 'success'
          })
          this.postFill = {}
        }
      }).then(() => {
        this.$axios(`/api/fillQuestionIds`).then(res => { //获取当前题目的questionId
          let questionId = res.data.data.questionId
          console.log("++++++++++++++++++"+questionId)
          this.postPaper.questionId = questionId
          this.postPaper.questionType = 2
          this.$axios({
            url: '/api/paperManage',
            method: 'Post',
            data: {
              ...this.postPaper
            }
          })
        })
      })
    },
    judgeSubmit() { //判断题提交
      this.postJudge.subject = this.subject
      this.$axios({
        url: '/api/judgeQuestion',
        method: 'post',
        data: {
          ...this.postJudge
        }
      }).then(res => {
        let status = res.data.code
        if(status == 200) {
          this.$message({
            message: '已添加到题库',
            type: 'success'
          })
          // this.postJudge = {}
        }
      }).then(() => {
        this.$axios(`/api/judgeQuestionId`).then(res => { //获取当前题目的questionId
          let questionId = res.data.data.questionId
          this.postPaper.questionId = questionId
          this.postPaper.questionType = 3
          this.$axios({
            url: '/api/paperManage',
            method: 'Post',
            data: {
              ...this.postPaper
            }
          })
        })
      })
    },
    answerSubmit(){
      this.postAnswer.subject = this.subject
      this.$axios({
        url: '/api/answerQuestion',
        method: 'post',
        data: {
          ...this.postAnswer
        }
      }).then(res => {
        let status = res.data.code
        if(status == 200) {
          this.$message({
            message: '已添加到题库',
            type: 'success'
          })
          // this.postJudge = {}
        }
      }).then(() => {
        this.$axios(`/api/answerQuestionId`).then(res => { //获取当前题目的questionId
          let questionId = res.data.data.questionId
          this.postPaper.questionId = questionId
          this.postPaper.questionType = 4
          console.log(questionId)
          this.$axios({
            url: '/api/paperManage',
            method: 'Post',
            data: {
              ...this.postPaper
            }
          })
        })
      })
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
    },
    exportanswerWord(){
      window.location.href="http://localhost:8090/static/简答题模板.xlsx" ;
    },
    mutilNum(){
      let section = this.sectionValue
      let subject = this.subject
      this.$axios(`/api/mutilnum/${section}/${subject}`).then(res=>{
              this.mutilnum = res.data.data
        }).then(
        this.$axios(`/api/judgenum/${section}/${subject}`).then(res=>{
          this.judgenum = res.data.data
        }).then(
          this.$axios(`/api/fillnum/${section}/${subject}`).then(res=>{
            this.fillnum = res.data.data
          })
        )
      )
    }
  },
};
</script>

<style lang="scss" scoped>
.add {
  margin: 0px 40px;
  .box {
    padding: 0px 20px;
    ul li {
      margin: 10px 0px;
      display: flex;
      align-items: center;
      .el-input {
        width: 6%;
      }
      .w150 {
        margin-left: 20px;
        width: 9%;
      }
    }
  }
  .el-icon-circle-plus {
    margin-right: 10px;
  }
  .icon-daoru-tianchong {
    margin-right: 10px;
  }
  .append {
    margin: 0px 20px;
    ul {
      display: flex;
      align-items: center;
      li {
        margin-right: 20px;
      }
    }
    .change {
      margin-top: 20px;
      padding: 20px 16px;
      background-color: #E7F6F6;
      border-radius: 4px;
      .title {
        padding-left: 6px;
        color: #2f4f4f;
        span:nth-child(1) {
          margin-right: 6px;
        }
        .answer {
          margin: 20px 0px 20px 8px;
        }
        .el-textarea {
          width: 98% !important;
        }
      }
      .options {
        ul {
          display: flex;
          flex-direction: column;
        }
        ul li {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 98%;
          margin: 10px 0px;
          span {
            margin-right: 20px;
          }
        }
      }
      .submit {
        display: flex;
        justify-content: center;
        align-items: center;
      }        
    }
    .fill {
      .fillAnswer {
        display: flex;
        justify-content: center;
        align-items: center;
        span {
          margin-right: 6px;
        }
        .el-input {
          width: 91% !important;
        }
      }
      .analysis {
        margin-top: 20px;
        margin-left: 5px;
      }
    }
    .judge {
      .judgeAnswer {
        margin-left: 20px;
        margin-bottom: 20px;
      }
    }
    .w150 {
      width: 150px;
    }
    li:nth-child(2) {
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}
</style>


