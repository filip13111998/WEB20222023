<template>
  <div class="hello">
    <admin-menu></admin-menu>
    <div style="width: 20%;margin-left:40%;margin-top:15%">
      <b-form @submit="onSubmit">
        <b style="color: rgb(224, 184, 25);">SEARCH USERS</b>

       <b-form-group style="margin-top:1em;" id="input-group-1" label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="form.firstname"
            placeholder="Enter First Name"
            type="text"
          ></b-form-input>
        </b-form-group>

        <b-form-group style="margin-top:1em;" id="input-group-5" label="First Name SORT:" label-for="input-5">
          <b-form-select
            id="input-5"
            v-model="form.firstnamesort"
            :options="sorts"
            required
          ></b-form-select>
        </b-form-group>

        <b-form-group style="margin-top:1em;" id="input-group-2" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="form.lastname"
            placeholder="Enter Last Name"
            type="text"
          ></b-form-input>
        </b-form-group>

        <b-form-group style="margin-top:1em;" id="input-group-6" label="Last Name SORT:" label-for="input-6">
          <b-form-select
            id="input-6"
            v-model="form.lastnamesort"
            :options="sorts"
            required
          ></b-form-select>
        </b-form-group>

       <b-form-group style="margin-top:1em;" id="input-group-3" label-for="input-3">
          <b-form-input
            id="input-3"
            v-model="form.email"
            placeholder="Enter Email"
            type="text"
          ></b-form-input>
        </b-form-group>

        <b-form-group style="margin-top:1em;" id="input-group-7" label="EMAIL SORT:" label-for="input-7">
          <b-form-select
            id="input-7"
            v-model="form.emailsort"
            :options="sorts"
            required
          ></b-form-select>
        </b-form-group>

        <b-button style="margin-top:1em;" type="submit" variant="warning">SEARCH</b-button>
        <!-- <b-button type="reset" variant="danger">Reset</b-button> -->
      </b-form>
      <!-- <b-card class="mt-3" header="Form Data Result">
        <pre class="m-0">{{ form }}</pre>
      </b-card> -->

    </div>

    <div style="margin-top:1em;">
      <div style="margin-top:1em;" v-for="u in users" :key="u.id">
        <b>{{ u.firstName }} </b>
        <b>{{ u.lastName }} </b>
        <b>{{ u.username }} </b>
        <b-button v-if="u.active" pill @click="block(u.username)" style="margin-top:1em;" type="submit" variant="danger">BLOCK</b-button>
        <b-button v-if="!u.active" pill @click="block(u.username)" style="margin-top:1em;" type="submit" variant="success">UNBLOCK</b-button>

        <router-link v-if="hasToken" :to="`/friend-profile/${u.username}`">View Profile</router-link>
        <!-- <router-link  v-if="!hasToken" :to="`/profile/${u.username}`">View Profile</router-link> -->
        <!-- <a href="/friend-profile/"+ u.username>View Profile</a> -->
      </div>
    </div>
  </div>
</template>

<script>
import AdminMenu from './AdminMenu.vue'
export default {
  name: 'RegisterHome',
  components: {
    AdminMenu
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      form: {
        firstname: '',
        firstnamesort: 'NO',
        lastname: '',
        lastnamesort: 'NO',
        email: '',
        emailsort: 'NO'
      },
      sorts: ['YES', 'NO'],
      users: null
    }
  },
  computed: {
    hasToken () {
      // Check if the JWT token exists in localStorage
      return localStorage.getItem('token') !== null
    }
  },
  methods: {
    onSubmit (event) {
      event.preventDefault()
      var self = this
      var xhr = new XMLHttpRequest()
      xhr.open('POST', 'http://localhost:8083/SOCNET/rest/user/admin-search', true)
      xhr.setRequestHeader('Content-type', 'application/json')
      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))
      this.usrmsg = false
      this.msg = false
      // const data = JSON.stringify(this.form)
      if (this.form.password !== this.form.confirmpassword) {
        this.msg = true
        return
      }

      const data = {
        firstname: this.form.firstname,
        firstnamesort: this.form.firstnamesort === 'YES',
        lastname: this.form.lastname,
        lastnamesort: this.form.lastnamesort === 'YES',
        email: this.form.email,
        emailsort: this.form.emailsort === 'YES'
      }

      console.log(data)

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
      xhr.send(JSON.stringify(data))
    },
    block (username) {
      // event.preventDefault()
      var self = this
      var xhr = new XMLHttpRequest()
      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/user/delete/' + username, true)
      xhr.setRequestHeader('Content-type', 'application/json')
      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))

      xhr.onload = function () {
        if (xhr.status === 200) {
          // Request was successful
          var responseData = JSON.parse(xhr.responseText)
          if (responseData) {
            console.log('jes')
            for (let i = 0; i < self.users.length; i++) {
              if (self.users[i].username === username) {
                console.log('ulett')
                self.users[i].active = !self.users[i].active
                break // Exit the loop after finding the matching username
              }
            }
          }
          console.log(responseData) // Output the response data
        } else {
          // Request failed
          console.error('Request failed with status ' + xhr.status)
        }
      }
      // Send the request
      xhr.send()
    }
  }
}
</script>

<!-- <style scoped>

</style> -->
