package com.github.printparam.starter;

import com.github.printparam.ParamFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author lian
 */
@Configuration
@ConditionalOnClass(ParamFilter.class)
@EnableConfigurationProperties(PrintParamProperties.class)
public class PrintParamAutoConfigure {
    @Resource
    private PrintParamProperties properties;

    public PrintParamAutoConfigure() {
    }

    @Bean
    public FilterRegistrationBean addFilter() {
        ParamFilter filter  = new ParamFilter(properties);
        FilterRegistrationBean<ParamFilter> registration = new FilterRegistrationBean<ParamFilter>(filter);
        registration.addUrlPatterns(properties.getFilterIncludePattern());
        return registration;
    }
}
