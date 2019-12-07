import Vue from 'vue'
import Router from 'vue-router'
import List from '../src/views/Loan/List'
import Add from '../src/views/Loan/Add'
import Edit from '../src/views/Loan/Edit'

Vue.use(Router)
let baseUrl = ''
export default new Router({
  mode: 'hash',
  // mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    { path: baseUrl + '/', component: () => import('./views/Welcome.vue') },
    { path: baseUrl + '/Loan', component: List },
    { path: baseUrl + '/Loan/List', component: List },
    { path: baseUrl + '/Loan/Add', component: Add },
    { path: baseUrl + '/Loan/Edit/:id', component: Edit }
  ]
})
