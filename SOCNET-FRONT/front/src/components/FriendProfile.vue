<template>
  <div class="hello">
    <register-menu v-if="!isAdmin()"></register-menu>
    <admin-menu v-if="isAdmin()"></admin-menu>
    <!-- <user-menu v-if="tokenExists"></user-menu> -->
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
      <b style="color: red;margin-top:1em;" v-if="form.priv && form.status !== 'ACCEPT'">ACCOUNT IS PRIVATE</b>

    </div>

    <div class="row" style="margin-top:1em;" v-if="!form.priv || form.status == 'ACCEPT'">
      <div class="col-4">
        <!-- Left Div -->
        <b>TOGETHER FRIENDS</b>

          <div style="margin-top:1em;">
            <div style="margin-top:1em;" v-for="u in users" :key="u.id">
              <!-- <b>ID: {{ u.id }} </b> -->
              <b>USERNAME: {{ u.username }} </b>
              <!-- <b>DATE: {{ u.date }} </b> -->
              <!-- <b-button @click="removeFriend(u.username , 'NO')" pill style="margin-top: 1em;"  variant="danger">REMOVE</b-button> -->
              <router-link :to="`/friend-profile/${u.username}`">View Profile</router-link>
            </div>
          </div>

      </div>
      <div class="col-8">
        <!-- Right Div -->
        <div>
          <div style="margin-top:1em;" v-for="p in posts" :key="p.id">
            <router-link :to="`/friend-post/${p.uuid}`">View Details</router-link>

            <b>{{ p.uuid }} </b>
            <b>{{ p.username }} </b>
            <b>{{ p.text }} </b>
            <div style="margin-top: 1em;">
              <img style="width: 10em; height: 10em;" :src="'data:image/png;base64,' + p.image" alt="Image" />
            </div>
            <router-link v-if="isAdmin(p.uuid)" :to="`/admin-delete/${p.uuid}/${p.username}`">Delete Post</router-link>

            <!-- <b-button v-if="isAdmin(p.uuid)" @click="deletePost()" pill style="margin-top: 1em;" >DELETE POST</b-button> -->
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import RegisterMenu from './RegisterMenu.vue'
import UserMenu from './UserMenu.vue'
import AdminMenu from './AdminMenu.vue'
export default {
  name: 'FriendProfile',
  components: {
    RegisterMenu,
    UserMenu,
    AdminMenu
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
      posts: null,
      users: null
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
    this.together()
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
          self.form.priv = responseData.priv
          self.form.status = responseData.status

          var self2 = self
          if (responseData.priv === false || responseData.status === 'ACCEPT') {
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

          console.log(responseData) // Output the response data
        } else {
          // Request failed
          console.error('Request failed with status ' + xhr.status)
        }
      }
      // Send the request
      xhr.send()
    },
    together () {
      var self = this

      var xhr = new XMLHttpRequest()

      let usern = JSON.parse(atob(localStorage.getItem('token').split('.')[1]))['username']
      let usern2 = this.$route.params.username
      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/frendrequest/togetherFriends/' + usern + '/' + usern2, true)

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
