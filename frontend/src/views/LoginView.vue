<template>
  <BaseNavigation/>
  <div>
    <span class="display-3">Login</span>
  </div>
  <div class="mt-5">
    <button class="btn btn-lg btn-warning" @click="login('KAKAO')">카카오 로그인</button>
  </div>
</template>

<script>
import { OAUTH } from '@/util/oauth.js'
import BaseNavigation from '@/components/BaseNavigation.vue'

export default {
  name: 'LoginView',
  components: {
    BaseNavigation
  },
  methods: {
    login (provider) {
      location.href = OAUTH[provider].URI
    }
  }
}
</script>
