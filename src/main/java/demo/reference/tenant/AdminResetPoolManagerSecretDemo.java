package demo.reference.tenant;

import demo.tools.PropertiesUtils;
import demo.tools.TenantApiToken;
import idlink.ig.client.api.TenantApi;
import idlink.ig.client.model.AdminResetPoolManagerSecretResponse;
import io.swagger.client.ApiException;

public class AdminResetPoolManagerSecretDemo {

    // tenant client id
    public static String tenantId = PropertiesUtils.getTenantId();

    // tenant secret
    public static String tenantSecret = PropertiesUtils.getTenantSecret();

    /**
     * Reset pool manager secret
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        TenantApiToken token = TenantApiToken.build(tenantId, tenantSecret);
        // Create instance
        TenantApi tenantApi = new TenantApi();
        // Reset pool manager secret
        AdminResetPoolManagerSecretResponse adminResetPoolManagerSecretResponse = tenantApi.adminResetPoolManagerSecret(token.getX_API_TENANT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(adminResetPoolManagerSecretResponse);
    }
}
