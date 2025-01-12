import Vue from 'vue'
import VueRouter from 'vue-router'
// import HomeView from '../views/HomeView.vue'
import homepageView from '@/views/homepageView.vue'
import JiemianView from '@/views/jiemianView.vue'
import loginView from '@/views/loginView.vue'
import wuweiqieyue from '@/views/WuWeiQieYue.vue'
import ZuCe from '@/views/ZuCe.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: loginView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/homepageView',
    name: 'homepageView',
    component: homepageView
  },
  {
    path: '/jiemianView',
    name: 'jiemianView',
    component: JiemianView
  },
  {
    path: '/wuweiqieyue',
    name: 'wuweiqieyue',
    component: wuweiqieyue
  },
  {
    path: '/zuce',
    name: 'zuce',
    component: ZuCe
  }
]

const router = new VueRouter({
  routes
})

export default router
