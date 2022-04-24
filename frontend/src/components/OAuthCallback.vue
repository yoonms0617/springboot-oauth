<template>
  <div></div>
</template>

<script>
import { backendAPI } from '@/api/index.js'

export default {
  name: 'OAuthCallback',
  created () {
    const authorizationCode = this.$route.query.code
    const provider = this.$route.params.provider
    backendAPI.get(`/oauth/${provider}/login?code=${authorizationCode}`).then(res => {
      console.log(res)
    })
  }
}
</script>
