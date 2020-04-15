package demo.reference.pool;

import demo.tools.TenantApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.TenantApi;
import idlink.ig.client.model.AdminAddIdentityPoolRequest;
import idlink.ig.client.model.AdminAddIdentityPoolResponse;
import io.swagger.client.ApiException;

public class AdminAddIdentityPoolDemo {

    public static String tenantId = PropertiesUtils.getTenantSecret();
    public static String tenantSecret = PropertiesUtils.getTenantId();

    public static void main(String[] args) throws ApiException {
        TenantApiToken token = TenantApiToken.build(tenantId, tenantSecret);

        String X_API_TENANT_ID = token.getX_API_TENANT_ID();
        Long X_API_TIMESTAMP = token.getX_API_TIMESTAMP();
        String X_API_TOKEN = token.getX_API_TOKEN();

        TenantApi tenantApi = new TenantApi();

        AdminAddIdentityPoolRequest addIdentity = new AdminAddIdentityPoolRequest();
        addIdentity.setDisplayName("newPool02");
        AdminAddIdentityPoolResponse adminAddIdentityPool = tenantApi.adminAddIdentityPool(addIdentity, X_API_TENANT_ID, X_API_TIMESTAMP, X_API_TOKEN);
        if ("OK".equals(adminAddIdentityPool.getHttpStatus())) {
            System.out.println("new Pool has been added" + addIdentity.getDisplayName());
        } else {
            System.out.println(adminAddIdentityPool);
        }
    }
}
