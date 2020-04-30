package demo.reference.oauth;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import demo.tools.AdminApiToken;
import demo.tools.AuthApiToken;
import demo.tools.JwtParseUtils;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.api.OAuth2Api;
import idlink.ig.client.model.AdminInitialLoginRequest;
import idlink.ig.client.model.AdminInitialLoginResponse;
import idlink.ig.client.model.OAuth2LoginResponse;
import io.swagger.client.ApiException;

public class OAuth2LoginByTokenDemo {

    public static String apiClientId = PropertiesUtils.getApiClientId();

    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * Login By OAuth2 User's Token
     */
    public static void main(String[] args) throws ApiException {
        String authorization = AuthApiToken.build(apiClientId, apiSecret);
        OAuth2Api oAuth2Api = new OAuth2Api();
        final OAuth2LoginResponse loginResponse = oAuth2Api.oAuth2Token("password", "sda142&h4j2", "", "Alex", authorization);
        System.out.println("tokens: ");
        System.out.println(loginResponse.getData());
        JsonObject jsonObject = new Gson().fromJson(loginResponse.getData(), JsonObject.class);
        String access_token = jsonObject.get("access_token").getAsString();
        System.out.println("access_token: ");
        JwtParseUtils.printJwt(access_token);
    }
}
