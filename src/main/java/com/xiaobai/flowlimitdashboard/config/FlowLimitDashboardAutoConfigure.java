package com.xiaobai.flowlimitdashboard.config;

import com.xiaobai.flowlimitdashboard.controller.GetInfoController;
import com.xiaobai.flowlimitdashboard.controller.GetServiceInfoController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配类
 *
 * @author yin_zhj
 * @date 2020/6/18
 */
@Configuration
public class FlowLimitDashboardAutoConfigure {
    @Bean
    @ConditionalOnMissingBean
    public GetInfoController initGetInfoController() {
        return new GetInfoController();
    }

    @Bean
    @ConditionalOnMissingBean
    public GetServiceInfoController initGetServiceInfoController() {
        return new GetServiceInfoController();
    }
}
