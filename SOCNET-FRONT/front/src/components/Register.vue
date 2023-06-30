<template>
  <div style="width: 20%;margin-left:40%;margin-top:15%">
    <b-form @submit="onSubmit">
      <b style="color: rgb(224, 184, 25);">REGISTER FORM</b>

      <b-form-group style="margin-top: 1em;" id="input-group-1" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="form.username"
          placeholder="Enter Username"
          type="text"
          required
        ></b-form-input>
      </b-form-group>
      <b style="color: red;" v-if="usrmsg">USERNAME EXIST!</b>

     <b-form-group style="margin-top:1em;" id="input-group-2" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.password"
          placeholder="Enter Password"
          type="password"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group style="margin-top:1em;" id="input-group-2c" label-for="input-2c">
        <b-form-input
          id="input-2c"
          v-model="form.confirmpassword"
          placeholder="Enter Confirm Pass"
          type="password"
          required
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

      <b-button style="margin-top:1em;" type="submit" variant="warning">REGISTER</b-button>
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
  name: 'Register',
  data () {
    return {
      msg: false,
      usrmsg: false,
      form: {
        username: '',
        password: '',
        confirmpassword: '',
        email: '',
        firstName: '',
        lastName: '',
        dateOfBrith: '',
        gender: null,
        isPrivate: null
      },
      genders: ['M', 'F'],
      privates: [true, false]
    }
  },
  methods: {
    onSubmit (event) {
      event.preventDefault()

      const url = 'http://localhost:8083/SOCNET/rest/auth/register'
      const config = {
        headers: {
          'Content-Type': 'application/json'
        }
      }
      this.usrmsg = false
      this.msg = false
      // const data = JSON.stringify(this.form)
      if (this.form.password !== this.form.confirmpassword) {
        this.msg = true
        return
      }

      const data = {
        username: this.form.username,
        password: this.form.password,
        email: this.form.email,
        firstName: this.form.firstName,
        lastName: this.form.lastName,
        dateOfBrith: this.form.dateOfBrith,
        gender: this.form.gender,
        isPrivate: this.form.isPrivate
      }
      console.log(data)
      axios.post(url, data, config)
        .then(response => {
          // Check if the response status code is 400
          if (response.status === 400) {
            // Access the jwttoken text from the response data
            const jwtokenText = response.data.text
            console.log(jwtokenText) // Output the JWT token text
          }

          const token = response.data.jwt

          localStorage.setItem('token', token)

          const myRole = JSON.parse(atob(token.split('.')[1]))['role']

          if (myRole.includes('USER')) {
            router.push('/login')
          }
        })
        .catch(_error => {
          this.usrmsg = true
        })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
