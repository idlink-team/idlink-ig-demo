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
        String access_token = "eyJraWQiOiJBbGV4IiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJBbGV4IiwiYXVkIjoiNDhmZGVkMjEtOWEwMC00Yjc0LTlkY2YtNjFjZTllYmFkNjFmIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwib3BlcmF0ZVN5c3RlbSI6IndpbmRvd3MiLCJpc3MiOiJodHRwczovL2F1dGhkZW1vLmlkLmxpbms6MTQ0MyIsImhvc3QiOiIxMjcuMC4wLjEiLCJleHAiOjE1ODcwNDczMTMsImlhdCI6MTU4NzAxODUxMywianRpIjoiNTA3MTczMmUtZjJiNS00NmE4LWFjOTktNTRkZmQwZmZlOWQxIiwiY2xpZW50X2lkIjoiNDhmZGVkMjEtOWEwMC00Yjc0LTlkY2YtNjFjZTllYmFkNjFmIiwidXNlcm5hbWUiOiJBbGV4In0.H82KFinzPpM9Quhz9-J7ndX2M0CJaOfuWR34HnmG1WVUvUG2k5HtNTr-YxQ9ctKU61XLXNeXB-eQlELGujZk5OoEy5Pu8iU0oDrFgsY1giX1hBTSwFgtWvP3aqQTrC5O0ECojR1eJrqifnxUlffLMw7pGrDlcBkzeBoPWIZjsq0";// Exchange access token
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
