package demo.reference;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import demo.tools.AdminApiToken;
import demo.tools.AuthApiToken;
import demo.tools.JwtParseUtils;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.api.OAuth2Api;
import idlink.ig.client.model.*;
import io.swagger.client.ApiException;

public class OAuthLoginVerifyTokenDemo {

    public static String apiClientId = PropertiesUtils.getApiClientId();
    /**
     * Admin api secret
     */
    public static String apiSecret = PropertiesUtils.getApiSecret();

    public static void main(String[] args) throws ApiException {
        //Build token before calling each api
        // Create Api Instance
        AdminApi adminApi = new AdminApi();
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Step 1. check User Tom isExist and If 'Tom' doesn't exists. Create User 'Tom'
        if(createUser(adminApi,token)){
            // Step 2. login by username&password get refresh_token
            AdminInitialLoginRequest initialLoginRequest = new AdminInitialLoginRequest()
                    .putDynamicClaimItem("host","127.0.0.1")
                    .username("Tom").password("sda142&h4j2");
            AdminInitialLoginResponse adminInitialLoginResponse = adminApi.adminInitialLogin(initialLoginRequest,
                    token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
            String refresh_token;
            if("OK".equals(adminInitialLoginResponse.getHttpStatus())){
                System.out.println("Login Success");
                JsonObject jsonObject = new Gson().fromJson(adminInitialLoginResponse.getData(), JsonObject.class);
                refresh_token = jsonObject.get("refresh_token").getAsString();
            }else{
                System.out.println(adminInitialLoginResponse);
                return;
            }
            // Step 3. login by oauth2 user's token
            String authorization = AuthApiToken.build(apiClientId, apiSecret);
            OAuth2Api oAuth2Api = new OAuth2Api();
            String grant_type = "password";
            String username = "Tom";
            String password = "sda142&h4j2";
            OAuth2LoginResponse oAuth2LoginResponse = oAuth2Api.oAuth2Token(grant_type, password, refresh_token, username, authorization);
            String access_token ;
            if("OK".equals(oAuth2LoginResponse.getHttpStatus())){
                System.out.println("Login By Token Success");
                JsonObject jsonObject = new Gson().fromJson(adminInitialLoginResponse.getData(), JsonObject.class);
                access_token = jsonObject.get("access_token").getAsString();
                System.out.println("login data : ");
                JwtParseUtils.printJwt(access_token);
            }else{
                System.out.println(oAuth2LoginResponse);
                return;
            }
            // Step 4. verify token
            OAuth2VerifyAccessTokenResponse verifyAccessTokenResponse = oAuth2Api.oAuth2VerifyAccessToken(access_token, authorization);
            if("OK".equals(verifyAccessTokenResponse.getHttpStatus())){
                System.out.println("Verify Token Success");
            }else{
                System.out.println(verifyAccessTokenResponse);
                return;
            }
            // Step 5. by OAuth2 get user info
            OAuth2GetUserInfoResponse getUserInfoResponse = oAuth2Api.oAuth2GetUserInfo(access_token, authorization);
            if("OK".equals(getUserInfoResponse.getHttpStatus())){
                System.out.println("get userInfo success");
                System.out.println("UserInfo :" + getUserInfoResponse.getData());
            }else{
                System.out.println(getUserInfoResponse);
            }
            // Step 6. Delete person User "Tom"
            deleteUser(adminApi,token);
        }
    }

    private static boolean createUser(AdminApi adminApi,AdminApiToken token) throws ApiException {
        AdminCheckUserRequest checkUserRequest = new AdminCheckUserRequest()
                .putAttributesItem("username", "Tom");

        AdminCheckUserResponse checkUserResponse = adminApi.adminCheckUserExist(checkUserRequest, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());

        if (checkUserResponse.isExist()) {
            System.out.println("Oops, person 'Tom' already exists!");
            return true;
        } else {
            System.out.println("Person 'Tom' doesn't exists.");
        }

        AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
                .putAttributesItem("username", "Tom")
                .putAttributesItem("enable", true)
                .putAttributesItem("password", "sda142&h4j2");
        AdminCreateUserResponse personCreateResponse = adminApi.adminCreateUser(createUserRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(personCreateResponse.getHttpStatus())) {
            System.out.println("Person 'Tom' created.");
        } else {
            System.out.println(personCreateResponse);
            return false;
        }
        return true;
    }

    private static void deleteUser(AdminApi adminApi,AdminApiToken token) throws ApiException{
        // Step 3. Delete person User "Tom"
        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest()
                // user type should be Person
                .username("Tom");
        AdminDeleteUserResponse adminDeleteUserResponse = adminApi.adminDeleteUser(adminDeleteUserRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(adminDeleteUserResponse.getHttpStatus())) {
            System.out.println("Person 'Tom' deleted.");
        } else {
            System.out.println(adminDeleteUserRequest);
        }
    }
}
