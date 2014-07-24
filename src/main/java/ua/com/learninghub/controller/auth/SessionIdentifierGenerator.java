package ua.com.learninghub.controller.auth;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by vasax32 on 24.07.14.
 */
public class SessionIdentifierGenerator {
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
