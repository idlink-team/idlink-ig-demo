package demo.pool;

import demo.tools.ClientApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.TenantApi;
import idlink.ig.client.model.AdminAddIdentityPoolRequest;
import idlink.ig.client.model.AdminAddIdentityPoolResponse;
import idlink.ig.client.model.AdminListIdentityPoolsResponse;
import io.swagger.client.ApiException;

public class CreateIdentityPool {

    public static String clientId = PropertiesUtils.getClientId();

    /**
     *  Client secret
     */
    public static String clientSecret = PropertiesUtils.getClientSecret();

    public static void main(String[] args) throws ApiException {





            //Build token
            ClientApiToken token = ClientApiToken.build(clientId,clientSecret);

            String X_API_TENANT_ID = token.getX_API_TENANT_ID();
            Long X_API_TIMESTAMP = token.getX_API_TIMESTAMP();
            String X_API_TOKEN = token.getX_API_TOKEN();

            TenantApi tenantApi = new TenantApi();



            // 2.Add new pool
            AdminAddIdentityPoolRequest addIdentity = new AdminAddIdentityPoolRequest();
            addIdentity.setDisplayName("Device");
            AdminAddIdentityPoolResponse adminAddIdentityPool = tenantApi.adminAddIdentityPool(addIdentity,X_API_TENANT_ID,X_API_TIMESTAMP,X_API_TOKEN);
            if("OK".equals(adminAddIdentityPool.getHttpStatus())){
                System.out.println("new Pool has been added" + addIdentity.getDisplayName());
            }else {
                System.out.println(adminAddIdentityPool);
            }
    }
}
