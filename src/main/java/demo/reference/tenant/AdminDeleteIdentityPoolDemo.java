package demo.reference.tenant;

import demo.tools.PropertiesUtils;
import demo.tools.TenantApiToken;
import idlink.ig.client.api.TenantApi;
import idlink.ig.client.model.AdminDeleteIdentityPoolRequest;
import idlink.ig.client.model.AdminDeleteIdentityPoolResponse;
import io.swagger.client.ApiException;

public class AdminDeleteIdentityPoolDemo {

    // tenant client id
    public static String tenantId = PropertiesUtils.getTenantId();

    // tenant secret
    public static String tenantSecret = PropertiesUtils.getTenantSecret();

    /**
     * Delete identity pool
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        TenantApiToken token = TenantApiToken.build(tenantId, tenantSecret);
        // Create instance
        TenantApi tenantApi = new TenantApi();
        // get from: AdminListIdentityPoolsDemo
        String poolClientId = "78f62111-7b8a-4f55-aba5-879cf877efbd";
        // Delete identity pool
        AdminDeleteIdentityPoolRequest deleteIdentityPoolRequest = new AdminDeleteIdentityPoolRequest();
        deleteIdentityPoolRequest.setIdentityId(poolClientId);
        AdminDeleteIdentityPoolResponse adminDeleteIdentityPool = tenantApi.adminDeleteIdentityPool(deleteIdentityPoolRequest, token.getX_API_TENANT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(adminDeleteIdentityPool);
    }
}
