package demo.reference.tenant;

import demo.tools.PropertiesUtils;
import demo.tools.TenantApiToken;
import idlink.ig.client.api.TenantApi;
import idlink.ig.client.model.AdminGenerateNewClientSecretRequest;
import idlink.ig.client.model.AdminGenerateNewClientSecretResponse;
import io.swagger.client.ApiException;

public class AdminGenerateNewClientSecretDemo {

    // tenant client id
    public static String tenantId = PropertiesUtils.getTenantId();

    // tenant secret
    public static String tenantSecret = PropertiesUtils.getTenantSecret();

    /**
     * Generate new client secret
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        TenantApiToken token = TenantApiToken.build(tenantId, tenantSecret);
        // Create instance
        TenantApi tenantApi = new TenantApi();
        // get from: AdminListIdentityPoolsDemo
        String identityId = "87b5af73-c4bc-4aea-91c5-508394d14717";
        // Generate new client secret
        AdminGenerateNewClientSecretRequest generateNewClientSecretRequest = new AdminGenerateNewClientSecretRequest();
        generateNewClientSecretRequest.setIdentityId(identityId);
        AdminGenerateNewClientSecretResponse generateNewClientSecretResponse = tenantApi.adminGenerateNewClientSecret(generateNewClientSecretRequest,
                token.getX_API_TENANT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(generateNewClientSecretResponse);
    }
}
