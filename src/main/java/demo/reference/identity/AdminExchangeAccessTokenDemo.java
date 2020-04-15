package demo.reference.identity;

import demo.tools.AdminApiToken;
import demo.tools.JwtParseUtils;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.AdminExchangeAccessTokenRequest;
import idlink.ig.client.model.AdminExchangeAccessTokenResponse;
import io.swagger.client.ApiException;

public class AdminExchangeAccessTokenDemo {

    // api client id
    public static String apiClientId = PropertiesUtils.getApiClientId();

    // api secret
    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * Exchange access token
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Create instance
        AdminApi adminApi = new AdminApi();
        // access token: get from AdminInitialLoginDemo
        String access_token = "eyJraWQiOiJLcml0aW9zIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJLcml0aW9zIiwiYXVkIjoiNzgzNDYwMWEtNDNiMi00MTRkLWJiNjAtOGNlNmMzY2FiNzRmIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwib3BlcmF0ZVN5c3RlbSI6IndpbmRvd3MiLCJpc3MiOiJodHRwczovL2F1dGhkZW1vLmlkLmxpbms6MTQ0MyIsImhvc3QiOiIxMjcuMC4wLjEiLCJleHAiOjE1ODY5OTQ4MTAsImlhdCI6MTU4Njk2NjAxMCwianRpIjoiZDBhMDFhN2QtZjQ2NC00YTRlLTgwOTItZTg3NDE5Yjk1OTJkIiwiY2xpZW50X2lkIjoiNzgzNDYwMWEtNDNiMi00MTRkLWJiNjAtOGNlNmMzY2FiNzRmIiwidXNlcm5hbWUiOiJLcml0aW9zIn0.ZCeZuXvnSgQiPt0NYUIyr7JB5tniOt3qbQFLAjy5YTejB2F-1YsOssbbuw2N22r8u6ePFDWqkTqURxZ9ah1CLyfCrPMhGF1-eR1tzO9g3CEZGlWXYta84GBOoG4bm5NBT06Rz7wskVc76eODK3pxVNNioJlZiF3Yo6vUUNIG760";
        // Exchange access token
        AdminExchangeAccessTokenRequest exchangeAccessTokenRequest = new AdminExchangeAccessTokenRequest()
                .putDynamicClaimItem("host","127.0.0.100")
                .putDynamicClaimItem("operateSystem","linux")
                .accessToken(access_token);
        AdminExchangeAccessTokenResponse exchangeAccessTokenResponse = adminApi.adminExchangeAccessToken(exchangeAccessTokenRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        String newAccessToken = exchangeAccessTokenResponse.getData();
        System.out.println("new access_token: ");
        System.out.println(newAccessToken);
        JwtParseUtils.printJwt(newAccessToken);
    }
}
