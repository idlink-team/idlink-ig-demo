package demo.reference.identity;

import demo.tools.AdminApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.AdminChangePasswordRequest;
import idlink.ig.client.model.AdminChangePasswordResponse;
import io.swagger.client.ApiException;

public class AdminChangePasswordDemo {

    // api client id
    public static String apiClientId = PropertiesUtils.getApiClientId();

    // api secret
    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * Change password
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Create instance
        AdminApi adminApi = new AdminApi();
        // Change password
        AdminChangePasswordRequest changePasswordRequest = new AdminChangePasswordRequest().username("Kritios").password("h4j2&sda142");
        AdminChangePasswordResponse changePasswordResponse = adminApi.adminChangePassword(changePasswordRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(changePasswordResponse);
    }
}
