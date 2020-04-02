package demo.reference;

import com.google.gson.Gson;
import demo.tools.AdminApiToken;
import demo.tools.AuthApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.api.AuthenticationApi;
import idlink.ig.client.model.AdminExchangeAccessTokenRequest;
import io.swagger.client.ApiException;
import java.util.HashMap;
import java.util.Map;

/**
 * Step1. Login with username & password
 * Step2. Exchange access token
 */
public class AdminExchangeAccessTokenDemo {

    /**
     * Client id
     */
    public static final String clientId = PropertiesUtils.getClientId();

    /**
     * Client secret
     */
    public static final String clientSecret = PropertiesUtils.getClientSecret();

    public static void main(String[] args) throws ApiException {

        // Build authorization header
        String authorization = AuthApiToken.build(clientId, clientSecret);

        // Build token before calling each admin api
        AdminApiToken token = AdminApiToken.build(clientId, clientSecret);

        // Create Api Instances
        AuthenticationApi authApi = new AuthenticationApi();
        AdminApi adminApi = new AdminApi();

        // Step1. Login with username & password
        String oauth2Tokens = authApi.personLogin(authorization, "password", "435435vdf", null,
                null, "Java39");

        // Login result
        System.out.println("Login successful and get OAuth tokens: ");
        System.out.println(oauth2Tokens);

        Gson gson = new Gson();
        HashMap tokenMap = gson.fromJson(oauth2Tokens, HashMap.class);
        String access_token = tokenMap.get("access_token").toString();

        // Step2. Exchange access token
        AdminExchangeAccessTokenRequest request = new AdminExchangeAccessTokenRequest();
        request.accessToken(access_token);
        Map clientMetaDataMap = new HashMap<>();
        clientMetaDataMap.put("ididid", "123");
        request.clientMetadata(clientMetaDataMap);
        String newToken = adminApi.adminExchangeAccessToken(request, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        // Exchange result
        System.out.println("Refresh new token: ");
        System.out.println(newToken);
    }
}
