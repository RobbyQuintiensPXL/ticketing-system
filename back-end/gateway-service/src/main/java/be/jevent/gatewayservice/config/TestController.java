package be.jevent.gatewayservice.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    private final static String EMAIL_CLAIM = "https://example.com/email";

    @GetMapping("/test")
    public ResponseEntity<String> testPoint(){
        return new ResponseEntity<>("Hallo $#{authentication.name}", HttpStatus.OK);
    }

    @GetMapping("/office")
    public ResponseEntity<String> officePoint(@AuthenticationPrincipal Jwt principal){
        String test = principal.getClaims().get(EMAIL_CLAIM).toString();
        return new ResponseEntity<>("Welcome " + test, HttpStatus.OK);
    }
}
