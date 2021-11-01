package com.blackship.greenion.user.oauth2;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Builder(access = AccessLevel.PRIVATE)
@Getter
public class OAuth2Attribute {
    private String email;
    private String attributeKey;

    static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes) {
        switch (provider) {
            case "kakao":
                return kakaoAttribute("email", attributes);
            default:
                throw new IllegalStateException();
        }
    }

    private static OAuth2Attribute kakaoAttribute(String email, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuth2Attribute.builder()
                .email((String) kakaoAccount.get("email"))
                .attributeKey(email)
                .build();
    }

    public Map<String, Object> convertToMap() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", attributeKey);
        attributes.put("email", email);

        return attributes;
    }
}
