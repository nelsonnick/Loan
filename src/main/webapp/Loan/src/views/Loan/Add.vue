<template>
  <div>
    <Content :style="{padding: '24px', minHeight: '500px', background: '#fff'}">
      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
        <Row>
          <Col span="8">
            <FormItem label="人员姓名" prop="name">
              <Input type="text" v-model="formValidate.name" placeholder="请输入姓名" :maxlength="20" clearable></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="证件号码" prop="number">
              <Input v-model="formValidate.number" placeholder="请输入18位身份证号码" :maxlength="18" show-word-limit clearable></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="联系电话" prop="phone">
              <Input v-model="formValidate.phone" placeholder="请输入11位手机号码" :maxlength="11" show-word-limit clearable></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="8">
            <FormItem label="婚姻状况" prop="marriage">
              <Select v-model="formValidate.marriage" placeholder="请选择婚姻状况">
                <Option value="1">未婚</Option>
                <Option value="2">已婚</Option>
                <Option value="3">离异</Option>
                <Option value="4">丧偶</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="配偶姓名" prop="spouse">
              <Input type="text" v-model="formValidate.spouse" placeholder="请输入配偶姓名" :maxlength="20" clearable></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="配偶电话" prop="phone2">
              <Input v-model="formValidate.phone2" placeholder="请输入11位手机号码" :maxlength="11" show-word-limit clearable></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="8">
            <FormItem label="所属部门" prop="department">
              <Select v-model="formValidate.department_id" placeholder="请选择所属部门">
                <Option v-for="item in departmentList" :value="item.value" :key="item.value">{{ item.label }}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="办理银行" prop="bank">
              <Select v-model="formValidate.bank_id" placeholder="请选办理银行">
                <Option v-for="item in bankList" :value="item.value" :key="item.value">{{ item.label }}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="申请类型" prop="type">
              <Select v-model="formValidate.type_id" placeholder="请选择申请类型">
                <Option v-for="item in typeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
              </Select>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="8">
            <FormItem label="公司名称" prop="business">
              <Input v-model="formValidate.business" placeholder="请输入营业执照名称" clearable></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="经营场所" prop="address">
              <Input v-model="formValidate.address" placeholder="请输入经营场所" clearable></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="贷款状态" prop="state">
              <Select v-model="formValidate.state" placeholder="请选择贷款状态">
                <Option value="1">未处理</Option>
                <Option value="2">被退回</Option>
                <Option value="3">已审批</Option>
                <Option value="4">已放款</Option>
                <Option value="5">已回款</Option>
              </Select>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="20">
            <FormItem label="经营项目" prop="project">
              <Input v-model="formValidate.project" placeholder="请输入经营项目" clearable></Input>
            </FormItem>
          </Col>
          <Col span="4">
            <FormItem label="申请时间" prop="base">
              <Input v-model="formValidate.time" placeholder="请输入8位申请日期" maxlength="8" type="number" clearable number/>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="8">
            <FormItem label="贷款金额" prop="money">
              <Input v-model="formValidate.money" placeholder="请输入贷款金额" type="number"  number maxlength="2">
                <span slot="append">万元</span>
              </Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="贷款期限" prop="month">
              <Input v-model="formValidate.month" placeholder="请输入贷款期限" type="number"  number maxlength="2">
                <span slot="append">个月</span>
              </Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="贴息比例" prop="base">
              <Input v-model="formValidate.base" placeholder="请输入贴息比例" type="number"  number maxlength="3">
                <span slot="append">%</span>
              </Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="24">
            <FormItem label="备注信息" prop="remark">
              <Input v-model="formValidate.remark" type="textarea" :autosize="{minRows: 3,maxRows: 4}" placeholder="如有必要，请输入备注信息"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="8">
            &nbsp;
          </Col>
          <Col span="8">
            <FormItem>
              <Button type="primary" @click="goSave('formValidate')" :disabled="dis">新增</Button>
              <Button @click="goReset('formValidate')" style="margin-left: 8px" :disabled="dis">重置</Button>
              <Button type="dashed" style="margin-left: 8px" @click="goBack" :disabled="dis">返回</Button>
            </FormItem>
          </Col>
          <Col span="8">
            &nbsp;
          </Col>
        </Row>
      </Form>
    </Content>
  </div>
