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
        String identityId = "87cb785f-0767-4f6a-aeca-c4fd7e8534e9";
        // Delete identity pool
        AdminDeleteIdentityPoolRequest deleteIdentityPoolRequest = new AdminDeleteIdentityPoolRequest();
        deleteIdentityPoolRequest.setIdentityId(identityId);
        AdminDeleteIdentityPoolResponse adminDeleteIdentityPool = tenantApi.adminDeleteIdentityPool(deleteIdentityPoolRequest, token.getX_API_TENANT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(adminDeleteIdentityPool);
    }
}
