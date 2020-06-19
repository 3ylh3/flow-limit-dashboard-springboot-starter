package com.xiaobai.flowlimitdashboard.entity;

import com.xiaobai.flowlimitdashboard.entity.base.BaseRspBO;
import lombok.Data;

/**
 * 获取通过/限制信息rspBO
 *
 * @author yin_zhj
 * @date 2020/6/16
 */
@Data
public class GetInfoRspBO extends BaseRspBO {
    /**
     * 通过总数
     */
    private Long accNums;
    /**
     * 限制总数
     */
    private Long lmtNums;
}
