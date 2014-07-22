package PassCryptTest;

import junit.framework.Assert;
import org.junit.*;
import junit.framework.*;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * Created by Max on 22.07.2014.
 */
public class PassCryptMethodTest {

public class UserTests {
    private MessageDigest md;

    @Test
    public String passwordCryptTest(String pass) {
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();

            byte[] passBytes = pass.getBytes();
            byte[] passDigestBytes = md.digest(passBytes);

            StringBuffer passDigestString = new StringBuffer();
            for (int i = 0; i < passDigestBytes.length; i++) {
                passDigestString.append(Integer.toHexString(0xff & passDigestBytes[i]));
            }
            return passDigestString.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
}
