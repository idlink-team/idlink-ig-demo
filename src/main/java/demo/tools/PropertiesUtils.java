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
    public static final String tenantId_key = "tenantId";
    public static final String tenantSecret_key = "tenantSecret";

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

    public static String getTenantId(){
        return properties.getProperty(tenantId_key);
    }

    public static String getTenantSecret() {
        return properties.getProperty(tenantSecret_key);
    }

    public static void main(String[] args) {
        String apiSecret = PropertiesUtils.getApiSecret();
        System.out.println("apiSecret = " + apiSecret);
    }
}
