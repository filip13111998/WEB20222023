<template>
  <div class="hello">
    <register-menu></register-menu>
    <search></search>

  </div>
</template>

<script>
import RegisterMenu from './RegisterMenu.vue'
import Search from './Search.vue'
export default {
  name: 'RegisterHome',
  components: {
    RegisterMenu,
    Search
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
