package com.ebsco.ese.dmsqlversioncontrol.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class AppProperties {

    @Value("${spring.datasource.url}")
    private String mysqlURL;

    @Value("${spring.datasource.username}")
    private String mysqlUser;

    @Value("${spring.datasource.password}")
    private String mysqlPassword;

    @Value("${help}")
    private Boolean help;

    @Value("${toVersion}")
    private int toVersion;

    public String getMysqlURL() {
        return mysqlURL;
    }

    public String getMysqlUser() {
        return mysqlUser;
    }

    public String getMysqlPassword() {
        return mysqlPassword;
    }

    public Boolean getHelp() {
        return help;
    }

    public int getToVersion() {
        return toVersion;
    }
}

