<template>
  <div class="hello">
    <register-menu v-if="!isAdmin()"></register-menu>
    <admin-menu v-if="isAdmin()"></admin-menu>
    <div>
      <div style="margin-top:1em;">
        <div style="margin-top:1em;" v-for="m in messages" :key="m.id">

          <b>SENT:{{ m.userSent }} </b>
          <b>RECEIVE:{{ m.userReceive }} </b>
          <b v-if="m.admin" style="color: red;">TEXT:{{ m.text }} </b>
          <b v-if="!m.admin" style="color: green;">TEXT:{{ m.text }} </b>
          <b>DATE:{{ m.dateCreated }} </b>

        </div>
      </div>
    </div>

    <div style="width: 20%;margin-left:40%;margin-top:15%">
      <b-form @submit="onSubmit">
        <b-form-group
          id="input-group-1"
          label-for="input-1"
          style="margin-top: 1em;"
        >
          <b-form-input
            id="input-1"
            v-model="form.text"
            type="text"
            placeholder="Enter Message"
            required
          ></b-form-input>
        </b-form-group>
        <b-button style="margin-top:1em;" type="submit" variant="success">SEND</b-button>
        <!-- <b-button type="reset" variant="danger">Reset</b-button> -->
      </b-form>
  </div>
  </div>
</template>

<script>
import RegisterMenu from './RegisterMenu'
import AdminMenu from './AdminMenu'
export default {
  name: 'Chat',
  components: {
    RegisterMenu,
    AdminMenu
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      form: {
        text: ''
      },
      messages: null
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
      let userReceive1 = this.$route.params.username

      var self = this

      var xhr = new XMLHttpRequest()

      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/message/user-messages/' + userReceive1, true)

      xhr.setRequestHeader('Content-type', 'application/json')

      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))

      xhr.onload = function () {
        if (xhr.status === 200) {
          // Request was successful
          var responseData = JSON.parse(xhr.responseText)
          self.messages = responseData
          console.log(responseData) // Output the response data
        } else {
          // Request failed
          console.error('Request failed with status ' + xhr.status)
        }
      }
      // Send the request
      xhr.send()
    },
    onSubmit (event) {
      event.preventDefault()
      let self = this

      let userReceive1 = this.$route.params.username

      var xhr = new XMLHttpRequest()

      xhr.open('POST', 'http://localhost:8083/SOCNET/rest/message/save', true)

      xhr.setRequestHeader('Content-type', 'application/json')

      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))

      const data = {
        userReceive: userReceive1,
        text: this.form.text
      }

      xhr.onload = function () {
        if (xhr.status === 200) {
          // Request was successful
          var responseData = JSON.parse(xhr.responseText)
          if (responseData) {
            self.onLoad()
          }
          console.log(responseData) // Output the response data
        } else {
          // Request failed
          console.error('Request failed with status ' + xhr.status)
        }
      }
      // Send the request
      this.form.text = ''
      xhr.send(JSON.stringify(data))
    },
    isAdmin () {
      let role = JSON.parse(atob(localStorage.getItem('token').split('.')[1]))['role']
      return role === 'ADMIN'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
