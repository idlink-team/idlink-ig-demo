package demo.scenario;

import demo.tools.AdminApiToken;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.*;
import io.swagger.client.ApiException;

import java.util.Map;

/**
 * Step1. Check if user "Kevin" exist
 * Step2. Create user when "Kevin" no exist
 * Step3. Login user by AdminInitialLogin API to get tokens
 * Step4. Clean up data, delete the user "Kevin" to make sure this demo could run again
 */
public class A_AppCallScenario {

    /**
     * Admin api
     */
    private static final AdminApi adminApi = new AdminApi();

    /**
     * Api client id
     */
    public static String apiClientId = "gig";

    /**
     * Admin api secret
     */
    public static String apiSecret = "dakjdiewsl2854o23";

    /**
     * User to test, please change it when "Kevin" already exists
     */
    public static String userToCreate = "Kevin";

    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);

        // Step1. Check if user "Kevin" exist
        AdminCheckUserRequest checkUserRequest = new AdminCheckUserRequest()
                .userType(AdminCheckUserRequest.UserTypeEnum.PERSON)
                .putAttributesItem("username", userToCreate);
        AdminCheckUserResponse checkUserResponse = adminApi.adminCheckUserExist(checkUserRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println("User " + userToCreate + " exist check result: " + checkUserResponse.isExist());
        if (checkUserResponse.isExist()) {
            System.out.println("User " + userToCreate + " already exists, change one please.");
            return;
        }

        // Step2. Create user when "Kevin" no exist
        AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
                .userType(AdminCreateUserRequest.UserTypeEnum.PERSON)
                .putAttributesItem("username", userToCreate)
                .putAttributesItem("password", "435435vdf")
                .putAttributesItem("enable", true)
                .putAttributesItem("mobile", "13980749266");
        AdminCreateUserResponse userCreateResponse = adminApi.adminCreateUser(createUserRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(userCreateResponse.getHttpStatus())) {
            System.out.println("User " + userToCreate + " created.");
        } else {
            System.out.println("User " + userToCreate + " create failed:");
            System.out.println(userCreateResponse);
            return;
        }

        // Step3. Login user to get tokens
        AdminInitialLoginRequest initialLoginRequest = new AdminInitialLoginRequest()
                .userType(AdminInitialLoginRequest.UserTypeEnum.PERSON)
                .username(userToCreate)
                .password("435435vdf");
        String oAuthTokens = adminApi.adminInitialLogin(initialLoginRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println("Logged in and got OAuth tokens: ");
        System.out.println(oAuthTokens);

        // Step4. Clean up data, delete the user "Kevin" to make sure this demo could run again
        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest()
                .userType(AdminDeleteUserRequest.UserTypeEnum.PERSON)
                .username(userToCreate);
        AdminDeleteUserResponse adminDeleteUserResponse = adminApi.adminDeleteUser(adminDeleteUserRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(adminDeleteUserResponse.getHttpStatus())) {
            System.out.println("User " + userToCreate + " has been deleted to make sure this demo could be run again.");
        } else {
            System.out.println(adminDeleteUserRequest);
        }
    }

}
