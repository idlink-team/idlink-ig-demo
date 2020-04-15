package demo.reference;

import demo.tools.AdminApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.*;
import io.swagger.client.ApiException;

/**
 * CheckExist, Add, Delete Person
 */
public class AdminCreatePersonUserDemo {
//    /**
//     * Api client id
//     */
//    public static String apiClientId = PropertiesUtils.getApiClientId();
//    /**
//     * Admin api secret
//     */
//    public static String apiSecret = PropertiesUtils.getApiSecret();
//
//    public static void main(String[] args) throws ApiException {
//        // Build token before calling each api
//        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
//
//        // Create Api Instance
//        AdminApi adminApi = new AdminApi();
//
//        // Step 1. Check if User "Bob" exists
//        AdminCheckUserRequest checkUserRequest = new AdminCheckUserRequest()
//                // user type should be Person
//                .userType(AdminCheckUserRequest.UserTypeEnum.PERSON)
//                // check username eq "Bob"
//                .putAttributesItem("username", "Bob");
//        AdminCheckUserResponse checkUserResponse = adminApi.adminCheckUserExist(checkUserRequest, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
//        if (checkUserResponse.isExist()) {
//            System.out.println("Oops, person 'Bob' already exists!");
//            return;
//        } else {
//            System.out.println("Person 'Bob' doesn't exists.");
//        }
//
//        // Step 2. Create person User "Bob"
//        AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
//                // user type should be Person
//                .userType(AdminCreateUserRequest.UserTypeEnum.PERSON)
//                // check username eq "Bob"
//                .putAttributesItem("username", "Bob")
//                .putAttributesItem("enable", true)
//                .putAttributesItem("password", "sda142&h4j2")
//                .putAttributesItem("mobile", "13980749267");
//        AdminCreateUserResponse personCreateResponse = adminApi.adminCreateUser(createUserRequest,
//                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
//        if ("OK".equals(personCreateResponse.getHttpStatus())) {
//            System.out.println("Person 'Bob' created.");
//        } else {
//            System.out.println(personCreateResponse);
//        }
//
//        // Step 3. Delete person User "Bob"
//        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest()
//                // user type should be Person
//                .userType(AdminDeleteUserRequest.UserTypeEnum.PERSON)
//                .username("Bob");
//        AdminDeleteUserResponse adminDeleteUserResponse = adminApi.adminDeleteUser(adminDeleteUserRequest,
//                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
//        if ("OK".equals(adminDeleteUserResponse.getHttpStatus())) {
//            System.out.println("Person 'Bob' deleted.");
//        } else {
//            System.out.println(adminDeleteUserRequest);
//        }
//
//    }
}
