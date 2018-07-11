package de.saschadoemer.arts.decoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class DecodeSecurityTokenService {

    public static final String SIGNATURE_KEY = "signature";
    public static final String STATE_KEY = "state";
    public static final String TOKEN_KEY = "token";
    public static final String ERROR_KEY = "error";

    public String decode(String token) {
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        return new String(decodedBytes);
    }

    public Map<String, String> extractAuthenticationResults(String query) {
        String[] queryParams = query.split("&");
        Map<String, String> authenticationResults = new HashMap<>();
        Arrays.stream(queryParams).forEach(s -> {
            String[] keyValuePair = s.split("=");
            try {
                authenticationResults.put(keyValuePair[0], URLDecoder.decode(keyValuePair[1], StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                // NOP
            }
        });
        return authenticationResults;
    }


}
