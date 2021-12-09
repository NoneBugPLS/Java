package com.zyg.search.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ------------------------------
 * 功能：
 * 作者：WF
 * 微信：hbxfwf13590332912
 * 创建时间：2021/12/3-9:45
 * ------------------------------
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class zygElasticsearchConfig {
    private String host;
    private Integer port;

    @Bean
    public RestHighLevelClient highLevelClient(){
        //1. 定义RestClientBuilder
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(host,port));
        //2. 构造RestHighLevelClient
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        //3. 返回
        return restHighLevelClient;
    }

}
