<template>
  <div class="hello">
    <register-menu v-if="!tokenExists"></register-menu>
    <user-menu v-if="tokenExists"></user-menu>
    <div style="width: 20%;margin-left:40%;margin-top:5%">
      <b-form @submit="onSubmit">
        <b style="color: rgb(224, 184, 25);">USER PROFILE</b>

        <div style="margin-top: 1em;">
          <img style="width: 10em;height:10em;" :src="form.image" alt="Image" />
        </div>

        <b-form-group style="margin-top:1em;" id="input-group-0" label-for="input-0">
          <b>USERNAME</b>
          <b-form-input
            id="input-0"
            v-model="form.username"
            placeholder="Enter Username"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group style="margin-top: 1em;" id="input-group-1" label-for="input-1">
          <b>FIRST NAME</b>
          <b-form-input
            id="input-1"
            v-model="form.firstName"
            placeholder="Enter First Name"
            type="text"
            disable
          ></b-form-input>
        </b-form-group>

        <b-form-group style="margin-top:1em;" id="input-group-2" label-for="input-2">
          <b>LAST NAME</b>
          <b-form-input
            id="input-2"
            v-model="form.lastName"
            placeholder="Enter Last Name"
            type="text"
            disable
          ></b-form-input>
        </b-form-group>

       <b-form-group style="margin-top:1em;" id="input-group-3" label-for="input-3">
          <b>DATE OF BIRTH</b>
          <b-form-input
            id="input-3"
            v-model="form.dateOfBrith"
            placeholder="Enter Date Of Brith"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <b-button @click="addOrRemoveFriend" pill style="margin-top: 1em;" >{{ this.form.status }}</b-button>
      </b-form>

    </div>
  </div>
</template>

<script>
import RegisterMenu from './RegisterMenu.vue'
import UserMenu from './UserMenu.vue'
export default {
  name: 'FriendProfile',
  components: {
    RegisterMenu,
    UserMenu
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      form: {
        username: '',
        firstName: '',
        lastName: '',
        dateOfBrith: '',
        image: '',
        isPriv: false,
        status: ''
      }
    }
  },
  computed: {
    tokenExists () {
      return localStorage.getItem('token') !== null
    }
    // buttonText () {
    //   if (this.form.status === 'WAIT') {
    //     return 'WAIT'
    //   }
    //   return this.form.isFriend ? 'DELETE' : 'ADD'
    // }
  },
  created () {
    // This code will be executed when the component is loaded
    console.log('Component loaded!')
    // You can call your methods or perform any desired operations here
    this.onLoad()
  },
  filters: {
    formatDate (timestamp) {
      const date = new Date(timestamp)
      return date.toLocaleString() // Adjust the format based on your requirements
    }
  },
  methods: {
    onLoad () {
      let usrn = this.$route.params.username
      var self = this
      var xhr = new XMLHttpRequest()

      let usern = JSON.parse(atob(localStorage.getItem('token').split('.')[1]))['username']
      console.log(usern)
      let usr2 = usern !== '' ? usern : '_'
      console.log(usr2)
      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/user/get/' + usrn + '/' + usr2, true)
      xhr.setRequestHeader('Content-type', 'application/json')
      // xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))
      xhr.onload = function () {
        if (xhr.status === 200) {
          // Request was successful
          var responseData = JSON.parse(xhr.responseText)
          // self.profile = responseData
          self.form.image = 'data:image/png;base64,' + responseData.profilImage
          // self.form.password = responseData.password
          self.form.username = responseData.username
          self.form.firstName = responseData.firstName
          self.form.lastName = responseData.lastName
          self.form.dateOfBrith = responseData.dateOfBrith
          self.form.isPriv = responseData.isPriv
          self.form.status = responseData.status
          // self.form.gender = responseData.gender
          // self.form.isPrivate = responseData.priv
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

    },
    addOrRemoveFriend (event) {
      let usern = JSON.parse(atob(localStorage.getItem('token').split('.')[1]))['username']
      var self = this
      const data = {
        userSent: usern,
        userReceive: this.form.username,
        status: self.form.status === 'NO' ? 'WAIT' : 'NO'
      }
      var xhr = new XMLHttpRequest()
      xhr.open('POST', 'http://localhost:8083/SOCNET/rest/frendrequest/save', true)
      xhr.setRequestHeader('Content-type', 'application/json')
      // xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))
      xhr.onload = function () {
        if (xhr.status === 200) {
          // Request was successful
          var responseData = JSON.parse(xhr.responseText)
          if (responseData) {
            // window.alert('jeaa')
            self.form.status = self.form.status === 'WAIT' || self.form.status === 'ACCEPT' ? 'NO' : 'WAIT'
          }
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
