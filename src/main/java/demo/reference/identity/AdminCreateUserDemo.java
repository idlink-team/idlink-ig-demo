package demo.reference.identity;

import demo.tools.AdminApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.AdminCreateUserRequest;
import idlink.ig.client.model.AdminCreateUserResponse;
import io.swagger.client.ApiException;

public class AdminCreateUserDemo {

    // api client id
    public static String apiClientId = PropertiesUtils.getApiClientId();

    // api secret
    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * Create user
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Create instance
        AdminApi adminApi = new AdminApi();
        // Create user
        AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
                .putAttributesItem("username", "Alex")
                .putAttributesItem("enable", true)
                .putAttributesItem("password", "sda142&h4j2");
        AdminCreateUserResponse personCreateResponse = adminApi.adminCreateUser(createUserRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(personCreateResponse);
    }
}
