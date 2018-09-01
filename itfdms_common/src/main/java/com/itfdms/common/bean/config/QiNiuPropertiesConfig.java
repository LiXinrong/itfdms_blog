package com.itfdms.common.bean.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
　　* @Description: 七牛参数
　　* @throws
　　* @author lxr
　　* @date 2018-07-16 20:32
　　*/

@Configuration
@ConditionalOnProperty(prefix = "qiniu",name = "accessKey")
@ConfigurationProperties(prefix = "qiniu")
public class QiNiuPropertiesConfig {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String qiniuHost;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getQiniuHost() {
        return qiniuHost;
    }

    public void setQiniuHost(String qiniuHost) {
        this.qiniuHost = qiniuHost;
    }

    public QiNiuPropertiesConfig(String accessKey, String secretKey, String bucket, String qiniuHost) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucket = bucket;
        this.qiniuHost = qiniuHost;
    }

    @Override
    public String toString() {
        return "QiNiuPropertiesConfig{" +
                "accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", bucket='" + bucket + '\'' +
                ", qiniuHost='" + qiniuHost + '\'' +
                '}';
    }

}
