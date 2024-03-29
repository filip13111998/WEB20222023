import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import UserHome from '@/components/UserHome'
import RegisterHome from '@/components/RegisterHome'
import RegisterProfile from '@/components/RegisterProfile'
import AdminHome from '@/components/AdminHome'
import FriendProfile from '@/components/FriendProfile'
import FriendPost from '@/components/FriendPost'
import Home from '@/components/Home'
import FriendshipRequests from '@/components/FriendshipRequests'
import Friends from '@/components/Friends'
import Profile from '@/components/Profile'
import ChatUsers from '@/components/ChatUsers'
import Chat from '@/components/Chat'
import DeletePost from '@/components/DeletePost'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/user-home',
      name: 'user-home',
      component: UserHome
    },
    {
      path: '/register-home',
      name: 'register-home',
      component: RegisterHome
    },
    {
      path: '/friendship-requests',
      name: 'friendship-requests',
      component: FriendshipRequests
    },
    {
      path: '/friends',
      name: 'friends',
      component: Friends
    },
    {
      path: '/chat-users',
      name: 'chat-users',
      component: ChatUsers
    },
    {
      path: '/chat/:username',
      name: 'chat',
      component: Chat
    },
    {
      path: '/register-profile',
      name: 'register-profile',
      component: RegisterProfile
    },
    {
      path: '/profile/:username',
      name: 'profile',
      component: Profile
    },
    {
      path: '/friend-profile/:username',
      name: 'friend-profile',
      component: FriendProfile
    },
    {
      path: '/friend-post/:uuid',
      name: 'friend-post',
      component: FriendPost
    },
    {
      path: '/admin-home',
      name: 'admin-home',
      component: AdminHome
    },
    {
      path: '/admin-delete/:uuid/:username',
      name: 'admin-delete',
      component: DeletePost
    }
  ]
})
