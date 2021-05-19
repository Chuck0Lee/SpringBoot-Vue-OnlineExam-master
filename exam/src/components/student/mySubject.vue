// 我的试卷页面
<template>
  <div id="myExam">
    <div class="title">考试课程</div>
    <div class="wrapper">
      <ul class="top">
        <li class="order">课程列表</li>
        <li class="search-li"><div class="icon"><input type="text" placeholder="课程名称" class="search" v-model="key"><i class="el-icon-search"></i></div></li>
        <li><el-button type="primary" @click="search()">搜索课程</el-button></li>
      </ul>
      <ul class="paper" v-loading="loading">
        <li class="item" v-for="(item,index) in pagination.records" :key="index">
          <h4 align="center" @click="toExamMsg(item.source)">{{item.source}}</h4>
        </li>
      </ul>
      <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-sizes="[6, 10, 20, 40]"
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    // name: 'myExam'
    data() {
      return {
        loading: false,
        key: null, //搜索关键字
        allSubject: null, //所有课程
        pagination: { //分页后的课程信息
          current: 1, //当前页
          total: null, //记录条数
          size: 6 //每页条数
        }
      }
    },
    created() {
      this.getExamInfo()
      this.loading = true
    },
    // watch: {

    // },
    methods: {
      //获取当前课程的所有考试
      getExamInfo() {
        let grade = this.$cookies.get("cgrade")
        let  major = unescape(this.$cookies.get("cmajor"))
        // console.log(grade)
        this.$axios(`/api/subject/${this.pagination.current}/${this.pagination.size}/${grade}/${major}`).then(res => {
          this.pagination = res.data.data
          this.loading = false
          console.log(this.pagination)
        }).catch(error => {
          console.log(error)
        })
      },
      //改变当前记录条数
      handleSizeChange(val) {
        this.pagination.size = val
        this.getExamInfo()
      },
      //改变当前页码，重新发送请求
      handleCurrentChange(val) {
        this.pagination.current = val
        this.getExamInfo()
      },
      //搜索试卷
      search() {
        let grade = this.$cookies.get("cgrade")
        let major = unescape(this.$cookies.get("cmajor"))
        // console.log(grade,major)

        this.$axios(`/api/subjects/${grade}/${major}`).then(res => {
          if (res.data.code == 200) {
            let allSubject = res.data.data
            let newPage = allSubject.filter(item => {
              return item.source.includes(this.key)
            })
            this.pagination.records = newPage
          }
        })
      },
      //跳转到试卷详情页
      toExamMsg(subject) {
        this.$router.push({path: '/myExam', query: {subject: subject}})
        console.log(subject)
      }
    }
  }
</script>

<style lang="scss" scoped>
  .pagination {
    padding: 20px 0px 30px 0px;
    .el-pagination {
      display: flex;
      justify-content: center;
    }
  }
  .paper {
    h4 {
      cursor: pointer;
    }
  }
  .paper .item a {
    color: #000;
  }
  .wrapper .top .order {
    cursor: pointer;
  }
  .wrapper .top .order:hover {
    color: #0195ff;
    border-bottom: 2px solid #0195ff;
  }
  .wrapper .top .order:visited {
    color: #0195ff;
    border-bottom: 2px solid #0195ff;
  }
  .item .info i {
    margin-right: 5px;
    color: #0195ff;
  }
  .item .info span {
    margin-right: 14px;
  }
  .paper .item {
    width: 380px;
    border-radius: 4px;
    padding: 20px 30px;
    border: 1px solid #eee;
    box-shadow: 0 0 4px 2px rgba(217,222,234,0.3);
    transition: all 0.6s ease;
    color: #3a8ee6;
  }
  .paper .item:hover {
    box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
    transform: scale(1.03);
  }
  .paper .item .info {
    font-size: 14px;
    color: #88949b;
  }
  .paper .item .name {
    font-size: 14px;
    color: #88949b;
  }
  .paper * {
    margin: 20px 0;
  }
  .wrapper .paper {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
  }
  .top .el-icon-search {
    position: absolute;
    right: 10px;
    top: 10px;
  }
  .top .icon {
    position: relative;
  }
  .wrapper .top {
    border-bottom: 1px solid #eee;
    margin-bottom: 20px;
  }
  #myExam .search-li {
    margin-left: auto;
  }
  .top .search-li {
    margin-left: auto;
  }
  .top li {
    display: flex;
    align-items: center;
  }
  .top .search {
    margin-left: auto;
    padding: 10px;
    border-radius: 4px;
    border: 1px solid #eee;
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
  }
  .top .search:hover {
    color: #0195ff;
    border-color: #0195ff;
  }
  .wrapper .top {
    display: flex;
  }
  .wrapper .top li {
    margin: 20px;
  }
  #myExam {
    width: 980px;
    margin: 0 auto;
  }
  #myExam .title {
    margin: 20px;
  }
  #myExam .wrapper {
    background-color: #fff;
  }
</style>
