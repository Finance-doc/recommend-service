package com.financedoc.recommend_service.config;


import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OpenAiConfig {
    @Value("${OPENAI_API_KEY}")
    private String apikey;

    @Bean
    public OpenAiService openAiService() {
        // Duration으로 timeout 설정
        return new OpenAiService(apikey, Duration.ofSeconds(120));
    }
}
