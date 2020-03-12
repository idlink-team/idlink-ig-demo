package demo.reference;

import com.google.gson.Gson;
import demo.tools.AuthApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AuthenticationApi;
import io.swagger.client.ApiException;

import java.util.HashMap;

/**
 * Step1. Login with username & password
 * Step2. Verify the valid access token
 * Step3. Verify the invalid access token
 */
public class VerifyAccessTokenDemo {
    /**
     * Client id
     */
    public static final String clientId = PropertiesUtils.getApiClientId();

    /**
     * Client secret
     */
    public static final String clientSecret = PropertiesUtils.getApiSecret();

    public static void main(String[] args) throws ApiException {
        // Build authorization header
        String authorization = AuthApiToken.build(clientId, clientSecret);
        // Create Api Instance
        AuthenticationApi authApi = new AuthenticationApi();

        // Step1. Login with username & password
        String oauth2Tokens = authApi.deviceLogin(authorization, "password", "sda142&h4j2", null,null, "testDevice");
        // Login result
        System.out.println("Login successful and get OAuth tokens: ");
        System.out.println(oauth2Tokens);

        Gson gson = new Gson();
        HashMap tokenMap = gson.fromJson(oauth2Tokens, HashMap.class);
        String access_token = tokenMap.get("access_token").toString();

        // Step2. Verify the valid access token
        Boolean verifyResultTrue = authApi.verifyAccessToken(authorization, access_token);
        System.out.println("Verify the valid access token, and the verify result is: " + verifyResultTrue);

        // Step3. Verify the invalid access token
        String invalid_access_token = "39b3af21-2b32-4f61-bd53-f05b6465641d";
        Boolean verifyResultFalse = authApi.verifyAccessToken(authorization, invalid_access_token);
        System.out.println("Verify the invalid access token, and the verify result is: " + verifyResultFalse);
    }
}
