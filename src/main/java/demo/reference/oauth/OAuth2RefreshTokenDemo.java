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

public class OAuth2RefreshTokenDemo {

    public static String apiClientId = PropertiesUtils.getApiClientId();

    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * Login By OAuth2 User's Token
     */
    public static void main(String[] args) throws ApiException {
        String authorization = AuthApiToken.build(apiClientId, apiSecret);
        OAuth2Api oAuth2Api = new OAuth2Api();
        String refresh_token = getOAuth2AccessToken(authorization,oAuth2Api);
        OAuth2LoginResponse loginResponse = oAuth2Api.oAuth2Token("refresh_token", "", refresh_token, "", authorization);
        System.out.println(loginResponse.getData());
        JsonObject jsonObject = new Gson().fromJson(loginResponse.getData(), JsonObject.class);
        JwtParseUtils.printJwt(jsonObject.get("access_token").getAsString());
    }

    private static String getOAuth2AccessToken(String authorization,OAuth2Api oAuth2Api) throws ApiException{
        String refresh_token = getAdminRefreshToken();
        OAuth2LoginResponse loginResponse = oAuth2Api.oAuth2Token("password", "sda142&h4j2", refresh_token, "Alex", authorization);
        JsonObject jsonObject = new Gson().fromJson(loginResponse.getData(), JsonObject.class);
        return jsonObject.get("refresh_token").getAsString();
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
