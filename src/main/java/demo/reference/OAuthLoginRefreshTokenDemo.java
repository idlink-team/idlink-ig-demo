package demo.reference;

import com.google.gson.Gson;
import demo.tools.AuthApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AuthenticationApi;
import io.swagger.client.ApiException;

import java.util.HashMap;

/**
 * Step1. Login with username & password
 * Step2. Refresh token
 */
public class OAuthLoginRefreshTokenDemo {
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
        // Create Api Instance
        AuthenticationApi authApi = new AuthenticationApi();

        // Step1. Login with username & password
        String oauth2Tokens = authApi.deviceLogin(authorization, "password", "sda142&h4j2", null,
                null, "testDevice");
        // Login result
        System.out.println("Login successful and get OAuth tokens: ");
        System.out.println(oauth2Tokens);

        Gson gson = new Gson();
        HashMap tokenMap = gson.fromJson(oauth2Tokens, HashMap.class);
        String refresh_token = tokenMap.get("refresh_token").toString();

        // Step2. Refresh token
        String newTokens = authApi.deviceLogin(authorization, "refresh_token", null, refresh_token, null, null);
        // Login result
        System.out.println("Refresh new token: ");
        System.out.println(newTokens);
    }
}
