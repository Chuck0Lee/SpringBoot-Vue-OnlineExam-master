<template>
  <div class="part" >
    <div class="box" ref="box"></div>
    <div v-if="isNull">
      <span>该门考试还没人参考哦,请提醒学生参加考试。</span>
    </div>
    <div id="tables">
      <el-table :data="pagination.records" border>
        <el-table-column fixed="left" prop="question" label="题目" width="290"></el-table-column>
        <el-table-column prop="rightnum" label="答对人数" width="300"></el-table-column>
        <el-table-column prop="num" label="总次数" width="295"></el-table-column>
        <el-table-column prop="rightss" label="正确率" width="295"></el-table-column>
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
    </div>
  </div>

</template>

<script>
export default {
  data() {
    return {
      isNull: false, //是否有成绩标志位
      name: null,
      category: { //保存分数段
        '90分及以上': 0,
        '80-89分': 0,
        '70-79分': 0,
        '60-69分': 0,
        '60分以下': 0,
      },
      pagination: {
        //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 6 //每页条数
      },
      paperId:null,
    }
  },
  created() {
    this.getrights()
    this.getScoreInfo()

  },
  methods: {
    getScoreInfo() {
      let examCode = this.$route.query.examCode
      this.name = this.$route.query.source
      this.$axios(`/api/scores/${examCode}`).then(res => {
        let data = res.data.data
        if(data.length > 0) {
          let box = this.$refs['box']
          let charts = this.$echarts.init(box)
          data.forEach(element => {
            switch(element.etScore / 10) {
              case 10:
              case 9:
                this.category["90分及以上"]++
                break
              case 8:
                this.category['80-89分']++
                break
              case 7:
                this.category["70-79分"]++
                break
              case 6:
                this.category['60-69分']++
                break
              default:
                this.category['60分以下']++
            }
          });
          let option = {
              title : {
                  text: `${this.name}分数段图`,
                  subtext: '分数段饼图',
                  x:'center'
              },
              tooltip : {
                  trigger: 'item',
                  formatter: "{a}：{b} <br/> {c}人 ({d}%)"
              },
              legend: {
                  orient: 'vertical',
                  left: 'left',
                  data: ['90分及以上','80-89分','70-79分','60-69分','60分以下']
              },
              series : [
                  {
                      name: '分数段',
                      type: 'pie',
                      radius : '35%',
                      center: ['50%', '35%'],
                      data:[
                          {value:this.category['90分及以上'], name:'90分及以上'},
                          {value:this.category['80-89分'], name:'80-89分'},
                          {value:this.category['70-79分'], name:'70-79分'},
                          {value:this.category['60-69分'], name:'60-69分'},
                          {value:this.category['60分以下'], name:'60分以下'}
                      ],
                      itemStyle: {
                          emphasis: {
                              shadowBlur: 10,
                              shadowOffsetX: 0,
                              shadowColor: 'rgba(0, 0, 0, 0.5)'
                          }
                      }
                  }
              ]
          };
          charts.setOption(option)
        }else {
          this.isNull = true
        }
      })
    },
    getrights(){
      let examCode = this.$route.query.examCode
      console.log(examCode)
      this.$axios(`/api/exam/${examCode}`).then(res => {  //通过examCode请求试卷详细信息
        this.examData = {...res.data.data} //获取考试详情
        this.index = 0
        this.time = this.examData.totalTime //获取分钟数
        let paperId = this.examData.paperId
        this.paperId = paperId
        this.$axios(`/api/nums/${this.pagination.current}/${this.pagination.size}/${paperId}`).then(res=>{
          this.pagination = res.data.data;

        }).catch(error => {});
      })


      // this.$axios(`/api/scores/${this.pagination.current}/${this.pagination.size}/${studentId}`).then(res => {
      //   this.pagination = res.data.data;
      //
      // }).catch(error => {});
    },
    rightnums(rightnum,num){
      console.log(rightnum+"++++++"+num)
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      // this.getscoresInfo();
      this.getrights()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      // this.getscoresInfo();
      this.getrights()
    },
  },

}
</script>

<style lang="scss" scoped>
.part {

  .box {
    width: 800px;
    height: 420px;
    margin-left: 40px;
  }

}
#tables{
  padding: 0px 40px;
  position: relative;
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


