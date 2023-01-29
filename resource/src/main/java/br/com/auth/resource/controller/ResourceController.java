package br.com.auth.resource.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/resource")
public class ResourceController {

    @GetMapping("/products")
    public String getProducts(){
        return " products route public";
    }

    @GetMapping("/login")
    public String getLogin(@AuthenticationPrincipal OidcUser oidc){
        return String.format(
                "<h1>Oauth2</h1>"                   +
				"<h3>Principal: %s</h3>"            +
				"<h3>Email attribute: %s</h3>"      +
				"<h3>Authorities: %s</h3>"          +
				"<h3>JWT: %s</h3>"
				,
                oidc,
                oidc.getAttribute("email"),
                oidc.getAuthorities(),
                oidc.getIdToken().getTokenValue());
    }
    
    @GetMapping("/customers")
    public String getCustomer(@AuthenticationPrincipal Jwt jwt){

        return String.format("Principal: %s\n" +
				"Email attribute: %s\n" +
				"JWT: %s\n", jwt.getClaims(), jwt.getClaim("email"), jwt.getTokenValue());
    }
}
