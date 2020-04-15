package demo.reference;

import com.google.gson.internal.LinkedTreeMap;
import demo.tools.ClientApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.TenantApi;
import idlink.ig.client.model.*;
import io.swagger.client.ApiException;

public class IdPoolListDemo {

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
        ClientApiToken token = ClientApiToken.build(clientId,clientSecret);

        String X_API_TENANT_ID = token.getX_API_TENANT_ID();
        Long X_API_TIMESTAMP = token.getX_API_TIMESTAMP();
        String X_API_TOKEN = token.getX_API_TOKEN();

        TenantApi tenantApi = new TenantApi();


        //1.Pool list
        AdminListIdentityPoolsResponse adminListIdentityPoolsResponse = tenantApi.adminListIdentityPools(X_API_TENANT_ID, X_API_TIMESTAMP, X_API_TOKEN);
        System.out.println(adminListIdentityPoolsResponse);


        // 2.Add new pool
        AdminAddIdentityPoolRequest addIdentity = new AdminAddIdentityPoolRequest();
        addIdentity.setDisplayName("newPool01");
        AdminAddIdentityPoolResponse adminAddIdentityPool = tenantApi.adminAddIdentityPool(addIdentity,X_API_TENANT_ID,X_API_TIMESTAMP,X_API_TOKEN);
        if("OK".equals(adminAddIdentityPool.getHttpStatus())){
            System.out.println("new Pool has been added" + addIdentity.getDisplayName());
        }else {
            System.out.println(adminAddIdentityPool);
        }

        //3.Generate new ClientSecret
        AdminGenerateNewClientSecretRequest generateNewClient = new AdminGenerateNewClientSecretRequest();
        LinkedTreeMap map = (LinkedTreeMap) adminAddIdentityPool.getData();
        String identityId = (String) map.get("id");
        generateNewClient.setIdentityId(identityId);
        AdminGenerateNewClientSecretResponse adminGenerateNewClientSecret = tenantApi.adminGenerateNewClientSecret(generateNewClient, X_API_TENANT_ID, X_API_TIMESTAMP,X_API_TOKEN);
        if ("OK".equals(adminGenerateNewClientSecret.getHttpStatus())){
            System.out.println("Generate new ClientSecret");
        }else{
            System.out.println(adminGenerateNewClientSecret);
        }

        //4.Get single pool Secret
        AdminGetClientByPoolIdRequest getClient = new AdminGetClientByPoolIdRequest();
        getClient.setIdentityId(identityId);
        AdminGetClientByPoolIdResponse adminGetClientByPoolId = tenantApi.adminGetClientByPoolId(getClient, X_API_TENANT_ID, X_API_TIMESTAMP, X_API_TOKEN);
        if("OK".equals(adminGetClientByPoolId.getHttpStatus())){
            System.out.println("Get single pool Secret");
        }else {
            System.out.println(adminGenerateNewClientSecret);
        }

        //5.Delete pool
        AdminDeleteIdentityPoolRequest delete = new AdminDeleteIdentityPoolRequest();
        delete.setIdentityId(identityId);
        AdminDeleteIdentityPoolResponse adminDeleteIdentityPool = tenantApi.adminDeleteIdentityPool(delete, X_API_TENANT_ID, X_API_TIMESTAMP, X_API_TOKEN);
        if("OK".equals(adminDeleteIdentityPool.getHttpStatus())){
            System.out.println("new Pool has been deleted");
        }else {
            System.out.println(adminDeleteIdentityPool);
        }

    }

}
