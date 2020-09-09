package demo.reference.oauth;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
        final OAuth2LoginResponse loginResponse = oAuth2Api.oAuth2TokenByCode("authorization_code", "Alex", "sda142&h4j2", authCode,"", authorization);
        System.out.println("tokens: ");
        System.out.println(loginResponse.getDetail());
        JsonObject jsonObject = new Gson().fromJson(loginResponse.getDetail(), JsonObject.class);
        String access_token = jsonObject.get("access_token").getAsString();
        System.out.println("access_token: ");
        JwtParseUtils.printJwt(access_token);

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
