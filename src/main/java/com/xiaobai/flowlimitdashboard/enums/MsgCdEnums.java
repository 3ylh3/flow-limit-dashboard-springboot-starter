package com.xiaobai.flowlimitdashboard.enums;

/**
 * 错误码枚举
 *
 * @author yin_zhj
 * @date 2020/6/16
 */
public enum MsgCdEnums {
    SUCCESS("00000", "成功"),
    SYS_ERROR("00001", "系统错误"),
    IP_ERROR("00002", "监控地址错误"),
    SEND_ERROR("00003", "通讯失败"),
    NO_SERVICE_INFO("00004", "没有该接口的数据");

    private String msgCd;
    private String msgInf;

    public String getMsgCd() {
        return msgCd;
    }

    public String getMsgInf() {
        return msgInf;
    }

    MsgCdEnums(String msgCd, String msgInf) {
        this.msgCd = msgCd;
        this.msgInf = msgInf;
    }
}
