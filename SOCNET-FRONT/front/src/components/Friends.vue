<template>
  <div class="hello">
    <register-menu></register-menu>
    <div style="margin-top:1em;">
      <div style="margin-top:1em;" v-for="u in users" :key="u.id">
        <b>ID: {{ u.id }} </b>
        <b>USERNAME: {{ u.username }} </b>
        <b>DATE: {{ u.date }} </b>
        <b-button @click="removeFriend(u.username , 'NO')" pill style="margin-top: 1em;"  variant="danger">REMOVE</b-button>
        <router-link :to="`/friend-profile/${u.username}`">View Profile</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import RegisterMenu from './RegisterMenu.vue'
export default {
  name: 'UserHome',
  components: {
    RegisterMenu
  },
  created () {
    // This code will be executed when the component is loaded
    console.log('Component loaded!')
    // You can call your methods or perform any desired operations here
    this.onLoad()
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      users: null
    }
  },
  methods: {
    onLoad () {
      var self = this

      var xhr = new XMLHttpRequest()

      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/frendrequest/getFriends', true)

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
    removeFriend (usernm, status) {
      let usern = JSON.parse(atob(localStorage.getItem('token').split('.')[1]))['username']
      // var self = this
      const data = {
        userSent: usern,
        userReceive: usernm,
        status: status
      }
      var xhr = new XMLHttpRequest()
      xhr.open('POST', 'http://localhost:8083/SOCNET/rest/frendrequest/save', true)
      xhr.setRequestHeader('Content-type', 'application/json')
      // xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))
      xhr.onload = function () {
        if (xhr.status === 200) {
          // Request was successful
          var responseData = JSON.parse(xhr.responseText)

          console.log(responseData) // Output the response data
        } else {
          // Request failed
          console.error('Request failed with status ' + xhr.status)
        }
      }
      // Send the request
      xhr.send(JSON.stringify(data))
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
