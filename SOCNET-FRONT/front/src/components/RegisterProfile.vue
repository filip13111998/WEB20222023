<template>
  <div class="hello">
    <register-menu></register-menu>
    <div style="width: 20%;margin-left:40%;margin-top:15%">
      <b-form @submit="onSubmit">
        <b style="color: rgb(224, 184, 25);">USER PROFILE</b>

        <!-- <b-form-group style="margin-top: 1em;" id="input-group-0" label-for="input-0">
          <b-form-input
            id="input-1"
            v-model="form.image"
            placeholder="Enter Base64 Image"
            type="text"
            @input="updateImage"
          ></b-form-input>
        </b-form-group> -->
        <div style="margin-top: 1em;">
          <img style="width: 10em;height:10em;" :src="form.image" alt="Image" />
        </div>

        <!-- <b-form-group style="margin-top: 1em;" id="input-group-1" label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="form.username"
            placeholder="Enter Username"
            type="text"
            disable
          ></b-form-input>
        </b-form-group> -->

        <b-form-group style="margin-top:1em;" id="input-group-1" label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="form.oldpassword"
            placeholder="Enter Old Password"
            type="password"

          ></b-form-input>
        </b-form-group>

       <b-form-group style="margin-top:1em;" id="input-group-2" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="form.newpassword"
            placeholder="Enter New Password"
            type="password"

          ></b-form-input>
        </b-form-group>

        <b-form-group style="margin-top:1em;" id="input-group-2c" label-for="input-2c">
          <b-form-input
            id="input-2c"
            v-model="form.confirmpassword"
            placeholder="Enter Confirm Pass"
            type="password"

          ></b-form-input>
        </b-form-group>
        <b style="color: red;" v-if="msg">DIFFERENT PASSWORDS!</b>

        <b-form-group style="margin-top:1em;" id="input-group-3" label-for="input-3">
          <b-form-input
            id="input-3"
            v-model="form.email"
            placeholder="Enter Email"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

       =<b-form-group style="margin-top:1em;" id="input-group-4" label-for="input-4">
          <b-form-input
            id="input-4"
            v-model="form.firstName"
            placeholder="Enter First Name"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group style="margin-top:1em;" id="input-group-5" label-for="input-5">
          <b-form-input
            id="input-5"
            v-model="form.lastName"
            placeholder="Enter Last Name"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

       <b-form-group style="margin-top:1em;" id="input-group-6" label-for="input-6">
          <b-form-input
            id="input-6"
            v-model="form.dateOfBrith"
            placeholder="Enter Date Of Brith"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group style="margin-top:1em;" id="input-group-7" label="Gender:" label-for="input-7">
          <b-form-select
            id="input-7"
            v-model="form.gender"
            :options="genders"
            required
          ></b-form-select>
        </b-form-group>

        <b-form-group style="margin-top:1em;" id="input-group-8" label="Private:" label-for="input-8">
          <b-form-select
            id="input-8"
            v-model="form.isPrivate"
            :options="privates"
            type="text"
            required
          ></b-form-select>
        </b-form-group>

        <b-button style="margin-top:1em;" type="submit" variant="success">UPDATE</b-button>

      </b-form>

    </div>

    <b-form @submit="addPost" style="width: 20%;margin-left:40%;margin-top:5em">
      <b style="color: rgb(113, 113, 236);">NEW POST</b>
      <b-form-group
        id="input-group-1"
        label-for="input-1"
        style="margin-top: 1em;"
      >
        <b-form-input
          id="input-1"
          v-model="newpost.text"
          type="text"
          placeholder="Enter text"
          required
        ></b-form-input>
      </b-form-group>
      <input type="file" @change="handleFileUpload">
      <!-- <b-form-group id="input-group-2" label="Your Password:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="newpost.base64"
          placeholder="Enter Password"
          type="password"
          required
        ></b-form-input>
      </b-form-group> -->
      <!-- <b style="color: red;" v-if="msg">WRONG CREDENTIALS!</b> -->
      <b-button style="margin-top:1em;" type="submit" variant="primary">CREATE</b-button>
      <!-- <b-button type="reset" variant="danger">Reset</b-button> -->
    </b-form>

    <div>
      <div style="margin-top:1em;" v-for="p in posts" :key="p.id">
        <router-link :to="`/friend-post/${p.uuid}`">View Details</router-link>
        <b-button @click="removeMyPost(p.uuid)" pill style="margin-top:1em;" variant="danger">DELETE</b-button>
        <b-button @click="profilePic(p.uuid)" pill style="margin-top:1em;" variant="info">SET AS PROFILE</b-button>

        <b>{{ p.uuid }} </b>
        <b>{{ p.username }} </b>
        <b>{{ p.text }} </b>
        <div style="margin-top: 1em;">
          <img style="width: 10em; height: 10em;" :src="'data:image/png;base64,' + p.image" alt="Image" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import RegisterMenu from './RegisterMenu.vue'
