<template>
  <div class="hello">
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
            v-model="form.startdate"
            placeholder="Enter Start Date Of Brith"
            type="text"
          ></b-form-input>
        </b-form-group>

        <b-form-group style="margin-top:1em;" id="input-group-4" label-for="input-4">
          <b-form-input
            id="input-4"
            v-model="form.enddate"
            placeholder="Enter End Date Of Brith"
            type="text"
          ></b-form-input>
        </b-form-group>

        <b-form-group style="margin-top:1em;" id="input-group-7" label="DATE SORT:" label-for="input-7">
          <b-form-select
            id="input-7"
            v-model="form.datesort"
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
        <router-link :to="`/friend-profile/${u.username}`">View Profile</router-link>
        <!-- <a href="/friend-profile/"+ u.username>View Profile</a> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Search',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      form: {
        firstname: '',
        firstnamesort: 'NO',
        lastname: '',
        lastnamesort: 'NO',
        startdate: '',
        enddate: '',
        datesort: 'NO'
      },
      sorts: ['YES', 'NO'],
      users: null
    }
  },
  methods: {
    onSubmit (event) {
      event.preventDefault()
      var self = this
      var xhr = new XMLHttpRequest()
      xhr.open('POST', 'http://localhost:8083/SOCNET/rest/user/search', true)
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
        startdate: this.form.startdate === '' ? 0 : this.form.startdate,
        enddate: this.form.enddate === '' ? 0 : this.form.enddate,
        datesort: this.form.datesort === 'YES'
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
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
