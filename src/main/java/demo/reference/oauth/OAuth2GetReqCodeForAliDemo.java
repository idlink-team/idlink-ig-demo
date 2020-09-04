package demo.reference.oauth;

import demo.tools.GetUtils;
import demo.tools.PropertiesUtils;
import io.swagger.client.ApiException;

import java.io.IOException;

public class OAuth2GetReqCodeForAliDemo {

    public static String apiClientId = PropertiesUtils.getApiClientId();

    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * get ReqCode For Ali TMALL By OAuth2
     */
    public static void main(String[] args) throws ApiException, IOException {
        String result = GetUtils.get("https://api.ig.id.link:1443/oauth2/authorize/codeOnly?response_type=code&client_id=" + apiClientId + "&redirect_uri=your_url&state=123");
        System.out.println("result = " + result);
    }
}
