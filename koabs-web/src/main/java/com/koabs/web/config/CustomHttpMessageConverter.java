package com.koabs.web.config;

/**
 * Author: koabs
 * 2020/8/20.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import springfox.documentation.spring.web.json.Json;

@Configuration
public class CustomHttpMessageConverter {

    @Bean
    public GsonHttpMessageConverter buildGson() {
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Json.class, new SwaggerJsonSerialize())
                .serializeNulls()
                .create();

        final GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }

}