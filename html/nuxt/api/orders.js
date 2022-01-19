import request from '@/utils/request'

export default {
  createOrder(courseId) {
    return request({
      url: `/eduorder/order/createOrder/${courseId}`,
      method: 'post'
    })
  },
  getOrdersInfo(id) {
    return request({
      url: `/eduorder/order/getOrderInfo/${id}`,
      method: 'get'
    })
  },
    //3、生成微信支付二维码
    createNative(orderNo) {
      return request({
          url: '/eduorder/pay-log/createNative/'+orderNo,
          method: 'get'
      })
  },
  //4、根据id获取订单支付状态
  queryPayStatus(orderNo) {
      return request({
          url: '/eduorder/pay-log/queryPayStatus/'+orderNo,
          method: 'get'
      })
  }
}