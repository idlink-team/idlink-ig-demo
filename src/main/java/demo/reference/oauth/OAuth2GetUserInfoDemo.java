package demo.reference.oauth;

import demo.tools.AuthApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.OAuth2Api;
import idlink.ig.client.model.OAuth2GetUserInfoResponse;
import io.swagger.client.ApiException;

public class OAuth2GetUserInfoDemo {

    public static String apiClientId = PropertiesUtils.getApiClientId();

    public static String apiSecret = PropertiesUtils.getApiSecret();

    // Get From OAuth2LoginByTokenDemo
    private static final String token = "eyJraWQiOiJLcml0aW9zIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJLcml0aW9zIiwiYXVkIjoiNzgzNDYwMWEtNDNiMi00MTRkLWJiNjAtOGNlNmMzY2FiNzRmIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwiaXNzIjoiaHR0cHM6Ly9hdXRoZGVtby5pZC5saW5rOjE0NDMiLCJleHAiOjE1ODcwMzMyNjUsImlhdCI6MTU4NzAwNDQ2NSwianRpIjoiNTY4ZTQ5ZTgtZDBmYi00OTE1LWEwNmYtY2E1YTZkNzA2MzZhIiwiY2xpZW50X2lkIjoiNzgzNDYwMWEtNDNiMi00MTRkLWJiNjAtOGNlNmMzY2FiNzRmIiwidXNlcm5hbWUiOiJLcml0aW9zIn0.FRDIXeqjqDJ26vKQkMsy7ZuzvMro444YfDaq5VIP7z1N3zWOS4cxzxFMqwBMdHxrjEcV2AlBRHu84I3pkrpln_02Y-S8xj0zU7naw8nbSsn3__TG1-TFKPG_AMLLWauIj-D3NJCyqRCJ8s73uvfnlUqiWVcEC4Na63TON7lrnyA";

    public static void main(String[] args) throws ApiException {
        String authorization = AuthApiToken.build(apiClientId, apiSecret);
        OAuth2Api oAuth2Api = new OAuth2Api();
        final OAuth2GetUserInfoResponse oAuth2GetUserInfoResponse = oAuth2Api.oAuth2GetUserInfo(token, authorization);
        System.out.println("UserInfo :" + oAuth2GetUserInfoResponse.getData());
    }
}
