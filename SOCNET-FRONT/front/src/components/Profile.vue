<template>
  <div class="hello">
    <user-menu></user-menu>
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

      </b-form>
      <b style="color: red;margin-top:1em;" v-if="form.priv">ACCOUNT IS PRIVATE</b>
      <div style="margin-top:1em;" v-if="!form.priv">

          <div style="margin-top:1em;" v-for="p in posts" :key="p.id">
            <b>{{ p.uuid }} </b>
            <b>{{ p.username }} </b>
            <b>{{ p.text }} </b>
            <!-- <img style="width: 10em;height:10em;" /> -->
            <div style="margin-top: 1em;">
              <img style="width: 10em; height: 10em;" :src="'data:image/png;base64,' + p.image" alt="Image" />
            </div>
            <!-- <b>{{ p.image }} </b> -->
            <!-- <router-link v-if="hasToken" :to="`/friend-profile/${u.username}`">View Profile</router-link> -->
            <!-- <router-link  v-if="!hasToken" :to="`/profile/${u.username}`">View Profile</router-link> -->
            <!-- <a href="/friend-profile/"+ u.username>View Profile</a> -->
          </div>

      </div>
    </div>
  </div>
</template>

<script>
import UserMenu from './UserMenu.vue'
export default {
  name: 'UserHome',
  components: {
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
        priv: false,
        status: ''
      },
      posts: null
    }
  },
  created () {
    // This code will be executed when the component is loaded
    console.log('Component loaded!')
    // You can call your methods or perform any desired operations here
    this.onLoad()
  },
  // computed: {
  //   baseConverter (url) {
  //     return function (url) {
  //       return 'data:image/png;base64,' + url
  //     }
  //   }
  // },
  methods: {
    onLoad () {
      let usrn = this.$route.params.username
      var self = this
      var xhr = new XMLHttpRequest()

      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/user/get/' + usrn, true)
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
          self.form.priv = responseData.priv
          self.form.status = responseData.status

          var self2 = self
          if (responseData.priv === false) {
            xhr.open('GET', 'http://localhost:8083/SOCNET/rest/post/getAll/' + usrn, true)
            xhr.setRequestHeader('Content-type', 'application/json')
            xhr.onload = function () {
              if (xhr.status === 200) {
                self2.posts = JSON.parse(xhr.responseText)
                console.log(self2.posts)
              }
            }
            xhr.send()
          }
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

    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
