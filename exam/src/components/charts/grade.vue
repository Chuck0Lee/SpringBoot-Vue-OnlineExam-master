// 成绩统计页面
<template>
  <div id="grade">
    <div ref="box" class="box"></div>
    <div class="notFound" v-if="isNull">
      <i class="iconfont icon-LC_icon_tips_fill"></i><span>该考生未参加考试</span>
    </div>
    <el-table :data="pagination.records" border>
      <el-table-column fixed="left" prop="studentName" label="姓名" width="290"></el-table-column>
      <el-table-column prop="answerDate" label="考试日期" width="300"></el-table-column>
      <el-table-column prop="subject" label="科目" width="300"></el-table-column>
      <el-table-column prop="etScore" label="分数" width="295"></el-table-column>
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




</template>

<script>
export default {
  name: "grade",
  data() {
    return {
      isNull: false, //原始数据
      tableDataX: [], //x轴数据 保存次数
      tableDataY: [], //y轴数据 保存分数
      pagination: {
        //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 6 //每页条数
      }
    }
  },
  mounted() {
    this.score();
  },
  created() {
    this.getscoresInfo();
  },
  methods: {
    score() {
      console.log(this.studentName)
      let studentId = this.$route.query.studentId
      this.$axios(`/api/scoress/${studentId}`).then(res => { //根据学生Id查询成绩
        console.log(res)
        if(res.data.code == 200) {
          let rootData = res.data.data
          rootData.forEach((element,index) => {
            // this.tableDataX.push(`第${index + 1}次`)
            this.tableDataX.push(element.subject)
            this.tableDataY.push(element.etScore)
          });
          let boxDom = this.$refs["box"];
          let scoreCharts = this.$echarts.init(boxDom);
          let option = {
            xAxis: {
              type: "category",
              data: this.tableDataX
            },
            yAxis: {
              type: "value"
            },
            series: [
              {
                data: this.tableDataY,
                type: "line",
                itemStyle: { normal: { label: { show: true } } }
              }
            ]
          };
          scoreCharts.setOption(option);
          scoreCharts.on("mouseover", params => {
            console.log(params.value);
          });
        }else {
          this.isNull = true
        }
      })
    },
    getscoresInfo(){
      //分页查询所有试卷信息
      let studentId = this.$route.query.studentId

      this.$axios(`/api/scores/${this.pagination.current}/${this.pagination.size}/${studentId}`).then(res => {
        this.pagination = res.data.data;

      }).catch(error => {});
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getscoresInfo();
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getscoresInfo();
    },
  }
};
</script>

<style lang="scss" scoped>
#grade {
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
