<template>
  <div>
    <Row :style="{margin: '5px 1px 0px 1px'}">
      <Col span="4">
        <Select v-model="queryStr.department_id" placeholder="请选择所属部门" prefix="md-easel">
          <Option v-for="item in departmentList" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </Col>
      <Col span="4">
        <Select v-model="queryStr.bank_id" placeholder="请选择放款银行" prefix="md-cube">
          <Option v-for="item in bankList" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </Col>
      <Col span="4">
        <Select v-model="queryStr.type_id" placeholder="请选择申请类型" prefix="md-swap">
          <Option v-for="item in typeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </Col>
      <Col span="4">
        <Select v-model="queryStr.state" placeholder="请选择贷款状态" prefix="ios-bookmark">
          <Option value="1">未处理</Option>
          <Option value="2">被退回</Option>
          <Option value="3">已审批</Option>
          <Option value="4">已放款</Option>
          <Option value="5">已还款</Option>
        </Select>
      </Col>
      <Col span="4">
        <Select v-model="queryStr.money" placeholder="请输入贷款金额" prefix="logo-yen">
          <Option value="1">1万元</Option>
          <Option value="2">2万元</Option>
          <Option value="3">3万元</Option>
          <Option value="4">4万元</Option>
          <Option value="5">5万元</Option>
          <Option value="6">6万元</Option>
          <Option value="7">7万元</Option>
          <Option value="8">8万元</Option>
          <Option value="9">9万元</Option>
          <Option value="10">10万元</Option>
          <Option value="11">11万元</Option>
          <Option value="12">12万元</Option>
          <Option value="13">13万元</Option>
          <Option value="14">14万元</Option>
          <Option value="15">15万元</Option>
          <Option value="16">16万元</Option>
          <Option value="17">17万元</Option>
          <Option value="18">18万元</Option>
          <Option value="19">19万元</Option>
          <Option value="20">20万元</Option>
        </Select>
      </Col>
      <Col span="4">
        <Select v-model="queryStr.month" placeholder="请输入贷款期限" prefix="md-calendar">
          <Option value="1">1月</Option>
          <Option value="2">2月</Option>
          <Option value="3">3月</Option>
          <Option value="4">4月</Option>
          <Option value="5">5月</Option>
          <Option value="6">6月</Option>
          <Option value="7">7月</Option>
          <Option value="8">8月</Option>
          <Option value="9">9月</Option>
          <Option value="10">10月</Option>
          <Option value="11">11月</Option>
          <Option value="12">12月</Option>
          <Option value="13">13月</Option>
          <Option value="14">14月</Option>
          <Option value="15">15月</Option>
          <Option value="16">16月</Option>
          <Option value="17">17月</Option>
          <Option value="18">18月</Option>
          <Option value="19">19月</Option>
          <Option value="20">20月</Option>
          <Option value="21">21月</Option>
          <Option value="22">22月</Option>
          <Option value="23">23月</Option>
          <Option value="24">24月</Option>
        </Select>
      </Col>
    </Row>
    <Row :style="{margin: '0px 1px 5px 1px'}">
      <Col span="4">
        <Input type="text" v-model="queryStr.name" placeholder="请输入人员姓名" prefix="md-person" clearable="true"></Input>
      </Col>
      <Col span="4">
        <Input type="text" v-model="queryStr.number" placeholder="请输入证件号码" prefix="md-browsers" clearable="true"></Input>
      </Col>
      <Col span="4">
        <Input type="text" v-model="queryStr.phone" placeholder="请输入联系电话" prefix="md-phone-portrait" clearable="true"></Input>
      </Col>
      <Col span="8">
        <Input type="text" v-model="queryStr.business" placeholder="请输入企业名称" prefix="md-albums" clearable="true"></Input>
      </Col>
      <Col span="4">
        <Button-group>
          <Button type="info" @click="goQuery">
            <Icon type="md-search"></Icon>
            搜索
          </Button>
          <Button @click="goRefresh">
            <Icon type="md-refresh"></Icon>
            重置（务必点两次）
          </Button>
        </Button-group>
      </Col>
    </Row>
    <Table
      highlight-row
      @on-row-dblclick="onRowDblclick"
      :loading="loading"
      :context="self"
      :border="border"
      :stripe="stripe"
      :size="size"
      :columns="columns"
      :data="pageList">
      <template slot-scope="{ row }" slot="name">
        {{row.name}}
      </template>
      <template slot-scope="{ row }" slot="number">
        {{row.number}}
      </template>
      <template slot-scope="{ row }" slot="department">
        {{row.department}}
      </template>
      <template slot-scope="{ row }" slot="state">
        {{row.state}}
      </template>
      <template slot-scope="{ row }" slot="bank">
        {{row.bank}}
      </template>
      <template slot-scope="{ row }" slot="type">
        {{row.type}}
      </template>
      <template slot-scope="{ row }" slot="time">
        {{row.time}}
      </template>
      <template slot-scope="{ row }" slot="money">
        {{row.money}}
      </template>
      <template slot-scope="{ row }" slot="month">
        {{row.month}}
      </template>
      <template slot-scope="{ row }" slot="base">
        {{row.base}}
      </template>
      <template slot-scope="{ row }" slot="business">
        {{row.business}}
      </template>
      <template slot-scope="{ row }" slot="address">
        {{row.address}}
      </template>
      <template slot-scope="{ row }" slot="project">
        {{row.project}}
      </template>
      <template slot-scope="{ row }" slot="sex">
        {{row.sex}}
      </template>
      <template slot-scope="{ row }" slot="birth">
        {{row.birth}}
      </template>
      <template slot-scope="{ row }" slot="marriage">
        {{row.marriage}}
      </template>
      <template slot-scope="{ row }" slot="phone">
        {{row.phone}}
      </template>
      <template slot-scope="{ row }" slot="spouse">
        {{row.spouse}}
      </template>
      <template slot-scope="{ row }" slot="phone2">
        {{row.phone2}}
      </template>
      <template slot-scope="{ row }" slot="code">
        {{row.code}}
      </template>
      <template slot-scope="{ row }" slot="startTime">
        {{row.startTime}}
      </template>
      <template slot-scope="{ row }" slot="endTime">
        {{row.endTime}}
      </template>
      <template slot-scope="{ row, index }" slot="action" >
        <Button type="primary" size="small" style="margin-right: 5px" @click="goEdit(index)">修改</Button>
      </template>
    </Table>
    <div class="right">
      <Page
        :total="pageTotal"
        :current="pageCurrent"
        :page-size="pageSize"
        @on-page-size-change="onPageSizeChange"
        @on-change="onChange"
        show-sizer
        show-elevator
        show-total>
      </Page>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
