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
        String access_token = "eyJraWQiOiJBbGV4IiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJBbGV4IiwiYXVkIjoiZjQ3NGU5MGUtYzYzMy00YzdlLWFlNjAtZWRlZDhmMzcyODBjIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwib3BlcmF0ZVN5c3RlbSI6ImxpbnV4IiwiaXNzIjoiaHR0cHM6Ly9hdXRoZGVtby5pZC5saW5rOjE0NDMiLCJob3N0IjoiMTI3LjAuMC4xMDAiLCJleHAiOjE1ODcwNTU0NzYsImlhdCI6MTU4NzAyNjY3NiwianRpIjoiZjc4ZWUwNTgtYjY4ZC00NDZlLWI2NGEtM2M4MmMyZjNmMDMwIiwiY2xpZW50X2lkIjoiZjQ3NGU5MGUtYzYzMy00YzdlLWFlNjAtZWRlZDhmMzcyODBjIiwidXNlcm5hbWUiOiJBbGV4In0.FpYEWlF-p0iBMofyF6cxfkLibNIwUuBBaKHWOnocX0O2Tz1_JI6abLdPr17kuDwk7u5-HjrYQ7sjZTk22zXWdcjZ8WlflZ5kxP17uG8DdoWzUZQdJ1dgUIMHP-GE5a99rDoYmGyxV5GtY6kWDMK6kX1Yp6PYujytBAwPulunQ_E";// Exchange access token
        AdminExchangeAccessTokenRequest exchangeAccessTokenRequest = new AdminExchangeAccessTokenRequest()
                .putDynamicClaimItem("scope","abc")
                .accessToken(access_token);
        AdminExchangeAccessTokenResponse exchangeAccessTokenResponse = adminApi.adminExchangeAccessToken(exchangeAccessTokenRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        String newAccessToken = exchangeAccessTokenResponse.getData();
        System.out.println("new access_token: ");
        System.out.println(newAccessToken);
        JwtParseUtils.printJwt(newAccessToken);
    }
}
