import request from '@/utils/request'

export default {
  //根据手机号码发送短信
  getTeacherList(current,size) {
    return request({
      url: `/eduservice/teacherfront/getTeacherFrontList/${current}/${size}`,
      method: 'post'
    })
  },
  getTeacherInfo(id) {
    return request({
      url: `/eduservice/teacherfront/getTeacherFrontInfo/${id}`,
      method: 'get'
    })
  },
}