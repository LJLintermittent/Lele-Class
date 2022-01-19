import request from '@/utils/request'

export default {
  createStaDate(day) {
    return request({
      url: '/staservice/sta/registerCount/' + day,
      method: 'post'
    })
  },
  getDataSta(searchObj) {
    return request({
      url: `/staservice/sta/showData/${searchObj.type}/${searchObj.begin}/${searchObj.end}`,
      method: 'get'
    })
  }
}