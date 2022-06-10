package be.jevents.ticketservice.filter;

import org.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserNameFilter {

    public static final String AUTH_TOKEN = "Authorization";
    private static final Logger logger = LoggerFactory.getLogger(UserNameFilter.class);

    public String getUsername(HttpHeaders requestHeaders) {
        String username = "";

        if (requestHeaders.get(AUTH_TOKEN) != null) {
            List<String> header = requestHeaders.get(AUTH_TOKEN);
            assert header != null;
            // String head = String.valueOf(header.stream().findFirst().isPresent());
            String head = header.stream().findFirst().orElse("test");
            String authToken = head.replace("Bearer ", "");
            JSONObject jsonObj = decodeJWT(authToken);
            try {
                username = jsonObj.getString("preferred_username");
            } catch (Exception e) {
                logger.debug(e.getMessage());
            }
        }
        return username;
    }

    private JSONObject decodeJWT(String JWTToken) {
        String[] split_string = JWTToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        return new JSONObject(body);
    }
}
