<template>
  <Menu mode="horizontal" theme="dark" :active-name="menuTop" @on-select="onSelect">
    <div class="layout-logo"></div>
    <div class="layout-nav">
      <MenuItem name="Main" to="/Main">
        <Icon type="md-contact"></Icon>
        当前用户：{{name}}
      </MenuItem>
      <MenuItem name="Loan" to="/Loan" v-cloak>
        <Icon type="md-list"></Icon>
        贷款列表
      </MenuItem>
      <MenuItem name="LoanAdd" to="/Loan/Add" v-cloak>
        <Icon type="md-add-circle"></Icon>
        新增贷款
      </MenuItem>
      <MenuItem name="Statistics" v-cloak>
        <Icon type="md-pie"></Icon>
        数据统计
      </MenuItem>
      <MenuItem name="Exit">
        <Icon type="ios-paper"></Icon>
        退出系统
      </MenuItem>
    </div>
  </Menu>
</template>
<script>
import * as API from './API.js'
import axios from 'axios'
export default {
  name: 'MenuTop',
  data () {
    return {
      name: '',
      menuTop: 'Main'
    }
  },
  created: function () {
    axios.get(API.getName).then(res => {
      this.name = res.data.toString()
    }).catch(res => {
      this.$Loading.error()
      this.$Notice.error({
        title: '服务器内部错误，无法获取用户姓名!'
      })
    })
    axios.get(API.getDepartment).then(res => {
      localStorage.setItem("departmentList",res.data.toString());
    }).catch(res => {
      this.$Loading.error()
      this.$Notice.error({
        title: '服务器内部错误，无法获取部门列表!'
      })
    })
    axios.get(API.getBank).then(res => {
      localStorage.setItem("bankList",res.data.toString());
    }).catch(res => {
      this.$Loading.error()
      this.$Notice.error({
        title: '服务器内部错误，无法获取银行列表!'
      })
    })
    axios.get(API.getType).then(res => {
      localStorage.setItem("typeList",res.data.toString());
    }).catch(res => {
      this.$Loading.error()
      this.$Notice.error({
        title: '服务器内部错误，无法获取证件类型列表!'
      })
    })
  },
  methods: {
    onSelect (name) {
      this.menuTop = name
      if (name.toString() === 'Exit') {
        window.location.href = '/logout'
      }
      if (name.toString() === 'Statistics') {
        window.open('/statistics/get')
      }
    }
  }
}
</script>

<style scoped>
  .layout-logo{
    width: 1px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
  }
  .layout-nav{
    width: 1200px;
    margin: 0 auto;
    margin-right: 20px;
  }
</style>
