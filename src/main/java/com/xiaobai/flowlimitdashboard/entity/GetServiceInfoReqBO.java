package com.xiaobai.flowlimitdashboard.entity;

import lombok.Data;

/**
 * 获取接口信息reqBO
 *
 * @author yin_zhj
 * @date 2020/6/17
 */
@Data
public class GetServiceInfoReqBO {
    /**
     * 监控url
     */
    private String url;
    /**
     * 接口名
     */
    private String service;
}
