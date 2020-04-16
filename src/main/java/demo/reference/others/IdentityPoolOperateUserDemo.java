package demo.reference.others;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import demo.tools.AdminApiToken;
import demo.tools.JwtParseUtils;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.*;
import io.swagger.client.ApiException;

/**
 * 1. Check user exists.
 * 2. If user doesn't exists, create user.
 * 3. Disable user.
 * 4. User login fail.
 * 5. Enable user.
 * 6. User login again.
 * 7. Exchange access token.
 * 8. Change password.
 * 9. User login again after change password.
 * 10.Update user.
 * 11.User login again after update.
 * 12.Delete user.
 */
public class IdentityPoolOperateUserDemo {

    // api client id
    public static String apiClientId = PropertiesUtils.getApiClientId();

    // api secret
    public static String apiSecret = PropertiesUtils.getApiSecret();

    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Create instance
        AdminApi adminApi = new AdminApi();
        // 1. Check user exists
        AdminCheckUserRequest checkUserRequest = new AdminCheckUserRequest().putAttributesItem("username", "Tom");
        AdminCheckUserResponse checkUserResponse = adminApi.adminCheckUserExist(checkUserRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if (checkUserResponse.isExist()) {
            System.out.println("User 'Tom' already exists!");
        } else {
            System.out.println("User 'Tom' doesn't exists.");
            // 2. If Tom doesn't exists, create user Tom
            AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
                    .putAttributesItem("username", "Tom")
                    .putAttributesItem("enable", true)
                    .putAttributesItem("password", "sda142&h4j2");
            AdminCreateUserResponse personCreateResponse = adminApi.adminCreateUser(createUserRequest, token.getX_API_CLIENT_ID(),
                    token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
            if ("OK".equals(personCreateResponse.getHttpStatus())) {
                System.out.println("User 'Tom' created.");
            } else {
                System.out.println(personCreateResponse);
                return;
            }
        }
        // 3. Disable user 'Tom'
        AdminDisableUserRequest disableUserRequest = new AdminDisableUserRequest().username("Tom");
        AdminDisableUserResponse disableUserResponse = adminApi.adminDisableUser(disableUserRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(disableUserResponse.getHttpStatus())) {
            System.out.println("Disable user 'Tom' success.");
        } else {
            System.out.println(disableUserResponse);
            return;
        }
        // Try to log in with the user name 'Tom', expected login failure because the user is disabled.
        userLogin(adminApi, token);
        // 12. Delete user 'Tom2'
        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest().username("Tom2");
        AdminDeleteUserResponse adminDeleteUserResponse = adminApi.adminDeleteUser(adminDeleteUserRequest, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(adminDeleteUserResponse.getHttpStatus())) {
            System.out.println("User 'Tom' deleted.");
        } else {
            System.out.println(adminDeleteUserRequest);
        }
    }

    public static void userLogin(AdminApi adminApi, AdminApiToken token) throws ApiException {
        // 4. User login
        AdminInitialLoginRequest initialLoginRequest = new AdminInitialLoginRequest()
                .putDynamicClaimItem("host","127.0.0.1")
                .username("Tom").password("sda142&h4j2");
        AdminInitialLoginResponse adminInitialLoginResponse = adminApi.adminInitialLogin(initialLoginRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(adminInitialLoginResponse.getHttpStatus())) {
            //
        } else {
            System.out.println("Login fail because current user already diabled, and the response detail message is:" + adminInitialLoginResponse.getDetail());
            // System.out.println(adminInitialLoginResponse);
        }
        // 5. Enable user 'Tom'
        AdminEnableUserRequest enableUserRequest = new AdminEnableUserRequest().username("Tom");
        AdminEnableUserResponse enableUserResponse = adminApi.adminEnableUser(enableUserRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(enableUserResponse.getHttpStatus())) {
            System.out.println("User 'Tom' enabled");
        }
        // 6. User login again
        AdminInitialLoginResponse adminInitialLoginAgainResponse = adminApi.adminInitialLogin(initialLoginRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        String access_token;
        if ("OK".equals(adminInitialLoginAgainResponse.getHttpStatus())) {
            System.out.println("User 'Tom' login success.");
            JsonObject jsonObject = new Gson().fromJson(adminInitialLoginAgainResponse.getData(), JsonObject.class);
            access_token = jsonObject.get("access_token").getAsString();
            System.out.println("access_token: " + access_token);
            JwtParseUtils.printJwt(access_token);
        } else {
            System.out.println(adminInitialLoginAgainResponse);
            return;
        }
        // 7. Exchange access token
        AdminExchangeAccessTokenRequest exchangeAccessTokenRequest = new AdminExchangeAccessTokenRequest()
                .putDynamicClaimItem("host","127.0.0.100")
                .accessToken(access_token);
        AdminExchangeAccessTokenResponse exchangeAccessTokenResponse = adminApi.adminExchangeAccessToken(exchangeAccessTokenRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(exchangeAccessTokenResponse.getHttpStatus())) {
            System.out.println("Exchange access token success.");
            access_token = exchangeAccessTokenResponse.getData();
            System.out.println("access_token: " + access_token);
            JwtParseUtils.printJwt(access_token);
        } else {
            System.out.println(exchangeAccessTokenResponse);
            return;
        }
        // 8. Change password
        AdminChangePasswordRequest changePasswordRequest = new AdminChangePasswordRequest().username("Tom").password("h4j2&sda142");
        AdminChangePasswordResponse changePasswordResponse = adminApi.adminChangePassword(changePasswordRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(changePasswordResponse.getHttpStatus())) {
            System.out.println("User 'Tom' change password success.");
        } else {
            System.out.println(changePasswordResponse);
            return;
        }
        // 9. User login again after change password
        initialLoginRequest.password("h4j2&sda142");
        AdminInitialLoginResponse adminInitialLoginResponse3 = adminApi.adminInitialLogin(initialLoginRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(adminInitialLoginResponse3.getHttpStatus())) {
            System.out.println("User 'Tom' login success after change password.");
        }
        // 10. Update user 'Tom' to 'Tom2'
        AdminUpdateUserRequest updateUserRequest = new AdminUpdateUserRequest().putAttributesItem("username", "Tom2").username("Tom");
        AdminUpdateUserResponse updateUserResponse = adminApi.adminUpdateUser(updateUserRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(updateUserResponse.getHttpStatus())) {
            System.out.println("Update user success.");
        } else {
            System.out.println(updateUserResponse);
            return;
        }
        // 11. User login again after update
        initialLoginRequest.username("Tom2");
        AdminInitialLoginResponse adminInitialLoginResponse4 = adminApi.adminInitialLogin(initialLoginRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(adminInitialLoginResponse3.getHttpStatus())) {
            System.out.println("User 'Tom2' login success after update.");
        }
    }
}