import * as API from './API.js'

export default {
  name: 'list',
  data () {
    return {
      queryURL: API.query,
      totalURL: API.total,
      pageTotal: 0,
      pageCurrent: 1,
      pageSize: 10,
      pageList: [],
      loading: true,
      border: true,
      stripe: true,
      size: 'small',
      height: 450,
      self: this,
      departmentList: [],
      bankList: [],
      typeList: [],
      queryStr: {
        department_id: '',
        bank_id: '',
        type_id: '',
        state: '',
        money: '',
        month: '',
        name: '',
        number: '',
        phone: '',
        business: ''
      },
      columns: [
        {
          title: '人员姓名',
          key: 'name',
          slot: 'name',
          width: 150,
          tooltip: true,
          resizable: true,
          fixed: 'left'
        },
        {
          title: '证件号码',
          key: 'number',
          width: 180,
          tooltip: true,
          resizable: true,
          fixed: 'left'
        },
        {
          title: '所属部门',
          key: 'department',
          width: 180,
          tooltip: true,
          resizable: true,
          fixed: 'left'
        },
        {
          title: '贷款状态',
          key: 'state',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '放款银行',
          key: 'bank',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '申请类型',
          key: 'type',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '申报时间',
          key: 'time',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '贷款金额',
          key: 'money',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '贷款期限',
          key: 'month',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '贴息比例',
          key: 'base',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '公司名称',
          key: 'business',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '经营场所',
          key: 'address',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '经营项目',
          key: 'project',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '性别',
          key: 'sex',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '出生日期',
          key: 'birth',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '婚姻状况',
          key: 'marriage',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '联系电话',
          key: 'phone',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '配偶姓名',
          key: 'spouse',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '配偶电话',
          key: 'phone2',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '客户编号',
          key: 'code',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '开始日期',
          key: 'startTime',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '结束日期',
          key: 'endTime',
          tooltip: true,
          resizable: true,
          width: 180
        },
        {
          title: '操作',
          slot: 'action',
          align: 'center',
          width: 180,
          tooltip: true,
          resizable: true,
          fixed: 'right',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.goEdit(params.index)
                  }
                }
              }, '修改')
            ])
          }
        }
      ]
    }
  },
  created: function () {
    this.departmentList = this.evil('(' + localStorage.getItem('departmentList') + ')')
    this.bankList = this.evil('(' + localStorage.getItem('bankList') + ')')
    this.typeList = this.evil('(' + localStorage.getItem('typeList') + ')')
    this.getLists(this.$store.state.queryStr, this.$store.state.pageCurrent, this.$store.state.pageSize)
  },
  methods: {
    // 一个变量指向Function，防止有些前端编译工具报错
    evil (fn) {
      let Fn = Function
      return new Fn('return ' + fn)()
    },
    getLists (queryStr, pageCurrent, pageSize) {
      this.loading = true
      axios.get(this.queryURL, {
        params: {
          queryStr: queryStr,
          pageCurrent: pageCurrent,
          pageSize: pageSize
        }
      }).then(res => {
        axios.get(this.totalURL, {
          params: {
            queryStr: queryStr
          }
        }).then(response => {
          this.pageList = res.data
          this.pageTotal = response.data
          this.loading = false
        }).catch(response => {
          this.$Loading.error()
          this.$Notice.error({
            title: '服务器内部错误,无法获取数量!'
          })
          this.loading = false
        })
      }).catch(res => {
        this.$Loading.error()
        this.$Notice.error({
          title: '服务器内部错误，无法获取列表数据!'
        })
        this.loading = false
      })
    },
    // 切换每页条数
    onPageSizeChange (value) {
      this.pageSize = value
      this.pageCurrent = 1
      this.$store.commit('setPageSize', {
        pageSize: value
      })
      this.$store.commit('setPageCurrent', {
        pageCurrent: 1
      })
      this.getLists(this.$store.state.queryStr, this.$store.state.pageCurrent, this.$store.state.pageSize)
    },
    // 切换页码
    onChange (value) {
      this.pageCurrent = value
      this.$store.commit('setPageCurrent', {
        pageCurrent: value
      })
      this.getLists(this.$store.state.queryStr, this.$store.state.pageCurrent, this.$store.state.pageSize)
    },
    goQuery () {
      this.pageCurrent = 1
      this.$store.commit('setQueryStr', {
        queryStr: this.queryStr
      })
      this.$store.commit('setPageCurrent', {
        pageCurrent: 1
      })
      this.getLists(this.$store.state.queryStr, this.$store.state.pageCurrent, this.$store.state.pageSize)
    },
    // 不知道为什么的BUG，重置必须点击两遍
    goRefresh () {
      this.queryStr = {
      department_id: '',
      bank_id: '',
      type_id: '',
      state: '',
      money: '',
      month: '',
      name: '',
      number: '',
      phone: '',
      business: ''
    }
      this.pageCurrent = 1
      this.$store.commit('setQueryStr', {
        queryStr: {
          department_id: '',
          bank_id: '',
          type_id: '',
          state: '',
          money: '',
          month: '',
          name: '',
          number: '',
          phone: '',
          business: ''
        }
      })
      this.$store.commit('setPageCurrent', {
        pageCurrent: 1
      })
      this.getLists(this.$store.state.queryStr, this.$store.state.pageCurrent, this.$store.state.pageSize)
    },
    onRowDblclick (row, index) {
      this.$Modal.info({
        title: `贷款信息---ID:${this.pageList[index].id}`,
        content: `人员姓名：${this.pageList[index].name}<br>
                  证件号码：${this.pageList[index].number}<br>
                  所属部门：${this.pageList[index].department}<br>
                  贷款状态：${this.pageList[index].state}<br>
                  放款银行：${this.pageList[index].bank}<br>
                  申请类型：${this.pageList[index].type}<br>
                  申报时间：${this.pageList[index].time}<br>
                  贷款金额：${this.pageList[index].money}<br>
                  贷款期限：${this.pageList[index].month}<br>
                  贴息比例：${this.pageList[index].base}<br>
                  公司名称：${this.pageList[index].business}<br>
                  经营场所：${this.pageList[index].address}<br>
                  经营项目：${this.pageList[index].project}<br>
                  人员性别：${this.pageList[index].sex}<br>
                  出生日期：${this.pageList[index].birth}<br>
                  婚姻状况：${this.pageList[index].marriage}<br>
                  联系电话：${this.pageList[index].phone}<br>
                  配偶姓名：${this.pageList[index].spouse}<br>
                  配偶电话：${this.pageList[index].phone2}`
      })
    },
    goEdit (index) {
      this.$router.push({ path: '/Loan/Edit/' + this.pageList[index].id })
    }
  }
}
</script>
<style scoped>
  .right{
    float: right;
    margin: 15px;
  }
</style>
