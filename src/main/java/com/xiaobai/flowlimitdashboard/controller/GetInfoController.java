package com.xiaobai.flowlimitdashboard.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaobai.flowlimitdashboard.entity.GetInfoReqBO;
import com.xiaobai.flowlimitdashboard.entity.GetInfoRspBO;
import com.xiaobai.flowlimitdashboard.enums.MsgCdEnums;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 获取通过/限制信息
 *
 * @author yin_zhj
 * @date 2020/6/16
 */
@RestController
public class GetInfoController {
    private static final String ACCESS = "access";
    private static final String LIMIT = "limit";

    @RequestMapping("/getInfo")
    public GetInfoRspBO getInfo(@RequestBody GetInfoReqBO reqBO) {
        GetInfoRspBO rspBO = new GetInfoRspBO();
        String url = reqBO.getUrl();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity());
                JSONObject json = JSONObject.parseObject(result);
                Long accNums = 0L;
                Long lmtNums = 0L;
                for(Map.Entry<String, Object> entry : json.entrySet()) {
                    JSONObject object = (JSONObject) entry.getValue();
                    Long acc = object.getLong(ACCESS);
                    Long lmt = object.getLong(LIMIT);
                    accNums += null == acc ? 0L : acc;
                    lmtNums += null == lmt ? 0L : lmt;
                }
                rspBO.setAccNums(accNums);
                rspBO.setLmtNums(lmtNums);
                rspBO.setMsgCd(MsgCdEnums.SUCCESS.getMsgCd());
                rspBO.setMsgInf(MsgCdEnums.SUCCESS.getMsgInf());
            } else if(response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                rspBO.setMsgCd(MsgCdEnums.IP_ERROR.getMsgCd());
                rspBO.setMsgInf(MsgCdEnums.IP_ERROR.getMsgInf());
            } else {
                rspBO.setMsgCd(MsgCdEnums.SEND_ERROR.getMsgCd());
                rspBO.setMsgInf(MsgCdEnums.SEND_ERROR.getMsgInf() + ":" + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            rspBO.setMsgCd(MsgCdEnums.SYS_ERROR.getMsgCd());
            rspBO.setMsgInf(MsgCdEnums.SYS_ERROR.getMsgInf() + ":" + e.toString());
        }
        return  rspBO;
    }
}
