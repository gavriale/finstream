package com.gavriale.finstream.finnhub;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableConfigurationProperties(FinnhubProps.class)
public class FinnhubConfig {

    @Bean
    WebClient finnhubWebClient(FinnhubProps props) {
        var http = HttpClient.create().wiretap(true); // logs request/response lines+headers
        return WebClient.builder()
                .baseUrl(props.baseUrl())
                .clientConnector(new ReactorClientHttpConnector(http))
                .build();
    }
}

@ConfigurationProperties(prefix = "finnhub")
record FinnhubProps(String baseUrl, String apiKey) {}