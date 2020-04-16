package demo.reference.oauth;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import demo.tools.AdminApiToken;
import demo.tools.AuthApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.api.OAuth2Api;
import idlink.ig.client.model.AdminInitialLoginRequest;
import idlink.ig.client.model.AdminInitialLoginResponse;
import idlink.ig.client.model.OAuth2LoginResponse;
import idlink.ig.client.model.OAuth2VerifyAccessTokenResponse;
import io.swagger.client.ApiException;

public class OAuth2VerifyAccessTokenDemo {

    public static String apiClientId = PropertiesUtils.getApiClientId();

    public static String apiSecret = PropertiesUtils.getApiSecret();

    public static void main(String[] args) throws ApiException {
        String authorization = AuthApiToken.build(apiClientId, apiSecret);
        OAuth2Api oAuth2Api = new OAuth2Api();
        String token = getOAuth2AccessToken(authorization,oAuth2Api);
        final OAuth2VerifyAccessTokenResponse oAuth2VerifyAccessTokenResponse = oAuth2Api.oAuth2VerifyAccessToken(token, authorization);
        System.out.println("verify result : " + oAuth2VerifyAccessTokenResponse.isData());
    }

    private static String getOAuth2AccessToken(String authorization,OAuth2Api oAuth2Api) throws ApiException{
        String refresh_token = getAdminRefreshToken();
        OAuth2LoginResponse loginResponse = oAuth2Api.oAuth2Token("password", "sda142&h4j2", refresh_token, "Alex", authorization);
        JsonObject jsonObject = new Gson().fromJson(loginResponse.getData(), JsonObject.class);
        return jsonObject.get("access_token").getAsString();
    }

    private static String getAdminRefreshToken() throws ApiException{
        // api client id
        String apiClientId = PropertiesUtils.getApiClientId();
        // api secret
        String apiSecret = PropertiesUtils.getApiSecret();
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Create instance
        AdminApi adminApi = new AdminApi();
        // Initial login
        AdminInitialLoginRequest initialLoginRequest = new AdminInitialLoginRequest()
                .putDynamicClaimItem("host","127.0.0.1")
                .putDynamicClaimItem("operateSystem", "windows")
                .username("Alex").password("sda142&h4j2");
        AdminInitialLoginResponse adminInitialLoginResponse = adminApi.adminInitialLogin(initialLoginRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        JsonObject jsonObject = new Gson().fromJson(adminInitialLoginResponse.getData(), JsonObject.class);
        return jsonObject.get("access_token").getAsString();
    }
}
