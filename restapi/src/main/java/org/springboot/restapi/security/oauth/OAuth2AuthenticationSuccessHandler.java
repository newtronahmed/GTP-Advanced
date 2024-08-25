package org.springboot.restapi.security.oauth;

import org.springboot.restapi.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // Generate JWT and redirect or return it in the response
        String jwt = tokenProvider.generateToken(authentication);
        // Example: redirect to your frontend with the JWT token as a query parameter
//        String jwt = "your_jwt_token_here"; // Replace with actual JWT generation logic
        response.sendRedirect("/success?token=" + jwt);
    }
}
