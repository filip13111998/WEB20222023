<template>
  <div class="hello">
    <register-menu ></register-menu>
    <div>
      <div style="margin-top:1em;">
        <b>{{ post.uuid }} </b>
        <b>{{ post.username }} </b>
        <b>{{ post.text }} </b>
        <div style="margin-top: 1em;">
          <img style="width: 10em; height: 10em;" :src="'data:image/png;base64,' + post.image" alt="Image" />
        </div>
        <div style="margin-top:1em;" v-for="c in post.comments" :key="c.id">

          <b>{{ c.text }} </b>
          <b>{{ c.username }} </b>
          <b>{{ c.dateCreated }} </b>
          <div v-if="checkUser(c.username)">
            <b-button @click="removeMyComment(c.uuid)" pill style="margin-top: 1em;" variant="danger">REMOVE</b-button>
          </div>
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
            v-model="form.comment"
            type="text"
            placeholder="Enter Comment"
            required
          ></b-form-input>
        </b-form-group>
        <b-button style="margin-top:1em;" type="submit" variant="primary">LEAVE COMMENT</b-button>
        <!-- <b-button type="reset" variant="danger">Reset</b-button> -->
      </b-form>
  </div>

  </div>
</template>

<script>
import RegisterMenu from './RegisterMenu.vue'
export default {
  name: 'FriendPost',
  components: {
    RegisterMenu
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      form: {
        comment: ''
      },
      post: null
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
      let uuid = this.$route.params.uuid

      var self = this

      var xhr = new XMLHttpRequest()

      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/post/get/' + uuid, true)

      xhr.setRequestHeader('Content-type', 'application/json')

      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))

      xhr.onload = function () {
        if (xhr.status === 200) {
          // Request was successful
          var responseData = JSON.parse(xhr.responseText)
          self.post = responseData
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

      let uuid = this.$route.params.uuid

      var xhr = new XMLHttpRequest()

      xhr.open('POST', 'http://localhost:8083/SOCNET/rest/comment/save', true)

      xhr.setRequestHeader('Content-type', 'application/json')

      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))

      const data = {
        post: uuid,
        text: this.form.comment
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
      xhr.send(JSON.stringify(data))
    },
    checkUser (username) {
      let usern = JSON.parse(atob(localStorage.getItem('token').split('.')[1]))['username']
      return username === usern
    },
    removeMyComment (uuid) {
      let self = this

      var xhr = new XMLHttpRequest()

      xhr.open('GET', 'http://localhost:8083/SOCNET/rest/comment/delete/' + uuid, true)

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
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
