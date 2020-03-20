package demo.tools;

import java.util.Base64;

/**
 * @author IDLINK
 */
public class AuthApiToken {

    /**
     * Generate authorization with client_id and client_secret
     * @param clientId Client id (required)
     * @param clientSecret Client secret (required)
     * @return authorization
     */
    public static String build(String clientId, String clientSecret) {
        return "Basic " + encoder((clientId + ":" + clientSecret).getBytes());
    }

    private static String encoder(byte[] content) {
        String result = Base64.getEncoder().encodeToString(content);
        return result.replaceAll("\r|\n", "");
    }
}
