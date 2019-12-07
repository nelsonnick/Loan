import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
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
    pageCurrent: 1,
    pageSize: 10
  },
  mutations: {
    setQueryStr (state, payload) {
      state.queryStr = payload.queryStr
    },
    setPageCurrent (state, payload) {
      state.pageCurrent = payload.pageCurrent
    },
    setPageSize (state, payload) {
      state.pageSize = payload.pageSize
    }
  },
  actions: {

  }
})
