package demo.reference;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import demo.tools.AdminApiToken;
import demo.tools.JwtParseUtils;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.*;
import io.swagger.client.ApiException;

public class IdentityPoolUserLoginExchangeTokenDemo {

    public static String apiClientId = PropertiesUtils.getApiClientId();
    /**
     * Admin api secret
     */
    public static String apiSecret = PropertiesUtils.getApiSecret();

    public static void main(String[] args) throws ApiException {

        //Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Create Api Instance
        AdminApi adminApi = new AdminApi();

        AdminCheckUserRequest checkUserRequest = new AdminCheckUserRequest()
                .putAttributesItem("username", "Tom");
        //Step 1. check User Tom isExist
        AdminCheckUserResponse checkUserResponse = adminApi.adminCheckUserExist(checkUserRequest, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());

        if (checkUserResponse.isExist()) {
            System.out.println("Oops, person 'Tom' already exists!");
        } else {
            System.out.println("Person 'Tom' doesn't exists.");
            // Step 2. If 'Tom' doesn't exists. Create User 'Tom'
            AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
                    // check username eq "Tom"
                    .putAttributesItem("username", "Tom")
                    .putAttributesItem("enable", true)
                    .putAttributesItem("password", "sda142&h4j2");
            AdminCreateUserResponse personCreateResponse = adminApi.adminCreateUser(createUserRequest,
                    token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
            if ("OK".equals(personCreateResponse.getHttpStatus())) {
                System.out.println("Person 'Tom' created.");
            } else {
                System.out.println(personCreateResponse);
                return;
            }
        }

        // Step 3. Login and show userInfo
        AdminInitialLoginRequest initialLoginRequest = new AdminInitialLoginRequest()
                .putDynamicClaimItem("host","127.0.0.99")
                .username("Tom").password("sda142&h4j2");
        AdminInitialLoginResponse adminInitialLoginResponse = adminApi.adminInitialLogin(initialLoginRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());

        String access_token;
        if ("OK".equals(adminInitialLoginResponse.getHttpStatus())) {
            System.out.println("Login Success");
            System.out.println(adminInitialLoginResponse.getData());
            JsonObject jsonObject = new Gson().fromJson(adminInitialLoginResponse.getData(), JsonObject.class);
            access_token = jsonObject.get("access_token").getAsString();
            System.out.println("login data : ");
            JwtParseUtils.printJwt(access_token);
        } else {
            System.out.println(initialLoginRequest);
            return;
        }
        // Step 4. exchange Token and show userInfo
        AdminExchangeAccessTokenRequest exchangeAccessTokenRequest = new AdminExchangeAccessTokenRequest()
                .putDynamicClaimItem("host","127.0.0.1")
                .accessToken(access_token);
        AdminExchangeAccessTokenResponse exchangeAccessTokenResponse = adminApi.adminExchangeAccessToken(exchangeAccessTokenRequest, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());

        if ("OK".equals(exchangeAccessTokenResponse.getHttpStatus())) {
            System.out.println("Exchange Access_Token Success");
            access_token = exchangeAccessTokenResponse.getData();
            System.out.println("Exchange Access_Token : ");
            JwtParseUtils.printJwt(access_token);
        } else {
            System.out.println(initialLoginRequest);
        }

        // Step 5. Delete person User "Tom"
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
