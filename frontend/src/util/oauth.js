const KAKAO_LOGIN = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.VUE_APP_KAKAO_CLIENT_ID}&redirect_uri=${process.env.VUE_APP_KAKAO_REDIRECT_URI}&response_type=code&prompt=login`

const OAUTH = {
  KAKAO: {
    URI: KAKAO_LOGIN
  },
  GOOGLE: {
    // todo
    URI: 'hello'
  },
  NAVER: {
    // todo
    URI: 'world'
  }
}

export {
  OAUTH
}
