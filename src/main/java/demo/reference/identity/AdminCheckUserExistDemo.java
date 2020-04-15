package demo.reference.identity;

import demo.tools.AdminApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.AdminCheckUserRequest;
import idlink.ig.client.model.AdminCheckUserResponse;
import io.swagger.client.ApiException;

public class AdminCheckUserExistDemo {

    // api client id
    public static String apiClientId = PropertiesUtils.getApiClientId();

    // api secret
    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * Check user exist
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Create instance
        AdminApi adminApi = new AdminApi();
        // Check user exists
        AdminCheckUserRequest checkUserRequest = new AdminCheckUserRequest().putAttributesItem("username", "Alex");
        AdminCheckUserResponse checkUserResponse = adminApi.adminCheckUserExist(checkUserRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(checkUserResponse.isExist());
    }
}
