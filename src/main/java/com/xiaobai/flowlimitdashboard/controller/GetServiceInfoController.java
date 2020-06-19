package com.xiaobai.flowlimitdashboard.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaobai.flowlimitdashboard.entity.GetServiceInfoReqBO;
import com.xiaobai.flowlimitdashboard.entity.GetServiceInfoRspBO;
import com.xiaobai.flowlimitdashboard.enums.MsgCdEnums;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取接口信息
 *
 * @author yin_zhj
 * @date 2020/6/17
 */
@RestController
public class GetServiceInfoController {
    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "application/json";
    private static final String ACCESS = "access";
    private static final String LIMIT = "limit";

    @RequestMapping("/getServiceInfo")
    public GetServiceInfoRspBO getServiceInfo(@RequestBody GetServiceInfoReqBO reqBO) {
        GetServiceInfoRspBO rspBO = new GetServiceInfoRspBO();
        String url = reqBO.getUrl();
        String service = reqBO.getService();
        Map<String, String> map = new HashMap<>();
        map.put("service", service);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(JSON.toJSONString(map), ENCODING);
        entity.setContentType(CONTENT_TYPE);
        httpPost.setEntity(entity);
        try {
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity());
                JSONObject json = JSONObject.parseObject(result);
                if(null == json) {
                    rspBO.setMsgCd(MsgCdEnums.NO_SERVICE_INFO.getMsgCd());
                    rspBO.setMsgInf(MsgCdEnums.NO_SERVICE_INFO.getMsgInf());
                } else {
                    Long accNums = json.getLong(ACCESS);
                    Long lmtNums = json.getLong(LIMIT);
                    rspBO.setAccNums(null == accNums ? 0L : accNums);
                    rspBO.setLmtNums(null == lmtNums ? 0L : lmtNums);
                    rspBO.setMsgCd(MsgCdEnums.SUCCESS.getMsgCd());
                    rspBO.setMsgInf(MsgCdEnums.SUCCESS.getMsgInf());
                }
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
