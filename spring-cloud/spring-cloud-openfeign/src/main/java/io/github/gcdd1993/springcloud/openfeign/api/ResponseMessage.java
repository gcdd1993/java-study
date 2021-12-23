package io.github.gcdd1993.springcloud.openfeign.api;

import lombok.Data;

/**
 * @author gcdd1993
 * @since 2021/12/23
 */
@Data
public class ResponseMessage<T> {
    private Boolean success;
    private Integer errorCode;
    private String errorMsg;
    private T data;
}