</template>
<script>
import * as API from './API.js'
import axios from 'axios'
import IdentityCodeValid from '../../plugins/checkId'
export default {
  data () {
    const validateName = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入人员姓名'))
      } else if (/^[\u4E00-\u9FA5]{2,8}$/.test(value)) {
        callback()
      } else {
        callback(new Error('人员姓名应为2-8个汉字'))
      }
    }
    const validateNumber = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入18位身份证号码'))
      } else if (/^\d{18}$|^\d{17}(\d|X)$/.test(value)) {
        var tip = IdentityCodeValid(value)
        if (tip === '' || value === '000000000000000000') {
          callback()
        } else {
          callback(new Error(tip))
        }
      } else {
        callback(new Error('身份证号码应为18位，如末尾的X需要大写'))
      }
    }
    const validatePhone = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入11位手机号码'))
      } else if (/^[1][0-9]{10}$/.test(value) || value === '00000000000') {
        callback()
      } else {
        callback(new Error('手机号码应为以1开头的11位数字'))
      }
    }
    return {
      dis: false,
      formValidate: {
        name: '',
        number: '',
        phone: '',
        marriage: '1',
        spouse: '',
        phone2: '',
        department_id: '1',
        bank_id: '1',
        type_id: '1',
        state: '1',
        money: 20,
        month: 24,
        base: 100,
        time: '',
        business: '',
        address: '',
        project: '',
        remark: ''
      },
      ruleValidate: {
        name: [
          { required: true, validator: validateName, trigger: 'blur' }
        ],
        number: [
          { required: true, validator: validateNumber, trigger: 'blur' }
        ],
        phone: [
          { required: true, validator: validatePhone, trigger: 'blur' }
        ],
        business: [
          { required: true, message: '公司名称为必填项' }
        ],
        address: [
          { required: true, message: '经营场所为必填项' }
        ],
        project: [
          { required: true, message: '经营项目为必填项' }
        ],
        marriage: [
          { required: true }
        ],
        // department: [
        //   { required: true }
        // ],
        // bank: [
        //   { required: true }
        // ],
        // type: [
        //   { required: true }
        // ],
        time: [
          { required: true, message: '申请时间为必填项' }
        ],
        money: [
          { required: true, message: '贷款金额为必填项' }
        ],
        month: [
          { required: true, message: '贷款期限为必填项' }
        ],
        base: [
          { required: true, message: '贴息比例为必填项' }
        ]
      }
    }
  },
  created: function () {
    this.departmentList = this.evil('(' + localStorage.getItem('departmentList') + ')')
    this.bankList = this.evil('(' + localStorage.getItem('bankList') + ')')
    this.typeList = this.evil('(' + localStorage.getItem('typeList') + ')')
    this.formValidate.time = this.getYearMonthDate()
  },
  methods: {
    // 一个变量指向Function，防止有些前端编译工具报错
    evil (fn) {
      let Fn = Function
      return new Fn('return ' + fn)()
    },
    goSave (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.dis = true
          this.$Loading.start()
          axios.get(API.add, {
            params: {
              form: this.formValidate
            }
          }).then(res => {
            if (res.data === 'OK') {
              this.$Loading.finish()
              this.$Message.success('新增成功!')
              this.$Notice.success({
                title: '操作完成!',
                desc: '贷款人员：' + this.formValidate.name + '已保存！'
              })
              setTimeout(() => {
                this.$router.push({ path: '/Loan' })
                this.dis = false
                this.$refs[name].resetFields()
              }, 1000)
            } else {
              this.dis = false
              this.$Loading.error()
              this.$Notice.error({
                title: res.data
              })
            }
          }).catch(res => {
            this.dis = false
            this.$Loading.error()
            this.$Notice.error({
              title: '服务器内部错误，无法保存贷款信息!'
            })
          })
        } else {
          this.$Message.error('请核实填写的信息!')
        }
      })
    },
    goReset (name) {
      this.$refs[name].resetFields()
    },
    goBack () {
      this.$router.push({ path: '/Loan' })
    },
    getYearMonthDate () {
      let myDate = new Date()
      let y = myDate.getFullYear() + ''
      let m = myDate.getMonth() + 1
      let d = myDate.getDate()
      let n, t
      if (m< 10) {
        n = '0' + m + ''
      } else {
        n = m + ''
      }
      if (d<10) {
        t = '0' + d + ''
      } else {
        t = d + ''
      }
      return y + n + t + ''
    }
  }
}
</script>

<style scoped>

</style>
