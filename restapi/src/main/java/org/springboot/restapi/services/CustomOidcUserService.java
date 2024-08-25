package org.springboot.restapi.services;

import org.springboot.restapi.models.AuthProvider;
import org.springboot.restapi.models.User;

import org.springboot.restapi.repository.AuthProviderRepository;
import org.springboot.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Map;

@Service
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private AuthProviderRepository authProviderRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        System.out.println("CustomOAuth2UserService is being called");
//        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
//
//        String providerId = oAuth2User.getAttribute("sub"); // Google’s unique ID
//        String email = oAuth2User.getAttribute("email");
//        String name = oAuth2User.getAttribute("name");
//        System.out.println(oAuth2User.toString());
//        // Check if user already exists in AuthProvider
//        Optional<AuthProvider> authProviderOpt = authProviderRepository.findByProviderIdAndProvider(providerId, "GOOGLE");
//
//        User user;
//        if (authProviderOpt.isPresent()) {
//            // User exists, fetch the user
//            user = authProviderOpt.get().getUser();
//        } else {
//            // Check if there's an existing account with the same email
//            Optional<User> userOpt = userRepository.findByEmail(email);
//            System.out.println("email from google: " + email);
//            if (userOpt.isPresent()) {
//                // Link the Google account to this user
//                user = userOpt.get();
//                AuthProvider authProvider = new AuthProvider(user, "GOOGLE", providerId, email);
//                authProviderRepository.save(authProvider);
//            } else {
//                // Register a new user with Google account
//                user = new User();
//                user.setUsername(name);
//                user.setEmail(email);
//                user.setPassword(""); // No password needed for OAuth accounts
//                userRepository.save(user);
//
//                // Save the auth provider
//                AuthProvider authProvider = new AuthProvider(user, "GOOGLE", providerId, email);
//                authProviderRepository.save(authProvider);
//            }
//        }
//
//        return new DefaultOAuth2User(
//                oAuth2User.getAuthorities(),
//                Map.of("name", user.getUsername(), "email", user.getEmail()),
//                "email" // The attribute to use as the principal
//        );
//    }
//}
public class CustomOidcUserService extends OidcUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthProviderRepository authProviderRepository;
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        String providerId = oidcUser.getAttribute("sub"); // Google’s unique ID
        String email = oidcUser.getAttribute("email");
        String name = oidcUser.getAttribute("name");
        System.out.println(oidcUser.toString());
        // Check if user already exists in AuthProvider
        Optional<AuthProvider> authProviderOpt = authProviderRepository.findByProviderIdAndProvider(providerId, "GOOGLE");

        User user;
        if (authProviderOpt.isPresent()) {
            // User exists, fetch the user
            user = authProviderOpt.get().getUser();
        } else {
            // Check if there's an existing account with the same email
            Optional<User> userOpt = userRepository.findByEmail(email);
            System.out.println("email from google: " + email);
            if (userOpt.isPresent()) {
                // Link the Google account to this user
                user = userOpt.get();
                AuthProvider authProvider = new AuthProvider(user, "GOOGLE", providerId, email);
                authProviderRepository.save(authProvider);
            } else {
                // Register a new user with Google account
                user = new User();
                user.setUsername(name);
                user.setEmail(email);
                user.setPassword(""); // No password needed for OAuth accounts
                userRepository.save(user);

                // Save the auth provider
                AuthProvider authProvider = new AuthProvider(user, "GOOGLE", providerId, email);
                authProviderRepository.save(authProvider);
            }
        }
        return oidcUser;
    }
}
