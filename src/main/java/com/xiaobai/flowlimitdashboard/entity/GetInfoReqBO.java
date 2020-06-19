package com.xiaobai.flowlimitdashboard.entity;

import lombok.Data;

/**
 * 获取通过/限制信息reqBO
 *
 * @author yin_zhj
 * @date 2020/6/16
 */
@Data
public class GetInfoReqBO {
    /**
     * 监控地址
     */
    private String url;
}
