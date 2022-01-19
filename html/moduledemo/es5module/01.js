// 定义成员：
    const sum = function(a,b){
    return parseInt(a) + parseInt(b)
   }
    const subtract = function(a,b){
    return parseInt(a) - parseInt(b)
   }
    const multiply = function(a,b){
    return parseInt(a) * parseInt(b)
   }
    const divide = function(a,b){
    return parseInt(a) / parseInt(b)
   }

   module.exports = {
    sum,
    subtract,
    multiply,
    divide
   }