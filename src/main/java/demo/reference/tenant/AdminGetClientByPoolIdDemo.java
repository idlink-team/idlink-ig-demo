package demo.reference.tenant;

import demo.tools.PropertiesUtils;
import demo.tools.TenantApiToken;
import idlink.ig.client.api.TenantApi;
import idlink.ig.client.model.AdminGetClientByPoolIdRequest;
import idlink.ig.client.model.AdminGetClientByPoolIdResponse;
import io.swagger.client.ApiException;

public class AdminGetClientByPoolIdDemo {

    // tenant client id
    public static String tenantId = PropertiesUtils.getTenantId();

    // tenant secret
    public static String tenantSecret = PropertiesUtils.getTenantSecret();

    /**
     * Get client by poolId
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        TenantApiToken token = TenantApiToken.build(tenantId, tenantSecret);
        // Create instance
        TenantApi tenantApi = new TenantApi();
        // get from: AdminListIdentityPoolsDemo
        String identityId = "<Please put pool client id here>";
        // Get client by poolId
        AdminGetClientByPoolIdRequest getClientByPoolIdRequest = new AdminGetClientByPoolIdRequest();
        getClientByPoolIdRequest.setIdentityId(identityId);
        AdminGetClientByPoolIdResponse adminGetClientByPoolId = tenantApi.adminGetClientByPoolId(getClientByPoolIdRequest, token.getX_API_TENANT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println(adminGetClientByPoolId);
    }
}
