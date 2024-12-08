package com.example.batchtest1.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.*;

@Configuration
@Slf4j
public class WebClientConfig {



    @Bean(value = "MspPlusIF")
    public WebClient webClient() {

        return WebClient
                .builder()
                .baseUrl("www.naver.com") // base url
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))  // 데이터 버퍼 조정
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.setBearerAuth(""); // Barrer token 설정
                })
                .build();
    }


}