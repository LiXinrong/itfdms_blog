package com.itfdms.common.bean.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lxr
 * @Title: FdfsPropertiesConfig
 * @ProjectName itfdms_blog
 * @Description: FastDFs参数
 * @date 2018-07-1620:26
 */

@Configuration
@ConditionalOnProperty(prefix = "fdfs",name = "file-host")
@ConfigurationProperties(prefix = "fdfs")
public class FdfsPropertiesConfig {

    private String fileHost;

    public String getFileHost() {
        return fileHost;
    }

    public void setFileHost(String fileHost) {
        this.fileHost = fileHost;
    }
}
