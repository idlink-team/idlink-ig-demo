package demo.reference;

import demo.tools.AdminApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.*;
import io.swagger.client.ApiException;

/**
 * CheckExist, Add, Delete Device
 */
public class AdminCreateDeviceUserDemo {
    /**
     * Api client id
     */
    public static String apiClientId = PropertiesUtils.getApiClientId();
    /**
     * Admin api secret
     */
    public static String apiSecret = PropertiesUtils.getApiSecret();

    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);

        // Create Api Instance
        AdminApi adminApi = new AdminApi();

        // Step 1. Check if User "deviceOfBob" exists
        AdminCheckUserRequest checkUserRequest = new AdminCheckUserRequest()
                // user type should be Device
                .userType(AdminCheckUserRequest.UserTypeEnum.DEVICE)
                // check username eq "deviceOfBob"
                .putAttributesItem("username", "deviceOfBob");
        AdminCheckUserResponse checkUserResponse = adminApi.adminCheckUserExist(checkUserRequest, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if (checkUserResponse.isExist()) {
            System.out.println("Oops, device 'deviceOfBob' already exists!");
            return;
        } else {
            System.out.println("Device 'deviceOfBob' doesn't exists.");
        }

        // Step 2. Create device User "deviceOfBob"
        AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
                // user type should be Device
                .userType(AdminCreateUserRequest.UserTypeEnum.DEVICE)
                // check username eq "deviceOfBob"
                .putAttributesItem("username", "deviceOfBob")
                .putAttributesItem("password", "sda142&h4j2")
                .putAttributesItem("enable", true);
        AdminCreateUserResponse deviceCreateResponse = adminApi.adminCreateUser(createUserRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(deviceCreateResponse.getHttpStatus())) {
            System.out.println("Device 'deviceOfBob' created.");
        } else {
            System.out.println(deviceCreateResponse);
        }

        // Step 3. Delete device User "deviceOfBob"
        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest()
                // user type should be Device
                .userType(AdminDeleteUserRequest.UserTypeEnum.DEVICE)
                .username("deviceOfBob");
        AdminDeleteUserResponse adminDeleteUserResponse = adminApi.adminDeleteUser(adminDeleteUserRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        if ("OK".equals(adminDeleteUserResponse.getHttpStatus())) {
            System.out.println("Device 'deviceOfBob' deleted.");
        } else {
            System.out.println(adminDeleteUserResponse);
        }

    }
}
