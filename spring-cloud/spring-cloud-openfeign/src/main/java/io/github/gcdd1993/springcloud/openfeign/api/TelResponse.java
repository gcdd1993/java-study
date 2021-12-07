package io.github.gcdd1993.springcloud.openfeign.api;

import lombok.Data;

/**
 * @author gcdd1993
 * @date 2021/12/7
 * @since 1.0.0
 */
@Data
public class TelResponse {
    private String code;
    private String tel;
    private String local;
    private String duan;
    private String type;
    private String yys;
    private String bz;
}
