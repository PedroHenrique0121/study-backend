package com.pedro.study.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@Validated
@ConfigurationProperties("pedro.auth")

@Data
public class AuthProperties {

    @NotBlank
    private String providerUri;
    @NotNull
    private JKSProperties jks;


    @Data

 static class JKSProperties{
     @NotBlank
     private String keypass;
     @NotBlank
     private String storepass;
     @NotBlank
     private String alias;
     @NotBlank
     private String path;
 }
}
