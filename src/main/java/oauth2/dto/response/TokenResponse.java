package oauth2.dto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenResponse {
    String access_token;
    String refresh_token;
    String scope;
    String token_type;
    Integer expires_in;

    public TokenResponse(String access_token, String refresh_token, String scope, String token_type, Integer expires_in) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.scope = scope;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }

    public String getAccess_token(){
        return this.access_token;
    }

    public String getRefresh_token(){
        return this.refresh_token;
    }

    public String getScope(){
        return this.scope;
    }

    public String getToken_type(){
        return this.token_type;
    }

    public Integer getExpires_in(){
        return this.expires_in;
    }

}

//{
//        "access_token": "eyJraWQiOiI0OTFkYWY1Mi0zZDdjLTQwNjctYWY4ZS0yZDE4MDdkNmU3NDYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJqaW53b283OTQ1MzNAZ21haWwuY29tIiwiYXVkIjoidGVzdC1zZXJ2aWNlIiwibmJmIjoxNjgzODk1NDQ2LCJzY29wZSI6WyJwcm9maWxlIl0sImlzcyI6Imh0dHA6Ly9hdXRoLXNlcnZpY2U6OTAwMCIsImV4cCI6MTY4Mzg5OTA0NiwiaWF0IjoxNjgzODk1NDQ2fQ.InMmcNSjt7LOqbJkii8OTvNnGgQyFcDeo_lR1Sn5HQCjjD0Ee9dah7PbHVy1Wafre2YEAvnIru_Q98KXawvGdNVN83kdtQmUd4HNVo4z_oTdQ-OkmW0AKbNBgJCjXMJFMLF4qoPtJS40wPCT3uKQmwWufWrsDqb-Or2tPjnKn_UlFTnUYD9a3YAEGUhvU30u4EYWgq6SE6L8aEDND53yKMEc-LOk6K5dCwbEsynjXSemy2N-U6eX5fEk0AM5whzBg5APEFS7BWXZPr2jLyaqzl_GZDmFgvfoWOf4RNFiKJ_DYknMoSup2XPYi3wlGDU-DRU3t3STbvIn26YOmJRunw",
//        "refresh_token": "O8AeJHr1dYllcz2DOreXW2mmZaui1TzZzy9_3UmuhrODZIIIk1_3ymm0B5Q6WLSenfPUcfQU1MX2-i2vSLZBW9anddQExWIrD9GcgFbPVaPtiHperqlzm0I--yYMfDrg",
//        "scope": "profile",
//        "token_type": "Bearer",
//        "expires_in": 3599
//        }
