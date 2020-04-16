package demo.reference.tenant;

import demo.tools.PropertiesUtils;
import demo.tools.TenantApiToken;
import idlink.ig.client.api.TenantApi;
import idlink.ig.client.model.AdminAddIdentityPoolRequest;
import idlink.ig.client.model.AdminAddIdentityPoolResponse;
import io.swagger.client.ApiException;

public class AdminAddIdentityPoolDemo {

    // tenant client id
    public static String tenantId = PropertiesUtils.getTenantId();

    // tenant secret
    public static String tenantSecret = PropertiesUtils.getTenantSecret();

    /**
     * Add identity pool
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        TenantApiToken token = TenantApiToken.build(tenantId, tenantSecret);
        // Create instance
        TenantApi tenantApi = new TenantApi();
        // Add identity pool
        AdminAddIdentityPoolRequest addIdentity = new AdminAddIdentityPoolRequest().displayName("User");
        AdminAddIdentityPoolResponse adminAddIdentityPool = tenantApi.adminAddIdentityPool(addIdentity, token.getX_API_TENANT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(adminAddIdentityPool);
    }
}
