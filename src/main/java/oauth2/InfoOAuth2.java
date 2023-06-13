package oauth2;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import oauth2.dto.request.ExchangeTokenRequest;
import oauth2.dto.response.ResourceResponse;
import oauth2.dto.response.TokenResponse;
import oauth2.exception.InfoOAuth2Exception;
import oauth2.feign.BasicAuthInterceptor;
import oauth2.feign.BearerAuthInterceptor;
import oauth2.feign.InfoOAuth2Server;


public class InfoOAuth2 {

    private final String CLIENT_ID;
    private final String CLIENT_SECRET;
    private final String SERVER_URL = "https://oauth2.info-dsm.info/";
    private final String AUTH_CODE_GRANT_TYPE = "authorization_code";
    private final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";

    private String accessToken;
    private String refreshToken;

    private final JacksonDecoder jacksonDecoder;
    private final JacksonEncoder jacksonEncoder;
    private final InfoOAuth2Server server;

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public InfoOAuth2(String clientId, String clientSecret) {
        this.CLIENT_ID = clientId;
        this.CLIENT_SECRET = clientSecret;
        this.jacksonDecoder = new JacksonDecoder();
        this.jacksonEncoder = new JacksonEncoder();

        server = Feign.builder()
                .decoder(jacksonDecoder)
                .encoder(this.jacksonEncoder)
                .requestInterceptor(new BasicAuthInterceptor(CLIENT_ID, CLIENT_SECRET))
                .target(InfoOAuth2Server.class, SERVER_URL);
    }

    public TokenResponse exchangeTokenWithoutPKCE(ExchangeTokenRequest request) throws InfoOAuth2Exception {
        try {

            TokenResponse tokenResponse = server.exchange(AUTH_CODE_GRANT_TYPE, request.getCode(), request.getRedirectUri());
            this.accessToken = tokenResponse.getAccess_token();
            this.refreshToken = tokenResponse.getRefresh_token();
            return tokenResponse;
        } catch (RuntimeException e) {
            throw new InfoOAuth2Exception(e.getMessage());
        }
    }

    public void refreshToken() throws InfoOAuth2Exception {
        try {
        TokenResponse tokenResponse = server.refresh(REFRESH_TOKEN_GRANT_TYPE, "profile", this.refreshToken);
        this.accessToken = tokenResponse.getAccess_token();
        this.refreshToken = tokenResponse.getRefresh_token();
        } catch (RuntimeException e) {
            throw new InfoOAuth2Exception(e.getMessage());
        }
    }

    public ResourceResponse getUserResponse() throws InfoOAuth2Exception {
         try {
        if (this.accessToken.isEmpty()) throw new InfoOAuth2Exception("AccessToken cannot found!");
        InfoOAuth2Server authorizedServer = Feign.builder()
                .decoder(jacksonDecoder)
                .encoder(jacksonEncoder)
                .requestInterceptor(new BearerAuthInterceptor(this.accessToken))
                .target(InfoOAuth2Server.class, SERVER_URL);
        return authorizedServer.getUser();
         } catch (RuntimeException e) {
             throw new InfoOAuth2Exception(e.getMessage());
         }
    }

}
