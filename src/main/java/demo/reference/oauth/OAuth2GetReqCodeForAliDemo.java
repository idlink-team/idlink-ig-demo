package demo.reference.oauth;

import demo.tools.AuthApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.OAuth2Api;
import idlink.ig.client.model.OAuth2GetReqCodeResponse;
import io.swagger.client.ApiException;

import java.io.IOException;

public class OAuth2GetReqCodeForAliDemo {

    public static String apiClientId = PropertiesUtils.getApiClientId();

    public static String apiSecret = PropertiesUtils.getApiSecret();

    /**
     * get ReqCode For Ali TMALL By OAuth2
     */
    public static void main(String[] args) throws ApiException, IOException {
        String authorization = AuthApiToken.build(apiClientId, apiSecret);
        OAuth2Api oAuth2Api = new OAuth2Api();
        OAuth2GetReqCodeResponse response = oAuth2Api.oAuth2AuthorizeGetCode("your_url", "state", authorization);

        System.out.println("result status = " + response.getHttpStatus());
        System.out.println("result = " + response.getData());
    }
}
