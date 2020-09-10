package demo.reference.oauth;

import com.google.gson.Gson;
import demo.tools.AuthApiToken;
import demo.tools.JwtParseUtils;
import demo.tools.PropertiesUtils;
import demo.tools.TenantApiToken;
import idlink.ig.client.api.OAuth2Api;
import idlink.ig.client.api.TenantApi;
import idlink.ig.client.model.*;
import io.swagger.client.ApiException;

import java.io.IOException;
import java.util.Map;

public class OAuth2LoginForTMallDemo {

    public static String apiClientId = PropertiesUtils.getApiClientId();

    public static String apiSecret = PropertiesUtils.getApiSecret();

    // tenant client id
    public static String tenantId = PropertiesUtils.getTenantId();

    // tenant secret
    public static String tenantSecret = PropertiesUtils.getTenantSecret();

    /**
     * get access_token For Ali TMALL By OAuth2
     */
    public static void main(String[] args) throws ApiException, IOException {
        String authorization = AuthApiToken.build(apiClientId, apiSecret);
        OAuth2Api oAuth2Api = new OAuth2Api();
        //Step 1
        OAuth2GetReqCodeResponse step1 = oAuth2Api.oAuth2AuthorizeGetCode("your_url", "state", authorization);

        String reqCode = step1.getData();
        System.out.println("reqCode = " + reqCode);

        //Step 2
        OAuth2GetAuthCodeResponse step2 = oAuth2Api.oAuth2OAuth2LoginGetCode("sda142&h4j2", reqCode, "Alex", authorization);
        String authCode = step2.getData();
        System.out.println("authCode = " + authCode);


        //Step 3
        authorization = AuthApiToken.build(apiClientId, tmallOauth2Secret());
        String loginResponse = oAuth2Api.oAuth2TmallToken(authCode,"authorization_code",  "", authorization);
        System.out.println("response = " + loginResponse);

//        printChildreResult(loginResponse);

    }

    private static void printChildreResult(String loginResponse) {
        System.out.println("====================================================");
        Map response = new Gson().fromJson(loginResponse, Map.class);
        String access_token = (String) response.get("access_token");
        if (access_token == null) {
            String httpStatus = (String) response.get("httpStatus");
            String detail = (String) response.get("detail");
            String error = (String) response.get("error");
            String error_description = (String) response.get("error_description");

            System.out.println("httpStatus = " + httpStatus);
            System.out.println("detail = " + detail);
            System.out.println("error = " + error);
            System.out.println("error_description = " + error_description);
        } else {
            String refresh_token = (String) response.get("refresh_token");
            Object expires_in = response.get("expires_in");
            System.out.println("tokens: ");
            System.out.println("access_token = " + access_token);
            System.out.println("refresh_token = " + refresh_token);
            System.out.println("expires_in = " + expires_in);
            JwtParseUtils.printJwt(access_token);
        }
    }

    private static String tmallOauth2Secret() throws ApiException {
        // Build token before calling each api
        TenantApiToken token = TenantApiToken.build(tenantId, tenantSecret);
        // Create instance
        TenantApi tenantApi = new TenantApi();
        // get from: AdminListIdentityPoolsDemo
        String identityId = apiClientId;
        // Get client by poolId
        AdminGetClientByPoolIdRequest getClientByPoolIdRequest = new AdminGetClientByPoolIdRequest();
        getClientByPoolIdRequest.setIdentityId(identityId);
        AdminGetClientByPoolIdResponse adminGetClientByPoolId = tenantApi.adminGetClientByPoolId(getClientByPoolIdRequest, token.getX_API_TENANT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        //TmallOauth2Secret
        String oauth2Secret = (String) ((Map)adminGetClientByPoolId.getData()).get("oauth2Secret");
        return oauth2Secret;
    }
}
