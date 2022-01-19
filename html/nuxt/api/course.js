import request from '@/utils/request'
export default {
  getCourseList(current, size, searchObj) {
    return request({
      url: `/eduservice/coursefront/getCourseFrontList/${current}/${size}`,
      method: 'post',
      data: searchObj
    })
  },
  // 获取课程二级分类
  getAllSubject() {
    return request({
      url: `/eduservice/subject/getAllSubject`,
      method: 'get'
    })
  },
  getCourseInfo(id) {
    return request({
        url: `/eduservice/coursefront/getFrontCourseInfo/${id}`,
        method: 'get'
      })
  }
}