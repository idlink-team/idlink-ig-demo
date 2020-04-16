package demo.reference.identity;

import demo.tools.AdminApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.AdminEnableUserRequest;
import idlink.ig.client.model.AdminEnableUserResponse;
import io.swagger.client.ApiException;

public class AdminEnableUserDemo {

    // api client id
    public static String apiClientId = PropertiesUtils.getApiClientId();

    // api secret
    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * Enable user
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Create instance
        AdminApi adminApi = new AdminApi();
        // Enable user
        AdminEnableUserRequest enableUserRequest = new AdminEnableUserRequest().username("Alex");
        AdminEnableUserResponse enableUserResponse = adminApi.adminEnableUser(enableUserRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(enableUserResponse);
    }
}
