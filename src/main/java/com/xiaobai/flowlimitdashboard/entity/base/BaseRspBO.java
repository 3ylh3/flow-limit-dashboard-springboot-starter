package com.xiaobai.flowlimitdashboard.entity.base;

import lombok.Data;

/**
 * 公共rspBO
 *
 * @author yin_zhj
 * @date 2020/6/16
 */
@Data
public class BaseRspBO {
    /**
     * 返回信息
     */
    private String msgCd;
    /**
     * 返回码
     */
    private String msgInf;
}
