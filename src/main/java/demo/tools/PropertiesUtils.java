package demo.tools;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author shoneliu on 2020/03/12
 */
public class PropertiesUtils {

    public static final String apiSecret_key = "apiSecret";
    public static final String apiClientId_key = "apiClientId";
    public static final String clientId_key = "clientId";
    public static final String clientIdSecret_key = "clientSecret";

    public static final Properties properties = new Properties();

    static {
        try {
            ClassPathResource secretProp = new ClassPathResource("secret.properties");
            properties.load(secretProp.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getApiClientId() {
        return properties.getProperty(apiClientId_key);
    }
    public static String getApiSecret() {
        return properties.getProperty(apiSecret_key);
    }

    public static String getClientId() {
        return properties.getProperty(clientId_key);
    }
    public static String getClientSecret() {
        return properties.getProperty(clientIdSecret_key);
    }

    public static String getTenantSecret() {
        return properties.getProperty("tenantSecret");
    }

    public static String getTenantId(){
        return properties.getProperty("tenantId");
    }

    public static void main(String[] args) {
        String apiSecret = PropertiesUtils.getApiSecret();
        System.out.println("apiSecret = " + apiSecret);
    }

}
