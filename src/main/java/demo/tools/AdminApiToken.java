package demo.tools;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;

public class AdminApiToken {

    public static AdminApiToken build(String apiClientId, String apiSecret) {
        AdminApiToken token = new AdminApiToken();
        token.setX_API_CLIENT_ID(apiClientId);
        token.setX_API_TIMESTAMP(new Date().getTime());
        String apiToken = sha1Encrypt(token.getX_API_CLIENT_ID() + apiSecret + token.getX_API_TIMESTAMP());
        token.setX_API_TOKEN(apiToken);
        return token;
    }

    public static String sha1Encrypt(String password) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA1");
            sha.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = sha.digest();
            return java.util.Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private String X_API_CLIENT_ID;
    private Long X_API_TIMESTAMP;
    private String X_API_TOKEN;

    public String getX_API_CLIENT_ID() {
        return X_API_CLIENT_ID;
    }

    public void setX_API_CLIENT_ID(String x_API_CLIENT_ID) {
        X_API_CLIENT_ID = x_API_CLIENT_ID;
    }

    public Long getX_API_TIMESTAMP() {
        return X_API_TIMESTAMP;
    }

    public void setX_API_TIMESTAMP(Long x_API_TIMESTAMP) {
        X_API_TIMESTAMP = x_API_TIMESTAMP;
    }

    public String getX_API_TOKEN() {
        return X_API_TOKEN;
    }

    public void setX_API_TOKEN(String x_API_TOKEN) {
        X_API_TOKEN = x_API_TOKEN;
    }
}
