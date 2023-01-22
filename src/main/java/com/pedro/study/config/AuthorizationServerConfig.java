package com.pedro.study.config;


import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.pedro.study.model.User;
import com.pedro.study.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.server.authorization.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenCustomizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwsEncoder;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.server.authorization.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.io.InputStream;
import java.security.KeyStore;
import java.time.Duration;
import java.util.Arrays;


@Configuration
@EnableWebSecurity

public class AuthorizationServerConfig  {


//    private AuthenticationManager authenticationManager;
    @Value("#{environment['jwt.chave']}")
    private String chaveJWT;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
        return httpSecurity.formLogin(Customizer.withDefaults()).build();

    }

    @Bean

    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(chaveJWT);

        return converter;
    }



    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .anyRequest()
                .authenticated();
        return httpSecurity.formLogin(Customizer.withDefaults()).build();

    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtEncodingContextOAuth2TokenCustomizer(UserRepository userRepository){
        return (jwtEncodingContext -> {

            Authentication authentication = jwtEncodingContext.getPrincipal();

                final org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

                final User userEntity =  userRepository.findByLogin(user.getUsername())
                        .orElseThrow(()-> new RuntimeException("dgsfhsfghdfs"));
                System.out.println(userEntity.getNome());



            jwtEncodingContext.getClaims().claim("user_Id","askdfjadskfhjjih");


        });
    }


    //
    @Bean
    public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {

        RegisteredClient registeredClient =
                RegisteredClient.withId("1")
                        .clientId("studyResource")
                        .clientSecret(passwordEncoder.encode("123456"))
                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .scope("users:write")
                        .scope("users:read")
                        .tokenSettings(TokenSettings.builder()
                                .accessTokenTimeToLive(Duration.ofMinutes(20))
                                .build())
                        .clientSettings(ClientSettings.builder()
                                .requireAuthorizationConsent(false)
                                .build())

                        .build();
        // @formatter:on

        return new InMemoryRegisteredClientRepository(Arrays.asList(registeredClient));


    }


    @Bean
    public ProviderSettings providerSettings(AuthProperties authProperties){

        return ProviderSettings.builder()
                .issuer(authProperties.getProviderUri())
                .build();

    }

    @Bean
    public JWKSet jwkSet(AuthProperties authProperties) throws Exception{

        String jkspath = authProperties.getJks().getPath();
        final InputStream inputStream = new ClassPathResource(jkspath).getInputStream();

        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(inputStream, authProperties.getJks().getStorepass().toCharArray());
        RSAKey rsaKey = RSAKey.load(ks,authProperties.getJks().getAlias(), authProperties.getJks().getKeypass().toCharArray());
        return new JWKSet(rsaKey);

    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(JWKSet jwkSet){

        return ((jwkSelector, securitContext)-> jwkSelector.select(jwkSet));
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource){

        return new NimbusJwsEncoder(jwkSource);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public OAuth2TokenCustomizer<JwtEncodingContext> jwtEncodingContextOAuth2TokenCustomizer(UserRepository userRepository){
//        return (jwtEncodingContext -> {
//
//            Authentication authentication = jwtEncodingContext.getPrincipal();
//            if(authentication.getPrincipal() instanceof User){
//                final User user = (User) authentication.getPrincipal();
////
//            final User userEntity =  userRepository.findByLogin(user.getLogin())
//                    .orElseThrow();
//            System.out.println(userEntity.getNome());
//            jwtEncodingContext.getClaims().claim("user_Id", userEntity.getLogin().toString());
//            }
//
//
//        });
//    }

//    @Bean
//    public TokenStore tokenStore() {
//        JwtTokenStore tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
//        return tokenStore;
//    }
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(chaveJWT);
//        return converter;
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .authenticationManager(authenticationManager)
//                .tokenStore(tokenStore())
//                .accessTokenConverter(jwtAccessTokenConverter());
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients
//                .inMemory()
//                .withClient("my-angular-app")
//                .secret("@321")
//                .scopes("read", "write")
//                .authorizedGrantTypes("password")
//                .accessTokenValiditySeconds(60 * 15);
//
//    }
//

}
