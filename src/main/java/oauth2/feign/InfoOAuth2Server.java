package oauth2.feign;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import oauth2.dto.response.ResourceResponse;
import oauth2.dto.response.TokenResponse;


public interface InfoOAuth2Server {
    @RequestLine("POST /oauth2/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @Body("grant_type={grantType}&code={code}&redirect_uri={refreshToken}")
    TokenResponse exchange(@Param("grantType") String grantType, @Param("code") String code, @Param("refreshToken") String refreshToken);

    @RequestLine("POST /oauth2/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @Body("grant_type={grantType}&scope={scope}&redirect_uri={refreshToken}")
    TokenResponse refresh(@Param("grantType") String grantType, @Param("scope") String scope, @Param("refreshToken") String refreshToken);

    @RequestLine("GET /resource/user")
    ResourceResponse getUser();

}
