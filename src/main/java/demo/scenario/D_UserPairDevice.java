package demo.scenario;

import demo.tools.AdminApiToken;
import demo.tools.AuthApiToken;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.api.AuthenticationApi;
import idlink.ig.client.model.*;
import io.swagger.client.ApiException;

import java.util.UUID;

/**
 * Step1. Create Device User
 * Step2. Login device user by OAuth2 grant_type=password to get the tokens
 * Step3. Clean up data, delete the device user to make sure this demo could run again
 */
public class D_UserPairDevice {

    /**
     * Admin api
     */
    private static final AdminApi adminApi = new AdminApi();

    /**
     * Authentication api
     */
    private static final AuthenticationApi authApi = new AuthenticationApi();

    /**
     * Api client id
     */
    public static String apiClientId = "gig";

    /**
     * Admin api secret
     */
    public static String apiSecret = "dakjdiewsl2854o23";

    /**
     * Client id
     */
    public static final String clientId = "gig";

    /**
     * Client secret
     */
    public static final String clientSecret = "ad398u21ijw3s9w393";

    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken adminApiToken = AdminApiToken.build(apiClientId, apiSecret);

        // Build authorization header value for OAuth2 login
        String authorization = AuthApiToken.build(clientId, clientSecret);

        // Generate an uuid as device username
        String userToCreate = UUID.randomUUID().toString();

        // Step1. Create Device User
        AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
                .userType(AdminCreateUserRequest.UserTypeEnum.DEVICE)
                .putAttributesItem("username", userToCreate)
                .putAttributesItem("password", "dsklgfi45")
                .putAttributesItem("enable", true);
        AdminCreateUserResponse adminCreateUserResponse = adminApi.adminCreateUser(createUserRequest,
                adminApiToken.getX_API_CLIENT_ID(), adminApiToken.getX_API_TIMESTAMP(), adminApiToken.getX_API_TOKEN());
        if ("OK".equals(adminCreateUserResponse.getHttpStatus())) {
            System.out.println("User " + userToCreate + " created.");
        } else {
            System.out.println("User " + userToCreate + " create failed:");
            System.out.println(adminCreateUserResponse);
            return;
        }

        // Step2. Login device user by OAuth2 grant_type=password to get the tokens
        String deviceLoginResult = authApi.deviceLogin(authorization, "password", "dsklgfi45", null, null, userToCreate);
        System.out.println("Logged in and got OAuth tokens: ");
        System.out.println(deviceLoginResult);

        // Step3. Clean up data, delete the device user to make sure this demo could run again
        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest()
                .userType(AdminDeleteUserRequest.UserTypeEnum.DEVICE)
                .username(userToCreate);
        AdminDeleteUserResponse adminDeleteUserResponse = adminApi.adminDeleteUser(adminDeleteUserRequest,
                adminApiToken.getX_API_CLIENT_ID(), adminApiToken.getX_API_TIMESTAMP(), adminApiToken.getX_API_TOKEN());
        if ("OK".equals(adminDeleteUserResponse.getHttpStatus())) {
            System.out.println("User " + userToCreate + " has been deleted to make sure this demo could be run again.");
        } else {
            System.out.println(adminDeleteUserRequest);
        }
    }

}
