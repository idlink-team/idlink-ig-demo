package demo.reference;

import com.google.gson.Gson;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import demo.tools.AdminApiToken;
import idlink.ig.client.api.AdminApi;
import idlink.ig.client.model.AdminInitialLoginRequest;
import io.swagger.client.ApiException;
import java.util.HashMap;

/**
 * Verify JWT Token
 */
public class VerifyJWTTokenDemo {
    /**
     * Api client id
     */
    public static String apiClientId = "gig";
    /**
     * Admin api secret
     */
    public static String apiSecret = "dakjdiewsl2854o23";

    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);

        // Create Api Instance
        AdminApi adminApi = new AdminApi();

        // Login with testUser
        AdminInitialLoginRequest adminInitialLoginRequest = new AdminInitialLoginRequest()
                .userType(AdminInitialLoginRequest.UserTypeEnum.PERSON)
                .username("testUser").password("kdi@43k423")
                // Add clientMeta for customize claims.
                .putClientMetadataItem("meta", "useClaim1");
        String oauth2Tokens = adminApi.adminInitialLogin(adminInitialLoginRequest,
                token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
        System.out.println("Logged in and got OAuth tokens: ");
        System.out.println(oauth2Tokens);

        // Get JWT Token
        Gson gson = new Gson();
        HashMap tokenMap = gson.fromJson(oauth2Tokens, HashMap.class);

        // Verify JWT Token
        boolean verifyResult;
        try {
            String jwtToken = tokenMap.get("id_token").toString();
            com.nimbusds.jwt.JWT idToken = JWTParser.parse(jwtToken);
            SignedJWT signedIdToken = (SignedJWT) idToken;
            // The JWK got from https://authdemo.id.link:1443/jwk
            com.nimbusds.jose.jwk.RSAKey rsaKey = new com.nimbusds.jose.jwk.RSAKey(
                    new Base64URL("oeTZNhi4skBTCFPodzhAOFHhW7t3Qh-bI3f7C08cb8I16uyS6iW7dqwiHc6QFzouIy_hURkJx2sVJR1AWbKI5wnB74a89pJ1eq1zaILdHpi-3-2TEaPCLEBlfJpSiAvcS55TkEHUTVLLJq0TqwhvfcKU0UTWYzpi75HKF3Xrmgk"),
                    new Base64URL("AQAB"),
                    KeyUse.SIGNATURE,
                    null,
                    JWSAlgorithm.RS512,
                    "defaultKey",
                    null,
                    null,
                    null,
                    null,
                    null
            );
            RSASSAVerifier rsassaVerifier = new RSASSAVerifier(rsaKey);
            verifyResult = (signedIdToken.verify(rsassaVerifier));
        } catch (Exception e) {
            verifyResult = false;
        }
        System.out.println("Verify Result: " + verifyResult);
    }
}
