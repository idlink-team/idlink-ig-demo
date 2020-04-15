package demo.reference;

import com.google.gson.Gson;
import demo.tools.AdminApiToken;
import demo.tools.AuthApiToken;
import demo.tools.JwtParseUtils;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
//import idlink.ig.client.api.AuthenticationApi;
import idlink.ig.client.model.AdminExchangeAccessTokenRequest;
import idlink.ig.client.model.AdminInitialLoginRequest;
import io.swagger.client.ApiException;
import java.util.HashMap;
import java.util.Map;

/**
 * Step1. Login with username & password
 * Step2. Exchange access token
 */
public class AdminExchangeAccessTokenDemo {

    /**
     * Api client id
     */
    public static String apiClientId = PropertiesUtils.getApiClientId();

    /**
     * Admin api secret
     */
    public static String apiSecret = PropertiesUtils.getApiSecret();

    public static void main(String[] args) throws ApiException {

//        // Build token before calling each admin api
//        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
//
//        // Create Api Instances
//        AuthenticationApi authApi = new AuthenticationApi();
//        AdminApi adminApi = new AdminApi();
//
//        // Step1. Login with username & passpersonLoginword
//        AdminInitialLoginRequest loginRequest = new AdminInitialLoginRequest();
//        loginRequest.userType(AdminInitialLoginRequest.UserTypeEnum.PERSON).username("Java39").password("435435vdf");
//        String loginResult = adminApi.adminInitialLogin(loginRequest, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
//
//        // Login result
//        System.out.println("Login successful and get token: ");
//        System.out.println(loginResult);
//
//        Gson gson = new Gson();
//        HashMap tokenMap = gson.fromJson(loginResult, HashMap.class);
//        String access_token = tokenMap.get("access_token").toString();
//
//        // Step2. Exchange access token
//        AdminExchangeAccessTokenRequest request = new AdminExchangeAccessTokenRequest();
//        request.accessToken(access_token);
//        Map clientMetaDataMap = new HashMap<>();
//        clientMetaDataMap.put("scope", "123");
//        request.clientMetadata(clientMetaDataMap);
//        String newAccessToken = adminApi.adminExchangeAccessToken(request, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
//        // Exchange result
//        System.out.println("exchanged access token: ");
//        System.out.println(newAccessToken);
//
//        JwtParseUtils.printJwt(newAccessToken);
    }
}
