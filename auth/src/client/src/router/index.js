import { createRouter, createWebHistory } from 'vue-router'
import Main from '@/components/MainPage.vue'
import Login from '@/components/LoginPage.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Main
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
