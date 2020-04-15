package demo.reference;

import com.google.gson.internal.LinkedTreeMap;
import demo.tools.TenantApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.TenantApi;
import idlink.ig.client.model.AdminListIdentityPoolsResponse;
import idlink.ig.client.model.AdminResetPoolManagerSecretResponse;
import io.swagger.client.ApiException;

public class IdPoolResetSecretDemo {

    /**
     *  Client id
     */
    public static String clientId = PropertiesUtils.getClientId();

    /**
     *  Client secret
     */
    public static String clientSecret = PropertiesUtils.getClientSecret();

    public static void main(String[] args) throws ApiException {

        //Build token
        TenantApiToken token = TenantApiToken.build(clientId,clientSecret);

        String X_API_TENANT_ID = token.getX_API_TENANT_ID();
        Long X_API_TIMESTAMP = token.getX_API_TIMESTAMP();
        String X_API_TOKEN = token.getX_API_TOKEN();

        TenantApi tenantApi = new TenantApi();

        //1.Pool list
        AdminListIdentityPoolsResponse adminListIdentityPools = tenantApi.adminListIdentityPools(X_API_TENANT_ID, X_API_TIMESTAMP, X_API_TOKEN);
        System.out.println(adminListIdentityPools);
        //2.Reset pool secret
        AdminResetPoolManagerSecretResponse adminResetPoolManagerSecretResponse = tenantApi.adminResetPoolManagerSecret(X_API_TENANT_ID, X_API_TIMESTAMP, X_API_TOKEN);
        if("OK".equals(adminResetPoolManagerSecretResponse.getHttpStatus())){
            System.out.println("Secret has been reset");
        }else {
            System.out.println(adminResetPoolManagerSecretResponse);
        }
        //3.Pool list (expect fail)
        try{
            AdminListIdentityPoolsResponse adminListIdentityFail = tenantApi.adminListIdentityPools(X_API_TENANT_ID, X_API_TIMESTAMP, X_API_TOKEN);
            System.out.println("fail：" + adminListIdentityFail +"\n");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //4.Pool list (expect success)
            LinkedTreeMap data = (LinkedTreeMap) adminResetPoolManagerSecretResponse.getData();
            String newSecret = (String) data.get("apiSecret");
            TenantApiToken newToken = TenantApiToken.build(clientId,newSecret);

            AdminListIdentityPoolsResponse adminListIdentitySuccess = tenantApi.adminListIdentityPools(newToken.getX_API_TENANT_ID(), newToken.getX_API_TIMESTAMP(), newToken.getX_API_TOKEN());
            System.out.println("new secret: " +adminListIdentitySuccess);
        }

    }
}
