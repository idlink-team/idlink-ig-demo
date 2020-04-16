package demo.reference.identity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import demo.tools.AdminApiToken;
import demo.tools.JwtParseUtils;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.AdminInitialLoginRequest;
import idlink.ig.client.model.AdminInitialLoginResponse;
import io.swagger.client.ApiException;

public class AdminInitialLoginDemo {

    // api client id
    public static String apiClientId = PropertiesUtils.getApiClientId();

    // api secret
    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * Initial login
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
        // Create instance
        AdminApi adminApi = new AdminApi();
        // Initial login
        AdminInitialLoginRequest initialLoginRequest = new AdminInitialLoginRequest()
                .putDynamicClaimItem("host","127.0.0.1")
                .putDynamicClaimItem("operateSystem", "windows")
                .username("Alex").password("sda142&h4j2");
        AdminInitialLoginResponse adminInitialLoginResponse = adminApi.adminInitialLogin(initialLoginRequest, token.getX_API_CLIENT_ID(),
                token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        JsonObject jsonObject = new Gson().fromJson(adminInitialLoginResponse.getData(), JsonObject.class);
        String access_token = jsonObject.get("access_token").getAsString();
        System.out.println("access_token: ");
        System.out.println(access_token);
        JwtParseUtils.printJwt(access_token);
    }
}
