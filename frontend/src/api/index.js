import axios from 'axios'

const BASE_URL = process.env.VUE_APP_BASE_SERVER_URI

const backendAPI = axios.create({
  baseURL: BASE_URL,
  withCredentials: true
})

export {
  backendAPI
}
