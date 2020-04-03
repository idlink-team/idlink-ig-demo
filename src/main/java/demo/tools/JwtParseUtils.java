package demo.tools;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.Payload;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;

public class JwtParseUtils {

    public static void printJwt(String jwtToken) {
        try {
            com.nimbusds.jwt.JWT idToken = JWTParser.parse(jwtToken);
            SignedJWT signedIdToken = (SignedJWT) idToken;
            JWSHeader header = signedIdToken.getHeader();
            Payload payload = signedIdToken.getPayload();
//            System.out.println("header = " + header);
            System.out.println("payload = " + payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
