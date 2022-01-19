import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import cookie from 'js-cookie'
// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:9001', // api的base_url
  timeout: 90000 // 请求超时时间
})
service.interceptors.request.use(
  config => {
  //debugger
  if (cookie.get('guli_token')) {
    config.headers['token'] = cookie.get('guli_token');
  }
    return config
  },
  err => {
  return Promise.reject(err);
})

service.interceptors.response.use(
  response => {
    if(response.data.code == 28004){
      console.log("response.data.resultCode是28004")
      window.location.href = "/login"
      return
    }else{
      if(response.data.code !== 20000){
        if(response.data.code !== 25000){
          Message({
            message: response.data.message || 'error',
            type: 'error',
            duration: 5*1000
          })
        }
      }else{
        return response;
      }
    }
  }
)

export default service