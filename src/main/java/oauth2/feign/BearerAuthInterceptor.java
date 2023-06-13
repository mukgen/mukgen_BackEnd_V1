package oauth2.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class BearerAuthInterceptor implements RequestInterceptor {
    private final String accessToken;

    public BearerAuthInterceptor(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Bearer " + accessToken);
    }
}
