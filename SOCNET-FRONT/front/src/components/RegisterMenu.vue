<template>
  <div class="hello">
    <div>
      <b-navbar toggleable="lg" type="dark" variant="info">
        <b-navbar-brand href="/register-home" style="margin-left: 1em;">REGISTER USER</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item href="/register-home">HOME</b-nav-item>
            <!-- <b-nav-item href="#" disabled>Disabled</b-nav-item> -->
          </b-navbar-nav>

          <b-navbar-nav>
            <b-nav-item href="/friendship-requests">FRIENDSHIP REQUESTS</b-nav-item>
            <!-- <b-nav-item href="#" disabled>Disabled</b-nav-item> -->
          </b-navbar-nav>

          <b-navbar-nav>
            <b-nav-item href="/friends">FRIENDS</b-nav-item>
            <!-- <b-nav-item href="#" disabled>Disabled</b-nav-item> -->
          </b-navbar-nav>

          <b-navbar-nav>
            <b-nav-item href="/chat-users">CHAT</b-nav-item>
            <!-- <b-nav-item href="#" disabled>Disabled</b-nav-item> -->
          </b-navbar-nav>

          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <!-- <b-nav-form>
              <b-row class="align-items-center">
                <b-col cols="auto">
                  <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                </b-col>
                <b-col cols="auto">
                  <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                </b-col>
              </b-row>
            </b-nav-form> -->

            <!-- <b-nav-item-dropdown text="Lang" right style="position: absolute; right: 7em;">
              <b-dropdown-item href="#">EN</b-dropdown-item>
              <b-dropdown-item href="#">ES</b-dropdown-item>
              <b-dropdown-item href="#">RU</b-dropdown-item>
              <b-dropdown-item href="#">FA</b-dropdown-item>
            </b-nav-item-dropdown> -->

            <b-nav-item-dropdown right style="position: absolute; right: 3em;margin-top:-1em">
              <!-- Using 'button-content' slot -->
              <template #button-content>
                <em>User</em>
              </template>
              <b-dropdown-item href="register-profile">Profile</b-dropdown-item>
              <b-dropdown-item href="#" @click="signOut">Sign Out</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>

  </div>
</template>

<script>
import router from '../router/index.js'
export default {
  name: 'RegisterMenu',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    signOut (event) {
      event.preventDefault()
      localStorage.removeItem('token')
      router.push('/login')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
