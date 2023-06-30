<template>
  <div class="hello">
    <user-menu></user-menu>
    <search></search>
  </div>
</template>

<script>
import UserMenu from './UserMenu.vue'
import Search from './Search.vue'
export default {
  name: 'UserHome',
  components: {
    UserMenu,
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
