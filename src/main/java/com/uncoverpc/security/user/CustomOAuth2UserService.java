package com.uncoverpc.security.user;

import com.uncoverpc.model.user.CustomOAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.uncoverpc.db.UserService;
import com.uncoverpc.model.user.User;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user =  super.loadUser(userRequest);
        return new CustomOAuth2User(user);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        try {
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            System.out.println(user);
            return new CustomUserDetails(user);
        } catch (Exception e) {
            System.err.println("User not found: " + email);
        }
        throw new UsernameNotFoundException("User not found");
    }

}
