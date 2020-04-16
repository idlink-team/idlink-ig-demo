package demo.reference.identity;

import demo.tools.AdminApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.AdminUpdateUserRequest;
import idlink.ig.client.model.AdminUpdateUserResponse;
import io.swagger.client.ApiException;

public class AdminUpdateUserDemo {

    // api client id
    public static String apiClientId = PropertiesUtils.getApiClientId();

    // api secret
    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * Update user
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Create instance
        AdminApi adminApi = new AdminApi();
        // Update user
        AdminUpdateUserRequest updateUserRequest = new AdminUpdateUserRequest().putAttributesItem("username", "Alex").username("Alex2");
        AdminUpdateUserResponse updateUserResponse = adminApi.adminUpdateUser(updateUserRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(updateUserResponse);
    }
}/
