<template>
  <div class="hello">
    <register-menu v-if="!isAdmin()"></register-menu>
    <admin-menu v-if="isAdmin()"></admin-menu>
    <div>
      <div style="margin-top:1em;" v-for="u in users" :key="u.id">

        <b>{{ u.username }} </b>
        <router-link :to="`/chat/${u.username}`">CHAT</router-link>
      </div>
    </div>

  </div>
</template>

<script>
import RegisterMenu from './RegisterMenu'
import AdminMenu from './AdminMenu'
export default {
  name: 'ChatUser',
  components: {
    RegisterMenu,
    AdminMenu
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      users: null
    }
  },
  created () {
    // This code will be executed when the component is loaded
    console.log('Component loaded!')
    // You can call your methods or perform any desired operations here
    this.onLoad()
  },
  methods: {
    onLoad () {
      // let usrn = this.$route.params.username
      var self = this
      var xhr = new XMLHttpRequest()

      // let usern = JSON.parse(atob(localStorage.getItem('token').split('.')[1]))['username']
      // console.log(usern)
      // let usr2 = usern !== '' ? usern : '_'
      // console.log(usr2)
      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/message/users', true)
      xhr.setRequestHeader('Content-type', 'application/json')
      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))
      xhr.onload = function () {
        if (xhr.status === 200) {
          // Request was successful
          var responseData = JSON.parse(xhr.responseText)

          self.users = responseData

          console.log(responseData) // Output the response data
        } else {
          // Request failed
          console.error('Request failed with status ' + xhr.status)
        }
      }
      // Send the request
      xhr.send()
    },
    isAdmin () {
      let role = JSON.parse(atob(localStorage.getItem('token').split('.')[1]))['role']
      console.log(role)
      return role === 'ADMIN'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
