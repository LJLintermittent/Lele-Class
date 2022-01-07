package com.learn.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * date: 2021/2/26 22:08
 * Package: com.learn.servicebase.exceptionhandler
 *
 * @author 李佳乐
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuLiException extends RuntimeException {

    private Integer code;//状态码

    private String msg;//异常信息


}