export default {
  name: 'RegisterProfile',
  components: {
    RegisterMenu
  },
  data () {
    return {
      msg: false,
      profile: null,
      form: {
        image: '',
        oldpassword: '',
        newpassword: '',
        confirmpassword: '',
        email: '',
        firstName: '',
        lastName: '',
        dateOfBrith: '',
        gender: null,
        isPrivate: null
      },
      genders: ['M', 'F'],
      privates: [true, false],
      posts: null,
      newpost: {
        image: '',
        base64: '',
        text: ''
      }
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
      var self = this
      var xhr = new XMLHttpRequest()
      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/user/getData', true)
      xhr.setRequestHeader('Content-type', 'application/json')
      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))
      xhr.onload = function () {
        if (xhr.status === 200) {
          // Request was successful
          var responseData = JSON.parse(xhr.responseText)
          self.profile = responseData
          self.form.image = 'data:image/png;base64,' + responseData.profilImage
          self.form.password = responseData.password
          self.form.email = responseData.email
          self.form.firstName = responseData.firstName
          self.form.lastName = responseData.lastName
          self.form.dateOfBrith = responseData.dateOfBrith
          self.form.gender = responseData.gender
          self.form.isPrivate = responseData.priv

          console.log(responseData) // Output the response data
          let usern = JSON.parse(atob(localStorage.getItem('token').split('.')[1]))['username']
          var self2 = self
          xhr.open('GET', 'http://localhost:8083/SOCNET/rest/post/getAll/' + usern, true)
          xhr.setRequestHeader('Content-type', 'application/json')
          xhr.onload = function () {
            if (xhr.status === 200) {
              self2.posts = JSON.parse(xhr.responseText)
              console.log(self2.posts)
            }
          }
          xhr.send()
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

      this.msg = false
      // const data = JSON.stringify(this.form)
      if (this.form.newpassword !== this.form.confirmpassword) {
        this.msg = true
        return
      }

      const data = {
        password: this.form.newpassword,
        email: this.form.email,
        firstName: this.form.firstName,
        lastName: this.form.lastName,
        dateOfBrith: this.form.dateOfBrith,
        gender: this.form.gender,
        isPrivate: this.form.isPrivate
      }
      if (this.form.newpassword === '') {
        data.password = this.profile.password
      }

      var xhr = new XMLHttpRequest()
      xhr.open('POST', 'http://localhost:8083/SOCNET/rest/user/update', true)
      xhr.setRequestHeader('Content-type', 'application/json')
      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))
      xhr.onload = function () {
        if (xhr.status === 200) {
          // Request was successful
          var responseData = JSON.parse(xhr.responseText)
          if (responseData === true) {
            window.alert('PROFILE UPDATED SUCCESSFULLY')
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
    removeMyPost (uuid) {
      let self = this

      var xhr = new XMLHttpRequest()

      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/post/delete/' + uuid, true)

      xhr.setRequestHeader('Content-type', 'application/json')

      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))

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
      xhr.send()
    },
    profilePic (uuid) {
      let self = this

      var xhr = new XMLHttpRequest()

      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/post/profile/' + uuid, true)

      xhr.setRequestHeader('Content-type', 'application/json')

      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))

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
      xhr.send()
    },
    addPost (event) {
      event.preventDefault()

      let self = this

      const data = {
        image: this.newpost.image,
        base64: 'data:image/png;base64,' + this.newpost.base64,
        text: this.newpost.text
      }
      console.log(data) // Output the
      var xhr = new XMLHttpRequest()

      xhr.open('POST', 'http://localhost:8083/SOCNET/rest/post/save', true)

      xhr.setRequestHeader('Content-type', 'application/json')

      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))

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
      xhr.send(JSON.stringify(data))
    },
    handleFileUpload (event) {
      const file = event.target.files[0]
      const reader = new FileReader()

      reader.onload = () => {
        this.newpost.base64 = reader.result.split(',')[1] // Extract base64 data
      }

      reader.readAsDataURL(file) // Convert file to base64 data

      this.newpost.image = file.name // Assign the file name to the 'imageName' property
    }

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
