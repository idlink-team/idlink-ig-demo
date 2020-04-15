package demo.reference.identity;

import demo.tools.AdminApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.AdminDeleteUserRequest;
import idlink.ig.client.model.AdminDeleteUserResponse;
import io.swagger.client.ApiException;

public class AdminDeleteUserDemo {

    // api client id
    public static String apiClientId = PropertiesUtils.getApiClientId();

    // api secret
    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * Delete user
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Create instance
        AdminApi adminApi = new AdminApi();
        // Delete user
        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest().username("Alex");
        AdminDeleteUserResponse adminDeleteUserResponse = adminApi.adminDeleteUser(adminDeleteUserRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(adminDeleteUserResponse);
    }
}
