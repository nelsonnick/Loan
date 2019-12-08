<template>
  <div>
  <table border="1" width="1260" height="800" cellspacing="0" cellpadding="0">
    <tr>
      <td width="70" style="text-align:center">办理地点</td>
      <td width="70" style="text-align:center">报件总数</td>
      <td width="70" style="text-align:center">报件五万数量</td>
      <td width="70" style="text-align:center">报件八万数量</td>
      <td width="70" style="text-align:center">报件十万数量</td>
      <td width="70" style="text-align:center">报件十五万数量</td>
      <td width="70" style="text-align:center">报件二十万数量</td>
      <td width="70" style="text-align:center">报件总计金额</td>
      <td width="70" style="text-align:center">退件数量</td>
      <td width="70" style="text-align:center">退件金额</td>
      <td width="70" style="text-align:center">批件数量</td>
      <td width="70" style="text-align:center">放款数量</td>
      <td width="70" style="text-align:center">放款五万金额</td>
      <td width="70" style="text-align:center">放款八万金额</td>
      <td width="70" style="text-align:center">放款十万金额</td>
      <td width="70" style="text-align:center">放款十五万金额</td>
      <td width="70" style="text-align:center">放款二十万金额</td>
      <td width="70" style="text-align:center">放款总计金额</td>
    </tr>
    <tr v-for="department in departmentList" v-bind:key="department.id">
      <td style="text-align:center">{{department.name}}</td>
      <td style="text-align:center">{{countList[department.id][0][0]}}</td>
      <td style="text-align:center">{{countList[department.id][5][0]}}</td>
      <td style="text-align:center">{{countList[department.id][8][0]}}</td>
      <td style="text-align:center">{{countList[department.id][10][0]}}</td>
      <td style="text-align:center">{{countList[department.id][15][0]}}</td>
      <td style="text-align:center">{{countList[department.id][20][0]}}</td>
      <td style="text-align:center">{{moneyList[department.id][0][0]}}</td>
      <td style="text-align:center">{{countList[department.id][0][2]}}</td>
      <td style="text-align:center">{{moneyList[department.id][0][2]}}</td>
      <td style="text-align:center">{{countList[department.id][0][3]}}</td>
      <td style="text-align:center">{{countList[department.id][0][4]}}</td>
      <td style="text-align:center">{{countList[department.id][5][4]}}</td>
      <td style="text-align:center">{{countList[department.id][8][4]}}</td>
      <td style="text-align:center">{{countList[department.id][10][4]}}</td>
      <td style="text-align:center">{{countList[department.id][15][4]}}</td>
      <td style="text-align:center">{{countList[department.id][20][4]}}</td>
      <td style="text-align:center">{{moneyList[department.id][0][4]}}</td>
    </tr>
    <tr>
      <td style="text-align:center">合计</td>
      <td style="text-align:center">{{countList[0][0][0]}}</td>
      <td style="text-align:center">{{countList[0][5][0]}}</td>
      <td style="text-align:center">{{countList[0][8][0]}}</td>
      <td style="text-align:center">{{countList[0][10][0]}}</td>
      <td style="text-align:center">{{countList[0][15][0]}}</td>
      <td style="text-align:center">{{countList[0][20][0]}}</td>
      <td style="text-align:center">{{moneyList[0][0][0]}}</td>
      <td style="text-align:center">{{countList[0][0][2]}}</td>
      <td style="text-align:center">{{moneyList[0][0][2]}}</td>
      <td style="text-align:center">{{countList[0][0][3]}}</td>
      <td style="text-align:center">{{countList[0][0][4]}}</td>
      <td style="text-align:center">{{countList[0][5][4]}}</td>
      <td style="text-align:center">{{countList[0][8][4]}}</td>
      <td style="text-align:center">{{countList[0][10][4]}}</td>
      <td style="text-align:center">{{countList[0][15][4]}}</td>
      <td style="text-align:center">{{countList[0][20][4]}}</td>
      <td style="text-align:center">{{moneyList[0][0][4]}}</td>
    </tr>
  </table>
  </div>
</template>

<script>import axios from 'axios'
export default {
  name: 'Welcome',
  data () {
    return {
      departmentList: [],
      countList: [],
      moneyList: []
    }
  },
  created: function () {
    axios.get('/statistics/getCount').then(res => {
      this.countList = res.data
    }).catch(res => {
      this.$Loading.error()
      this.$Notice.error({
        title: '服务器内部错误，无法获取统计列表!'
      })
    })
    axios.get('/statistics/getMoney').then(res => {
      this.moneyList = res.data
    }).catch(res => {
      this.$Loading.error()
      this.$Notice.error({
        title: '服务器内部错误，无法获取统计列表!'
      })
    })
    axios.get('/statistics/getDepartment').then(res => {
      this.departmentList = res.data
    }).catch(res => {
      this.$Loading.error()
      this.$Notice.error({
        title: '服务器内部错误，无法获取统计列表!'
      })
    })
  },
  methods: {

  }
}
</script>
<style scoped>

</style>
