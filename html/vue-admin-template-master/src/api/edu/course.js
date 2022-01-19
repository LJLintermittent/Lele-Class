import request from '@/utils/request'

export default {
    addCourseInfo(CourseInfo) {
        return request ({
            url: '/eduservice/course/addCourseInfo',
            method: 'post',
            data: CourseInfo    
        })
    },
    getListTeacher() {
        return request ({
            url: '/eduservice/teacher/findAll',
            method: 'get' 
        })
    },
    getCourseInfoId(id){
        return request({
            url: 'eduservice/course/getCourseInfo/' + id,
            method: 'get' 
        })
    },
    updateCourseInfoId(CourseInfo){
        return request({
            url: 'eduservice/course/updateCourseInfo/',
            method: 'post',
            data: CourseInfo
        })
    },
    getPublishCourseInfo(id){
        return request({
            url: 'eduservice/course/getPublishCourseInfo/' + id,
            method: 'get'
        })
    },
    publishCourse(id){
        return request({
            url: 'eduservice/course/publishCourse/' + id,
            method: 'post'
        })
    },
    //TODO:条件查询带分页
    getListCourse(){
        return request({
            url: 'eduservice/course/getCourseList',
            method: 'get'
        })
    },
    deleteCourse(id){
        return request({
            url: 'eduservice/course/deleteCourse/' + id,
            method: 'delete'
        })
    },
    //条件查询带分页
    getCourseListPage(current, size, courseQuery) {
        return request({
            url: `/eduservice/course/pageCourseCondition/${current}/${size}`,
            method: 'post',
            data: courseQuery
        })
    },
}