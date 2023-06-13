package oauth2.feign;

import feign.auth.BasicAuthRequestInterceptor;

import java.nio.charset.Charset;

public class BasicAuthInterceptor extends BasicAuthRequestInterceptor {
    public BasicAuthInterceptor(String username, String password) {
        super(username, password);
    }

    public BasicAuthInterceptor(String username, String password, Charset charset) {
        super(username, password, charset);
    }
}
