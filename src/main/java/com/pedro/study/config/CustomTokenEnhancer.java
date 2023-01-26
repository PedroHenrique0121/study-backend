package com.pedro.study.config;

import com.pedro.study.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UserService userService;
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfoToken = new HashMap();

        User  user = (User) authentication.getPrincipal();
        com.pedro.study.model.User userEntity=  userService.retornarPorLogin(user.getUsername());
        additionalInfoToken.put("user_id",userEntity.getId());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfoToken);
        return accessToken;
    }


}
