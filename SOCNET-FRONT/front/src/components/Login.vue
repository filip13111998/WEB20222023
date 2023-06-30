<template>
  <div style="width: 20%;margin-left:40%;margin-top:15%">
    <b-form @submit="onSubmit">
      <b style="color: rgb(113, 113, 236);">LOGIN FORM</b>
      <b-form-group
        id="input-group-1"
        label="Your Username:"
        label-for="input-1"
        style="margin-top: 1em;"
      >
        <b-form-input
          id="input-1"
          v-model="form.username"
          type="text"
          placeholder="Enter username"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Your Password:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.password"
          placeholder="Enter Password"
          type="password"
          required
        ></b-form-input>
      </b-form-group>
      <b style="color: red;" v-if="msg">WRONG CREDENTIALS!</b>
      <b-button style="margin-top:1em;" type="submit" variant="primary">LOGIN</b-button>
      <!-- <b-button type="reset" variant="danger">Reset</b-button> -->
    </b-form>
    <!-- <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ form }}</pre>
    </b-card> -->
  </div>
</template>

<script>
import axios from 'axios'
import router from '../router/index.js'
export default {
  name: 'Login',
  data () {
    return {
      msg: false,
      form: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    onSubmit (event) {
      event.preventDefault()
      // alert(JSON.stringify(this.form))
      // const xhr = new XMLHttpRequest()
      // xhr.open('POST', 'http://localhost:8083/SOCNET/rest/auth/login', true)
      // xhr.setRequestHeader('Content-Type', 'application/json')
      // alert(JSON.stringify(this.form))
      // xhr.onload = () => {
      //   if (xhr.status === 200) {
      //     // Request was successful
      //     console.log(xhr.responseText)
      //     alert(JSON.stringify(this.form))
      //   } else {
      //     // Request failed
      //     console.error(xhr.statusText)
      //   }
      // }

      // xhr.onerror = function () {
      //   // An error occurred during the request
      //   console.error('An error occurred')
      // }
      // console.log('PRE SLANJA')
      // // xhr.send(JSON.stringify(this.form))
      // console.log('POSLE SLANJA')
      // const url = 'http://localhost:8083/SOCNET/rest/auth/login'
      // const url = 'http://localhost:8083/SOCNET/rest/auth/zika/23'
      // alert('KKK')
      // axios.get(url).then(response => {
      //   console.log(response.data)
      //   alert(response.data)
      //   // Handle successful respons
      // }).catch(error => {
      //   console.error(error)
      //   alert('NEE')
      //   // Handle error
      // })
      const url = 'http://localhost:8083/SOCNET/rest/auth/login'
      const config = {
        headers: {
          'Content-Type': 'application/json'
        }
      }
      // const data = JSON.stringify(this.form)
      const data = {
        username: this.form.username,
        password: this.form.password
      }

      axios.post(url, data, config)
        .then(response => {
          const token = response.data.jwt

          localStorage.setItem('token', token)

          const myRole = JSON.parse(atob(token.split('.')[1]))['role']

          if (myRole.includes('USER')) {
            router.push('/register-home')
          } else if (myRole.includes('ADMIN')) {
            router.push('/admin-home')
          }
        })
        .catch(_error => {
          this.msg = true
        })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
