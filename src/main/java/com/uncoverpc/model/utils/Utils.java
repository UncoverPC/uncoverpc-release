package com.uncoverpc.model.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.uncoverpc.jwt.JwtUtil;
import com.uncoverpc.security.user.CustomUserDetails;

@Component
public class Utils {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    //ADD ORIGINS HERE
    public static final String[] allowedOrigins = {"chrome-extension://ocbbkmkbllooadfldeijemdamkjlhbgm"};

    public static final String SECRET_KEY = "isof3-ed.;df!oflez;!";

    @Autowired
    private JwtUtil jwtUtil;

    public static String formatMonitoredWebsiteDocumentName(String email, Date date) {
        return email + " -- " + formatDate(date);
    }

    public static String formatMonitoredWebsiteDocumentName(String email, String date) {
        return email + " -- " + date;
    }

    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static Date getNow() {
        return new Date(System.currentTimeMillis());
    }

    public static Date epcohToDate(long epoch) {
        return new Date(epoch);
    }

    // TO DO more secure encryption
    public static String encrypt(String text, String key) {
        char[] characters = text.toCharArray();
        StringBuilder encrpytion = new StringBuilder();

        for (char character : characters) {
            character -= 1;
            encrpytion.append(character);
        }
        return encrpytion.toString();
    }

    public static String decrypt(String text, String key) {
        char[] characters = text.toCharArray();
        StringBuilder encrpytion = new StringBuilder();

        for (char character : characters) {
            character += 1;
            encrpytion.append(character);
        }
        return encrpytion.toString();
    }

    public static int hasJWT(Cookie[] cookies) {
        if (cookies == null)
            return -1;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equalsIgnoreCase("jwt")) {
                return i;
            }
        }
        return -1;
    }

    public boolean validateUser(HttpServletRequest request) {
        try {
            final String auth = request.getHeader("Authorization");
            if (auth == null || !auth.startsWith("Bearer ")) {
                return false;
            }
            String jwt = auth.substring(7);
            String email = jwtUtil.extractUsername(jwt);

            if (email != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
                CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal();
                return jwtUtil.validateToken(jwt, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean contains(String[] array, String str){
        for(String arr: array){
            if(arr.equals(str)){
                return true;
            }
        }
        return false;
    }
}
