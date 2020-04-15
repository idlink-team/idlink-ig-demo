package demo.reference;

import demo.tools.AuthApiToken;
import demo.tools.PropertiesUtils;
//import idlink.ig.client.api.AuthenticationApi;
import io.swagger.client.ApiException;

/**
 * Demo device login and get customized JWT token
 * <p>
 * Important Notes:
 * Please visit https://authdemo.id.link:1445/#/b964643b-738e-47c4-9b0e-dc1b08d4cffa to view the device pre-token-generation script.
 * Feel free to edit the script online to meet your needs.
 */
public class OAuthLoginAndCustomizeJWTTokenDemo {
//    /**
//     * Client id
//     */
//    public static final String clientId = PropertiesUtils.getClientId();
//
//    /**
//     * Client secret
//     */
//    public static final String clientSecret = PropertiesUtils.getClientSecret();
//
//    public static void main(String[] args) throws ApiException {
//        // Build authorization header
//        String authorization = AuthApiToken.build(clientId, clientSecret);
//        // Create Api Instance
//        AuthenticationApi authApi = new AuthenticationApi();
//        // Login testDevice by password
//        String deviceLoginResult = authApi.deviceLogin(authorization, "password", "sda142&h4j2", null,
//                null, "testDevice");
//        // Login result
//        System.out.println("Login successful and get OAuth tokens: ");
//        System.out.println(deviceLoginResult);
//    }
}
