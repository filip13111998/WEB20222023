<template>
  <div class="hello">
    <admin-menu></admin-menu>
    ADMIN HOME
  </div>
</template>

<script>
import AdminMenu from './AdminMenu.vue'
export default {
  name: 'RegisterHome',
  components: {
    AdminMenu
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- <style scoped>

</style> -->
