package demo.reference;

import com.google.gson.Gson;
import demo.tools.AdminApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
//import idlink.ig.client.api.AuthenticationApi;
import idlink.ig.client.model.AdminInitialLoginRequest;
import io.swagger.client.ApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo same user login with different clientMetadata and get different id_token claims
 * <p>
 * Important Notes:
 * Please visit https://authdemo.id.link:1445/#/1ac34308-9688-49cf-8c1b-8b64a2b094df to view the person pre-token-generation script.
 * Feel free to edit the script online to meet your needs.
 */
public class AdminInitialLoginAndCustomizeJWTTokenDemo {
//    /**
//     * Api client id
//     */
//    public static String apiClientId = PropertiesUtils.getApiClientId();
//    /**
//     * Admin api secret
//     */
//    public static String apiSecret = PropertiesUtils.getApiSecret();
//    /**
//     * Authentication api
//     */
//    private static final AuthenticationApi authApi = new AuthenticationApi();
//
//    public static void main(String[] args) throws ApiException {
//        // Build token before calling each api
//        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
//
//        // Create Api Instance
//        AdminApi adminApi = new AdminApi();
//
//        // Login with testUser
//        AdminInitialLoginRequest adminInitialLoginRequest = new AdminInitialLoginRequest()
//                .userType(AdminInitialLoginRequest.UserTypeEnum.PERSON)
//                .username("testUser").password("kdi@43k423")
//                // Add clientMeta for customize claims.
//                .putClientMetadataItem("meta", "useClaim1");
//        String userLoginResult = adminApi.adminInitialLogin(adminInitialLoginRequest,
//                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
//        System.out.println("Login successful and get OAuth tokens: ");
//        System.out.println(userLoginResult);
//
//        // Login with testUser Again
//        adminInitialLoginRequest = new AdminInitialLoginRequest()
//                .userType(AdminInitialLoginRequest.UserTypeEnum.PERSON)
//                .username("testUser").password("kdi@43k423");
//        userLoginResult = adminApi.adminInitialLogin(adminInitialLoginRequest,
//                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
//        System.out.println("\n Login again and get different id_token: ");
//        System.out.println(userLoginResult);
//
//        Map userTokens = new Gson().fromJson(userLoginResult, HashMap.class);
//        String authorization = userTokens.get("token_type") + " " + java.util.Base64.getEncoder().encodeToString(userTokens.get("access_token").toString().getBytes());
//        String userInfo = authApi.oauth2UserInfo(authorization);
//        System.out.println("\n Get user info by access Token: ");
//        System.out.println(userInfo);
//    }
}
