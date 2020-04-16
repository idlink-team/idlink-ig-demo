package demo.reference.oauth;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import demo.tools.AuthApiToken;
import demo.tools.JwtParseUtils;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.OAuth2Api;
import idlink.ig.client.model.OAuth2LoginResponse;
import io.swagger.client.ApiException;

public class OAuth2LoginByTokenDemo {

    public static String apiClientId = PropertiesUtils.getApiClientId();

    public static String apiSecret = PropertiesUtils.getApiSecret();

    // Get From AdminInitialLoginDemo
    private static final String refresh_token = "eyJraWQiOiJLcml0aW9zIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJLcml0aW9zIiwiYXVkIjoiNzgzNDYwMWEtNDNiMi00MTRkLWJiNjAtOGNlNmMzY2FiNzRmIiwidG9rZW5fdXNlIjoicmVmcmVzaCIsIm9wZXJhdGVTeXN0ZW0iOiJ3aW5kb3dzIiwiaXNzIjoiaHR0cHM6Ly9hdXRoZGVtby5pZC5saW5rOjE0NDMiLCJob3N0IjoiMTI3LjAuMC4xIiwiZXhwIjoxNTg3NjA5MTg4LCJpYXQiOjE1ODcwMDQzODgsImp0aSI6ImE2NDY0MjgzLWFlM2EtNDYzZC05Y2U1LTdhMWYyNDBmODdkYyIsInVzZXJuYW1lIjoiS3JpdGlvcyJ9.QPHL1xhFV-S2w5oCmI8J0XVMsfWEJbDmYUhK7mvY2PV4O9S-nKZLwgx1lVc97HIG77iFtDXhpSBIpMi3twpS76s1mldywxtEbXVEewaXjt6Qae8WxgZwa8wA9TcLf9_eZQ9A6bSHZwtc11ToQc5rGtiu5M1QQUQS-XHMRRsN_F8";

    /**
     * Login By OAuth2 User's Token
     */
    public static void main(String[] args) throws ApiException {
        String authorization = AuthApiToken.build(apiClientId, apiSecret);
        OAuth2Api oAuth2Api = new OAuth2Api();
        final OAuth2LoginResponse loginResponse = oAuth2Api.oAuth2Token("password", "h4j2&sda142", refresh_token, "Kritios", authorization);
        JsonObject jsonObject = new Gson().fromJson(loginResponse.getData(), JsonObject.class);
        String access_token = jsonObject.get("access_token").getAsString();
        System.out.println("access_token: ");
        System.out.println(access_token);
        JwtParseUtils.printJwt(access_token);
    }
}
