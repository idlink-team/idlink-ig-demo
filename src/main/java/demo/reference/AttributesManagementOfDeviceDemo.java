package demo.reference;

import demo.tools.AdminApiToken;
import demo.tools.PropertiesUtils;
import idlink.ig.client.api.AdminApi;
//import idlink.ig.client.model.AdminAddUserAttributeRequest;
//import idlink.ig.client.model.AdminDeleteUserAttributeRequest;
//import idlink.ig.client.model.AdminListUserAttributesRequest;
//import idlink.ig.client.model.AdminListUserAttributesResponse;
import io.swagger.client.ApiException;

/**
 * Add, List, Delete attribute of user type Device
 */
public class AttributesManagementOfDeviceDemo {
    /**
     * Api client id
     */
    public static String apiClientId = PropertiesUtils.getApiClientId();
    /**
     * Admin api secret
     */
    public static String apiSecret = PropertiesUtils.getApiSecret();

    public static void main(String[] args) throws ApiException {
        // Build token before calling each api
//        AdminApiToken token = AdminApiToken.build(apiClientId, apiSecret);
//
//        // Create Api Instance
//        AdminApi api = new AdminApi();
//
//        //----------- Add a new attribute ------------------
//        AdminAddUserAttributeRequest adminAddUserAttributeRequest = new AdminAddUserAttributeRequest()
//                // attribute unique name
//                .code("xAttr")
//                // description, optional
//                .description("X Attribute of Device")
//                // displayName
//                .displayName("X Attr")
//                // attribute type:
//                .type(AdminAddUserAttributeRequest.TypeEnum.STRING)
//                // add to user type: device | device
//                .userType(AdminAddUserAttributeRequest.UserTypeEnum.DEVICE);
//        api.adminAddUserAttribute(adminAddUserAttributeRequest, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
//        System.out.println("Step1. Attribute xAttr added to Device Type");
//
//        //-----------  List user attributes of type device ------------------
//        // create a new token
//        token = AdminApiToken.build(apiClientId, apiSecret);
//        // build request
//        AdminListUserAttributesRequest adminListUserAttributesRequest = new AdminListUserAttributesRequest().userType(AdminListUserAttributesRequest.UserTypeEnum.DEVICE);
//        // call api to get result
//        AdminListUserAttributesResponse adminListUserAttributesResponse = api.adminListUserAttributes(adminListUserAttributesRequest, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
//        System.out.println("\nStep2. List Attributes of Device Type");
//        System.out.println(adminListUserAttributesResponse.getData());
//
//        //-----------  Remove user attributes of type device ------------------
//        // create a new token
//        token = AdminApiToken.build(apiClientId, apiSecret);
//        // build request
//        AdminDeleteUserAttributeRequest adminDeleteUserAttributeRequest = new AdminDeleteUserAttributeRequest().userType(AdminDeleteUserAttributeRequest.UserTypeEnum.DEVICE).code("xAttr");
//        // call api to get result
//        api.adminDeleteUserAttribute(adminDeleteUserAttributeRequest, token.getX_API_CLIENT_ID(), token.getX_API_TIMESTAMP(), token.getX_API_TOKEN());
//        System.out.println("\nStep3. Attribute xAttr deleted from Device Type");
    }
}
