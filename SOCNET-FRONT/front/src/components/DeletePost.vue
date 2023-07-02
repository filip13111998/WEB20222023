<template>
  <div class="hello">
    <admin-menu></admin-menu>
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
            placeholder="Enter Text"
            required
          ></b-form-input>
        </b-form-group>
        <b-button style="margin-top:1em;" type="submit" variant="danger">DELETE POST</b-button>
        <!-- <b-button type="reset" variant="danger">Reset</b-button> -->
      </b-form>
  </div>
  </div>
</template>

<script>
import AdminMenu from './AdminMenu'
export default {
  name: 'DeletePost',
  components: {
    AdminMenu
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      form: {
        text: ''
      }
    }
  },
  methods: {
    onSubmit (event) {
      // let role = JSON.parse(atob(localStorage.getItem('token').split('.')[1]))['role']
      // console.log(role)
      // return role === 'ADMIN'
      // let usern = JSON.parse(atob(localStorage.getItem('token').split('.')[1]))['username']
      event.preventDefault()
      let uuid = this.$route.params.uuid
      let username = this.$route.params.username
      var self = this
      const data = {
        postId: uuid,
        text: this.form.text,
        userReceive: username
      }
      console.log(data)
      var xhr = new XMLHttpRequest()
      xhr.open('POST', 'http://localhost:8083/SOCNET/rest/post/delete-admin', true)
      xhr.setRequestHeader('Content-type', 'application/json')
      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'))
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
