package ua.com.learninghub.businessLogic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Sania_000 on 7/24/2014.
 */
public class UserLogic {
    private MessageDigest md;

    public String encryptPass(String pass){
        try {
            md = MessageDigest.getInstance("MD5");

            StringBuffer passDigestString = new StringBuffer();

            return passDigestString.toString();
        }catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
