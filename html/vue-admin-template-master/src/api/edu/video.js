import request from '@/utils/request'

export default {
  
    addVideo(video) {
        return request ({
            url: '/eduservice/video/addVideo',
            method: 'post',
            data: video   
        })
    },
    deleteVideo(id) {
        return request ({
            url: '/eduservice/video/deleteVideo/' + id,
            method: 'delete' 
        })
    },
    deleteAliyunVideo(id) {
        return request ({
            url: '/eduvod/video/removeVideo/' + id,
            method: 'delete' 
        })
    }
}
