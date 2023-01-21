package com.pedro.study.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean(){
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        List<String> all = Arrays.asList("*");
//        corsConfiguration.setAllowedOrigins(all);
//        corsConfiguration.setAllowedHeaders(all);
//        corsConfiguration.setAllowedMethods(all);
//        corsConfiguration.setAllowCredentials(false);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**",corsConfiguration);
//
//
//        CorsFilter corsFilter = new CorsFilter(source);
//        FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(corsFilter);
//        filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return filter;
//
//    }
//}



