package com.blackship.greenion.user.oauth2;

import com.blackship.greenion.user.oauth2.token.GreenionToken;
import com.blackship.greenion.user.oauth2.token.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final TokenService tokenService;
    private final ObjectMapper objectMapper;

    public OAuth2SuccessHandler(TokenService tokenService) {
        this.tokenService = tokenService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String uid = oAuth2User.getAttribute("email");

        GreenionToken token = tokenService.generateToken(uid);

        writeTokenToResponse(response, token);
    }

    private void writeTokenToResponse(HttpServletResponse response, GreenionToken token) throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        var writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(token));
        writer.flush();
    }
}
