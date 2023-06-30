<template>
  <div class="hello">
    <div>
      <b-navbar toggleable="lg" type="dark" variant="info">
        <b-navbar-brand href="/register-home" style="margin-left: 1em;">FVSN</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav style="position: absolute; right: 9em;">
            <b-nav-item href="/login">LOGIN</b-nav-item>
            <!-- <b-nav-item href="#" disabled>Disabled</b-nav-item> -->
          </b-navbar-nav>

          <b-navbar-nav style="position: absolute; right: 2em;">
            <b-nav-item href="/register">REGISTER</b-nav-item>
            <!-- <b-nav-item href="#" disabled>Disabled</b-nav-item> -->
          </b-navbar-nav>

        </b-collapse>
      </b-navbar>
    </div>

  </div>
</template>

<script>
import router from '../router/index.js'
export default {
  name: 'UserMenu',
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
